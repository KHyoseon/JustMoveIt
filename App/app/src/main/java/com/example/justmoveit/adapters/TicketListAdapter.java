package com.example.justmoveit.adapters;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.justmoveit.R;
import com.example.justmoveit.model.ReservedTicket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

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

        ReservedTicket ticket = (ReservedTicket)getItem(position);

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
