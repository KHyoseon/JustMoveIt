package com.example.justmoveit.fragment;

import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.justmoveit.R;
import com.example.justmoveit.activity.MyTicketListActivity;
import com.example.justmoveit.adapters.TicketListAdapter;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class TicketListFragment extends Fragment {
    private MyTicketListActivity activity;
    private ViewGroup rootView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MyTicketListActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ticket_list, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ticketList fragment", "onResume()");

        Handler handler = new Handler();
        handler.postDelayed(this::adaptTicketList, 100);
    }

    private void adaptTicketList(){
        Gson gson = new Gson();
        String json = userSP.getString("user_tickets", "");
        ArrayList<ReservedTicket> ticketListFromSP
                = gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, ReservedTicket.class).getType());

        if(ticketListFromSP == null){
            Log.e("TicketListFragment - adaptTicketList()", "ticketListFromSP is null");
            return;
        }

        // 예매 티켓 리스트 가공 (만료 여부, 정렬)
        processingTicketList(ticketListFromSP);

        // 예매 티켓 어댑트
        Log.d("adapter", "티켓 어댑트");
        TicketListAdapter adapter = new TicketListAdapter(activity, ticketListFromSP);
        ListView list = rootView.findViewById(R.id.list);
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

    private void processingTicketList(ArrayList<ReservedTicket> ticketListFromSP) {
        // 티켓 리스트 데이터 가공
        for (ReservedTicket reservedTicket: ticketListFromSP) {
            // 현재 시간 기준으로 예매한 티켓과 만료된 티켓을 구분
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String nowPriority = simpleDateFormat.format(new Date());
            reservedTicket.setExpired(reservedTicket.isPassedNow(nowPriority));
        }
        // 상영일 내림차 순으로 정렬 (최근 것이 위에 오도록)
        Collections.sort(ticketListFromSP, Collections.reverseOrder());
    }
}
