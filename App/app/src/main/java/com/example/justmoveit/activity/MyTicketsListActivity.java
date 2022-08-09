package com.example.justmoveit.activity;
import static com.example.justmoveit.activity.MainActivity.movieSP;
import static com.example.justmoveit.activity.MainActivity.userSP;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.justmoveit.R;
import com.example.justmoveit.adapters.TicketListAdapter;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.example.justmoveit.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTicketsListActivity extends AppCompatActivity {
    ImageView imgProfile;
    TextView userName, userEmail;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytickets_list);

        imgProfile = findViewById(R.id.profile_img);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);

        // 세션에서 사용자 정보 가져와서 profile_section setText
        Gson gson = new Gson();
        User user =  gson.fromJson(userSP.getString("user_info", ""), User.class);
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        // 원형 프로필
        Glide.with(this).load(user.getImgUrl()).into(imgProfile);

        // 로그아웃
        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        editor = userSP.edit();
                        editor.remove("user_info");
                        editor.remove("user_tickets");
                        editor.apply();
                        finish(); // 현재 액티비티 종료 -> 메인으로 돌아감
                    }
                });
            }
        });

        // 서버에서 예매 이력 가져오기
        ConnectionThread thread = new ConnectionThread();
        Log.d("MyTicketsListActivity", "connection thread start");
        thread.start();
        synchronized (thread){
            try {
                Log.d("MyTicketsListActivity", "main thread waiting");
                thread.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        ArrayList<ReservedTicket> ticketListFromSP = gson.fromJson(userSP.getString("user_tickets", ""),
                TypeToken.getParameterized(ArrayList.class, ReservedTicket.class, Ticket.class).getType());

        // 티켓 리스트 데이터 가공
        for (ReservedTicket reservedTicket: ticketListFromSP) {
            // 현재 시간 기준으로 예매한 티켓과 만료된 티켓을 구분
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String nowPriority = simpleDateFormat.format(new Date());

            reservedTicket.setExpired(reservedTicket.isPassedNow(nowPriority));
        }

        // 상영일 내림차 순으로 정렬 (최근 것이 위에 오도록)
        Collections.sort(ticketListFromSP);

        // 예매 티켓 어댑트
        Log.e("adapter", "티켓 어댑트");
        TicketListAdapter adapter = new TicketListAdapter(this, ticketListFromSP);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        // 일반 예매 티켓 클릭하면 상세 페이지로 이동
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.TicketInfoActivity");
                it.putExtra("ticket", ticketListFromSP.get(position));
                startActivity(it);
            }
        });

    }

    static class ConnectionThread extends Thread {
        @Override
        public void run() {
            synchronized (this){
                getUserTicketsFromServer();
                notify();
            }
        }

        private void getUserTicketsFromServer() {
            UserTicketApi ticketService = UserTicketApi.retrofit.create(UserTicketApi.class);

            ArrayList<ReservedTicket> finalTicketListFromPS = new ArrayList<>();

            ticketService.getUserTicketList(userSP.getString("phone_number", "")).enqueue(new Callback<Ticket[]>() {
                @Override
                public void onResponse(Call<Ticket[]> call, Response<Ticket[]> response) {
                    Log.d("ConnectionThread - getUserTicketList", "onResponse");
                    Ticket[] ticketListFromServer = response.body();

                    SharedPreferences.Editor editor = userSP.edit();
                    // 서버에 예매 이력이 없으면 종료
                    if(ticketListFromServer==null){
                        Log.e("getUserTicketsFromServer", "ticket list from server doesn't exist");
                    } else {
                        // 서버에 이력이 있으면 추가함
                        for (Ticket ticket : ticketListFromServer) {
                            finalTicketListFromPS.add(new ReservedTicket(ticket));
                        }
                    }
                    // final을 SP에 담음
                    Gson gson = new Gson();
                    editor.remove("user_tickets");
                    editor.putString("user_tickets", gson.toJson(finalTicketListFromPS));
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Ticket[]> call, Throwable t) {
                    Log.e("ConnectionThread - getUserTicketList", "onFailure");
                }
            });
        }
    }
}