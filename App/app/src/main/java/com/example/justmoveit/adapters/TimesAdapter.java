package com.example.justmoveit.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justmoveit.R;
import com.example.justmoveit.activity.TicketingActivity;
import com.example.justmoveit.model.Movie;

import java.util.List;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.TimeViewHolder> {

    private List<String> times;
    Movie movie;

    Context context;


    public void setTimes(List<String> times,Movie movie){
        this.times=times;
        this.movie=movie;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //클릭 이벤트

                Intent intent = new Intent(context.getApplicationContext(), TicketingActivity.class);
                intent.putExtra("movie",movie);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return times.size();
    }
    static class TimeViewHolder extends RecyclerView.ViewHolder{




        private final TextView textTime;

        public TimeViewHolder(@NonNull View view) {
            super(view);
            textTime = view.findViewById(R.id.textTime);
        }

        void setTime(String time){

            textTime.setText(time);
        }


    }
}
