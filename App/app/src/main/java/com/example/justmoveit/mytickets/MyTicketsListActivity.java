package com.example.justmoveit.mytickets;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.justmoveit.R;
import com.example.justmoveit.login.User;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.api.UserApi;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.helper.SharedPreferencesCache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class MyTicketsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytickets_list);

        ImageView iv_profile = findViewById(R.id.profile_img);
        TextView tv_name = findViewById(R.id.user_name);
        TextView tv_email = findViewById(R.id.user_email);

        // 세션에서 사용자 정보 가져와서 profile_section setText
        SharedPreferencesCache cache = Session.getCurrentSession().getAppCache();
        tv_name.setText(cache.getString("user_name"));
        tv_email.setText(cache.getString("user_email"));
        // 원형 프로필
        Glide.with(this).load(cache.getString("user_img_url")).into(iv_profile);

        Toast.makeText(this, "gender: "+cache.getString("user_gender"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "age: "+cache.getString("user_age_range"), Toast.LENGTH_SHORT).show();

        // 사용자가 예매한 티켓 모두 가져오기
        ArrayList<ReservedTicketItem> tickets = new ArrayList<>();
        // dump
        tickets.add(new ReservedTicketItem("미니언즈", "2022-05-22", "2022-06-22", "13:00", "5관", 2, 0, "E3, E4, E5"));
        tickets.add(new ReservedTicketItem("토르", "2022-06-01", "2022-09-23", "11:40", "6관", 1, 0, "D2"));
        tickets.add(new ReservedTicketItem("탑건", "2022-07-27", "2022-07-30", "16:10", "2관", 1, 0, "H12"));
        tickets.add(new ReservedTicketItem("헤어질 결심", "2022-06-30", "2022-07-02", "15:50", "3관", 1, 1, "H2, H3"));
        tickets.add(new ReservedTicketItem("외계+인", "2022-07-30", "2022-08-25", "15:50", "3관", 0, 1, "G2"));

        // 예매 이력이 없을 경우
        if(tickets.size()==0){
            Log.i("ticket list", "no ticket");
            // Todo: 예매 이력 없음 컴포넌트 만들기
        }

        for(ReservedTicketItem t: tickets){
            // 현재 시간 기준으로 예매한 티켓과 만료된 티켓을 구분
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            Long nowPriority = Long.parseLong(simpleDateFormat.format(new Date()));
                Log.i(t.getTitle(), t.getPriority() + " vs " + nowPriority);
            if(t.getPriority() <= nowPriority) {
                t.setExpired(true);
            } else {
                t.setExpired(false);
            }
            Log.i(t.getTitle(), t.isExpired()?"expired":"not expired");
        }

        // 상영일 내림차 순으로 정렬 (최근 것이 위에 오도록)
        Collections.sort(tickets, new TicketComparator());

        // 예매 티켓 어댑트
        TicketList adapter = new TicketList(this, tickets);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        // 일반 예매 티켓 클릭하면 상세 페이지로 이동
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.mytickets.TicketInfoActivity");
                it.putExtra("ticket", tickets.get(position));
                startActivity(it);
            }
        });

        // 로그아웃
        TextView logout = (TextView) findViewById(R.id.logout);
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
                        // 로그아웃 성공시 수행
                        finish(); // 현재 액티비티 종료 -> 메인으로 돌아감
                    }
                });
            }
        });

    }

    static class TicketList extends ArrayAdapter {

        public TicketList(@NonNull Activity context, ArrayList<ReservedTicketItem> tickets) {
            super(context, R.layout.ticket_item, tickets);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ticket_item, parent, false);

            TextView title = convertView.findViewById(R.id.movie_title);
            TextView date = convertView.findViewById(R.id.movie_date);
            TextView time = convertView.findViewById(R.id.movie_time);
            TextView theater = convertView.findViewById(R.id.theater);
            TextView seat = convertView.findViewById(R.id.seat);

            ReservedTicketItem ticket = (ReservedTicketItem)getItem(position);

            title.setText(ticket.getTitle());
            date.setText(ticket.getViewingDate());
            time.setText(ticket.getViewingTime());
            theater.setText(ticket.getTheater());
            seat.setText(ticket.getSeat());

            if(ticket.isExpired()){
                Log.i("background Tint", ticket.getTitle());
                convertView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a5a5aa")));
            }

            return convertView;
        }
    }

    static class TicketComparator implements Comparator<ReservedTicketItem> {
        @Override
        public int compare(ReservedTicketItem t1, ReservedTicketItem t2) {
            if(Objects.equals(t2.getPriority(), t1.getPriority()))   return 0;
            return (t2.getPriority()>t1.getPriority()? 1 : -1);
        }
    }
}
