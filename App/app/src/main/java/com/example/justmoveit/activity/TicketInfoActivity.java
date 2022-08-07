package com.example.justmoveit.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.model.ReservedTicket;

public class TicketInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_done);

        Intent intent = getIntent();
        ReservedTicket ticket = (ReservedTicket) intent.getExtras().getSerializable("ticket");

        if(ticket.isExpired()){
            ImageView ticketBg = findViewById(R.id.ticket_bg);
            ticketBg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a5a5aa")));
        }

        TextView movieTitle = findViewById(R.id.movie_title);
        movieTitle.setText(ticket.getTitle());
        TextView reserveDate = findViewById(R.id.reserve_date);
        reserveDate.setText(ticket.getViewingDate());
        TextView viewingDate = findViewById(R.id.viewing_date);
        viewingDate.setText(ticket.getViewingTime());
        TextView classification = findViewById(R.id.classification);
        classification.setText((ticket.getAdult()>0?"성인 "+ticket.getAdult()+"명  ":"")
                .concat(ticket.getChild()>0?"어린이 "+ticket.getChild()+"명":"")
                .concat(ticket.getDisabled()>0?"장애인 "+ticket.getDisabled()+"명":""));
        TextView reserveSeat = findViewById(R.id.reserve_seat);
        reserveSeat.setText(ticket.getSeat());


        TextView reserveCancel = findViewById(R.id.reserve_cancel_button);
        reserveCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Todo: 서버 취소 요청
            }
        });

    }
}
