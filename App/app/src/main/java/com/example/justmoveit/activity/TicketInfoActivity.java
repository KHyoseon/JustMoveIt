package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.MainActivity.userSP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_done);

        Intent intent = getIntent();
        ReservedTicket reservedTicket = (ReservedTicket) intent.getExtras().getSerializable("ticket");

        if(reservedTicket.isExpired()){
            ImageView ticketBg = findViewById(R.id.ticket_bg);
            ticketBg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a5a5aa")));
        }

        Ticket ticket = reservedTicket.getTicket();
        TextView movieTitle = findViewById(R.id.movie_title);
        movieTitle.setText(ticket.getMovieTitle());
        TextView reserveDate = findViewById(R.id.reserve_date);
        reserveDate.setText(ticket.getReservationTime().substring(0, ticket.getReservationTime().length()-2));
        TextView viewingDate = findViewById(R.id.viewing_date);
        viewingDate.setText(ticket.getStartTime());
        TextView classification = findViewById(R.id.classification);
        classification.setText((reservedTicket.getAdult()>0?"성인 "+reservedTicket.getAdult()+"명  ":"")
                .concat(reservedTicket.getChild()>0?"어린이 "+reservedTicket.getChild()+"명":""));
        TextView reserveSeat = findViewById(R.id.reserve_seat);
        reserveSeat.setText(ticket.getSeat());


        TextView reserveCancel = findViewById(R.id.reserve_cancel_button);
        reserveCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Todo: 서버 취소 요청
                UserTicketApi service = UserTicketApi.retrofit.create(UserTicketApi.class);
                service.cancelTicket(ticket.getTicketId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        SharedPreferences.Editor editor = userSP.edit();
                        // SP에서 사용자 예매 정보 불러옴
                        Gson gson = new Gson();
                        String json = userSP.getString("user_tickets", "");
                        ArrayList<ReservedTicket> reservedTickets = gson.fromJson(json, TypeToken.getParameterized(List.class, Ticket.class).getType());
                        // id에 해당하는 티켓 예매 정보 삭제
                        for(int i=0, n=reservedTickets.size(); i<n; i++){
                            if(Objects.equals(reservedTickets.get(i).getTicket().getTicketId(), ticket.getTicketId())){
                                reservedTickets.remove(i);
                                break;
                            }
                        }
                        // 다시 삽입
                        editor.putString("user_tickets", gson.toJson(reservedTickets));
                        editor.apply();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("canceling ticket reservation failed", t.getMessage());
                    }
                });
            }
        });

    }
}
