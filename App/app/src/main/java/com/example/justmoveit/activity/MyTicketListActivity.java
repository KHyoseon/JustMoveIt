package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;
import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.justmoveit.R;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.fragment.BlankFragment;
import com.example.justmoveit.model.Movie;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.example.justmoveit.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTicketListActivity extends AppCompatActivity {
    private Gson gson;
    private SharedPreferences.Editor editor1;
    private SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();

        // 서버 통신 스레드 - 서버에서 예매 이력 가져오기
        ConnectionThread thread = new ConnectionThread();
        Log.d("MyTicketsListActivity", "connection thread start");
        thread.start();
        /*try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        synchronized (thread){
            try {
                Log.d("MyTicketsListActivity", "main thread waiting");
                thread.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_my_ticket_list);

        // 프로필 섹션 채우기
        getFillProfileSection();

        // 로그아웃
        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                editor1 = userSP.edit();
                editor1.remove("user_info");
                editor1.remove("user_tickets");
                editor1.remove("phone_number");
                editor1.apply();

                editor2 = movieSP.edit();
                editor2.remove("my_ranking");
                editor2.apply();

                finish(); // 현재 액티비티 종료 -> 메인으로 돌아감
            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new Gson();
        Log.d("MyTicketListActivity - onResume()", "실행");
        // 서버에서 받아왔는데 아무것도 없으면 빈 프래그먼트로 교체
        ArrayList<ReservedTicket> userTickets = gson.fromJson(userSP.getString("user_tickets", ""), TypeToken.getParameterized(ArrayList.class, ReservedTicket.class).getType());
        String userPN = userSP.getString("phone_number", "");
        if(userPN.equals("")){
            BlankFragment blankFragment = new BlankFragment("전화 번호 정보가 없습니다.");
            getSupportFragmentManager().beginTransaction().replace(R.id.TL_container, blankFragment).commit();
        } else if(userTickets==null || userTickets.size()==0){
            BlankFragment blankFragment = new BlankFragment("예매 내역이 없습니다.");
            getSupportFragmentManager().beginTransaction().replace(R.id.TL_container, blankFragment).commit();
        }
    }

    private void getFillProfileSection() {
        ImageView imgProfile = findViewById(R.id.profile_img);
        TextView userName = findViewById(R.id.user_name);
        TextView userEmail = findViewById(R.id.user_email);

        // 세션에서 사용자 정보 가져와서 profile_section setText
        User user =  gson.fromJson(userSP.getString("user_info", ""), User.class);
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        // 원형 프로필
        Glide.with(this).load(user.getImgUrl()).into(imgProfile);
    }

    private static class ConnectionThread extends Thread {
        @Override
        public void run() {
            synchronized (this){
                getUserTicketsFromServer();
                Log.d("MyTicketsListActivity", "connection thread end");
                notify();
            }
        }

        private void getUserTicketsFromServer() {
            UserTicketApi ticketService = UserTicketApi.retrofit.create(UserTicketApi.class);
            String phoneNumber = userSP.getString("phone_number", "");

            if(phoneNumber.equals("")){
                Log.e("MyTicketListActivity - getUserTicketsFromServer()", "no user phone number");
                return;
            }

            ticketService.getUserTicketList(phoneNumber).enqueue(new Callback<Ticket[]>() {
                @Override
                public void onResponse(Call<Ticket[]> call, Response<Ticket[]> response) {
                    Ticket[] ticketListFromServer = response.body();

                    // 서버에 예매 내역이 없으면 종료
                    if(ticketListFromServer==null || ticketListFromServer.length==0){
                        Log.e("MyTicketListActivity - getUserTicketList", "ticket list from server doesn't exist");
                        return;
                    }
                    Log.d("MyTicketListActivity - getUserTicketList", "onResponse()");

                    // 서버에 예매 내역이 있으면 추가함
                    ArrayList<ReservedTicket> finalTicketListFromPS = new ArrayList<>();
                    for (Ticket ticket : ticketListFromServer) {
                        finalTicketListFromPS.add(new ReservedTicket(ticket));
                    }

                    // final을 SP에 담음
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = userSP.edit();
                    editor.putString("user_tickets", gson.toJson(finalTicketListFromPS));
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Ticket[]> call, Throwable t) {
                    Log.e("MyTicketListActivity - getUserTicketList", "onFailure: " + t.getMessage());
                }
            });
        }
    }
}