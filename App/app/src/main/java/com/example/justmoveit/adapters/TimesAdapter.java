package com.example.justmoveit.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justmoveit.R;
import com.example.justmoveit.activity.MovieInfoActivity;
import com.example.justmoveit.activity.TicketingActivity;
import com.example.justmoveit.model.Movie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.TimeViewHolder> {
    Movie movie;
    private List<String> times;
    Context context;

    public void setTimes(List<String> times, Movie movie) {
        this.movie = movie;
        this.times = times;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimesAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimesAdapter.TimeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_time,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public void onBindViewHolder(@NonNull TimesAdapter.TimeViewHolder holder, int position) {
        int index = position % times.size();

        holder.setTime(times.get(index));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String now = simpleDateFormat.format(new Date());
        if(times.get(index).compareTo(now) < 0){
            holder.itemView.setEnabled(false);
            holder.textTime.setTextColor(R.color.black);
            holder.relativeLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("darkgray")));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //클릭 이벤트
                Intent intent = new Intent(context.getApplicationContext(), TicketingActivity.class);
                intent.putExtra("movieId", movie.getMoviePlayingInfoByIndex(index).getMovieId()+"");
                intent.putExtra("moviePlayingInfoId", movie.getMoviePlayingInfoByIndex(index).getMoviePlayingInfoId()+"");
                MovieInfoActivity.launcher.launch(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    static class TimeViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTime;
        private final RelativeLayout relativeLayout;

        public TimeViewHolder(@NonNull View view) {
            super(view);
            textTime = view.findViewById(R.id.textTime);
            relativeLayout = view.findViewById(R.id.background_color);
        }

        void setTime(String time) {
            textTime.setText(time);
        }
    }
}
