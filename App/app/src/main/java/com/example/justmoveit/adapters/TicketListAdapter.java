package com.example.justmoveit.adapters;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.justmoveit.R;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;

import java.util.ArrayList;

public class TicketListAdapter extends ArrayAdapter {

    public TicketListAdapter(@NonNull Activity context, ArrayList<ReservedTicket> tickets) {
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

        ReservedTicket reservedTicket = (ReservedTicket)getItem(position);
        Ticket ticket = reservedTicket.getTicket();

        title.setText(ticket.getMovieTitle());
        date.setText((ticket.getReservationTime().split(" "))[0]);
        time.setText(ticket.getStartTime());
        theater.setText(ticket.getTheaterNo()+"관");
        seat.setText(ticket.getSeat());

        if(reservedTicket.isExpired()){
            Log.i("background Tint", ticket.getMovieTitle());
            convertView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a5a5aa")));
        }

        return convertView;
    }
}
