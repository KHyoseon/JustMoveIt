package com.example.justmoveit.activity;

import android.content.Intent;
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
import com.example.justmoveit.model.ReservedTicket;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.helper.SharedPreferencesCache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class MyTicketsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytickets_list);

        ImageView imgProfile = findViewById(R.id.profile_img);
        TextView userName = findViewById(R.id.user_name);
        TextView userEmail = findViewById(R.id.user_email);

        // 세션에서 사용자 정보 가져와서 profile_section setText
        SharedPreferencesCache cache = Session.getCurrentSession().getAppCache();
        userName.setText(cache.getString("user_name"));
        userEmail.setText(cache.getString("user_email"));
        // 원형 프로필
        Glide.with(this).load(cache.getString("user_img_url")).into(imgProfile);

        // 사용자가 예매한 티켓 모두 가져오기
        ArrayList<ReservedTicket> tickets = new ArrayList<>();
        // dump
        tickets.add(new ReservedTicket("미니언즈", "2022-05-22", "2022-06-22", "13:00", "5관", 2, 0, "E3, E4, E5"));
        tickets.add(new ReservedTicket("토르", "2022-06-01", "2022-09-23", "11:40", "6관", 1, 0, "D2"));
        tickets.add(new ReservedTicket("탑건", "2022-07-27", "2022-07-30", "16:10", "2관", 1, 0, "H12"));
        tickets.add(new ReservedTicket("헤어질 결심", "2022-06-30", "2022-07-02", "15:50", "3관", 1, 1, "H2, H3"));
        tickets.add(new ReservedTicket("외계+인", "2022-07-30", "2022-08-25", "15:50", "3관", 0, 1, "G2"));

        // 예매 이력이 없을 경우
        if (tickets.size() == 0) {
            Log.i("ticket list", "no ticket");
            // Todo: 예매 이력 없음 컴포넌트 만들기
        }

        for (ReservedTicket t : tickets) {
            // 현재 시간 기준으로 예매한 티켓과 만료된 티켓을 구분
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            Long nowPriority = Long.parseLong(simpleDateFormat.format(new Date()));
            Log.i(t.getTitle(), t.getPriority() + " vs " + nowPriority);
            if (t.getPriority() <= nowPriority) {
                t.setExpired(true);
            } else {
                t.setExpired(false);
            }
            Log.i(t.getTitle(), t.isExpired() ? "expired" : "not expired");
        }

        // 상영일 내림차 순으로 정렬 (최근 것이 위에 오도록)
        Collections.sort(tickets);

        // 예매 티켓 어댑트
        TicketListAdapter adapter = new TicketListAdapter(this, tickets);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        // 일반 예매 티켓 클릭하면 상세 페이지로 이동
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.TicketInfoActivity");
                it.putExtra("ticket", tickets.get(position));
                startActivity(it);
            }
        });

        // 로그아웃
        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        SharedPreferencesCache cache = Session.getCurrentSession().getAppCache();
                        cache.remove("user_name");
                        cache.remove("user_img_url");
                        cache.remove("user_email");
                        cache.remove("user_age_range");
                        cache.remove("user_gender");
                        finish(); // 현재 액티비티 종료 -> 메인으로 돌아감
                    }
                });
            }
        });

    }
}