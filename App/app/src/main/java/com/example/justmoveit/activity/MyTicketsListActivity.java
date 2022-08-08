package com.example.justmoveit.activity;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.justmoveit.R;
import com.example.justmoveit.adapters.TicketListAdapter;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
        userName.setText(userSP.getString("user_name", ""));
        userEmail.setText(userSP.getString("user_email", ""));
        // 원형 프로필
        Glide.with(this).load(userSP.getString("user_img_url", "")).into(imgProfile);

        editor = userSP.edit();

        // 로그아웃
        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        editor.remove("user_name");
                        editor.remove("user_img_url");
                        editor.remove("user_email");
                        editor.remove("user_age_range");
                        editor.remove("user_gender");
                        editor.apply();
                        finish(); // 현재 액티비티 종료 -> 메인으로 돌아감
                    }
                });
            }
        });

    }


    @Override
    public void onResume(){
        super.onResume();
        // And put the SharedPreferences test here

        // 사용자가 예매한 티켓 모두 가져오기
        // 1. userSP 저장된 데이터 불러오기
        ArrayList<ReservedTicket> ticketListFromSP = new ArrayList<>();
        editor = userSP.edit();
        Gson gson = new Gson();

//        editor.remove("user_tickets");
//        editor.apply();

        String json = userSP.getString("user_tickets", "");
        // 저장된 게 있으면 바로 담고 끝
        if(!json.equals("")){
            Log.e("userSP", "있음");
            ticketListFromSP = gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, ReservedTicket.class).getType());
        } else {
            Log.e("userSP", "없음");
            // 2. 서버에서 가져오기
            UserTicketApi ticketService = UserTicketApi.retrofit.create(UserTicketApi.class);
            ArrayList<ReservedTicket> finalTicketListFromPS = ticketListFromSP;
            // Todo: 서버 통신
            ticketService.getUserTicketList("01012345678").enqueue(new Callback<Ticket[]>() {
                @Override
                public void onResponse(Call<Ticket[]> call, Response<Ticket[]> response) {
                    Log.e("ticketService", "통신 성공");

                    Ticket[] ticketListFromServer = response.body();
                    // 서버에 예매 이력이 없으면 종료
                    if(ticketListFromServer==null){
                        Log.e("ticket list from server", "is not exist");
                    } else {
                        // 서버에 이력이 있으면 추가함
                        for (Ticket t : ticketListFromServer) {
                            finalTicketListFromPS.add(new ReservedTicket(t));
                        }
                    }
                    // final을 SP에 담음
                    editor.putString("user_tickets", gson.toJson(finalTicketListFromPS));
                    Log.e(">>>> MyTicketsList", "서버 통신 ticketService- getUserTicketList");
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Ticket[]> call, Throwable t) {
                    Log.e("ticketService", "통신 실패");
                    Toast.makeText(MyTicketsListActivity.this, "ticketServic - getUserTicketList: onFailure", Toast.LENGTH_SHORT).show();
                    Log.e("ticketServic - getUserTicketList", "onFailure");
                    /*ArrayList<Ticket> t1 = new ArrayList<>();
                    t1.add(new Ticket(1L, 1L, 1L, "미니언즈", "전체 연령가", "13:00", "15:10",
                            "01012345678", "ADULT,ADULT,ADULT", "20220622", "E3, E4, E5", 1, 12900));
                    t1.add(new Ticket(2L, 2L, 2L, "토르", "12세 이상", "11:40", "13:00",
                            "01012345678", "ADULT,ADULT,CHILD", "20220923", "E3, E4, E5", 2, 12900));
                    t1.add(new Ticket(3L, 3L, 3L, "탑건", "12세 이상", "16:10", "17:50",
                            "01012345678", "ADULT,ADULT,CHILD", "20220730", "E3, E4, E5", 3, 12900));
                    t1.add(new Ticket(4L, 4L, 4L, "헤어질 결심", "15세 이상", "15:50", "16:10",
                            "01012345678", "ADULT,CHILD,CHILD", "20220702", "E3, E4, E5", 4, 12900));
                    t1.add(new Ticket(5L, 5L, 5L, "외계+인", "12세 이상", "15:50", "16:30",
                            "01012345678", "CHILD,CHILD,CHILD", "20220825", "E3, E4, E5", 5, 12900));

                    for(Ticket t2: t1){
                        finalTicketListFromPS.add(new ReservedTicket(t2));
                    }
                    // final을 SP에 담음
                    editor.putString("mytickets", gson.toJson(finalTicketListFromPS));
                    editor.apply();*/
                }
            });
        }

        // 티켓 리스트 데이터 가공
        if(ticketListFromSP != null) {
            for (int i = 0, n = ticketListFromSP.size(); i < n; i++) {
                // 현재 시간 기준으로 예매한 티켓과 만료된 티켓을 구분
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                String nowPriority = simpleDateFormat.format(new Date());

                Log.e("new Ticket's priority", ticketListFromSP.get(i).getPriority()+"");

                Log.i(ticketListFromSP.get(i).getTitle(), ticketListFromSP.get(i).getPriority() + " vs " + nowPriority);
                if (ticketListFromSP.get(i).isGreaterThan(nowPriority)) {
                    ticketListFromSP.get(i).setExpired(true);
                } else {
                    ticketListFromSP.get(i).setExpired(false);
                }
                Log.i(ticketListFromSP.get(i).getTitle(), ticketListFromSP.get(i).isExpired() ? "expired" : "not expired");
            }
        }

        // 상영일 내림차 순으로 정렬 (최근 것이 위에 오도록)
        Collections.sort(ticketListFromSP);

        // 예매 티켓 어댑트
        Log.e("adapter", "티켓 어댑트");
        TicketListAdapter adapter = new TicketListAdapter(this, ticketListFromSP);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        // 일반 예매 티켓 클릭하면 상세 페이지로 이동
        ArrayList<ReservedTicket> finalTickets = ticketListFromSP;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.TicketInfoActivity");
                it.putExtra("ticket", finalTickets.get(position));
                startActivity(it);
            }
        });
    }
}