package com.example.justmoveit.mytickets;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;

public class TicketInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_done);

        Intent intent = getIntent();
        ReservedTicketItem ticket = (ReservedTicketItem) intent.getExtras().getSerializable("ticket");

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
        TextView spectators = findViewById(R.id.spectators);
        spectators.setText((ticket.getAdult()>0?"성인 "+ticket.getAdult()+"명":"").concat(ticket.getTeen()>0?"청소년 "+ticket.getTeen()+"명":""));
        TextView reserveSeat = findViewById(R.id.reserve_seat);
        reserveSeat.setText(ticket.getTitle());

    }
}
