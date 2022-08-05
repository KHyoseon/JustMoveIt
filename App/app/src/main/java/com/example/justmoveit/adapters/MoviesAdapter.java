package com.example.justmoveit.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justmoveit.R;
import com.example.justmoveit.activity.DetailActivity;
import com.example.justmoveit.model.Movie;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private final List<Movie> movieList;
    Context context;

    public MoviesAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_container_movie, parent, false
            )
        );
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        int index = position % movieList.size();

        holder.setMovie(movieList.get(index));
        // 홀더 아이템 클릭시 영화 상세 페이지로 이동
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
                intent.putExtra("movie", movieList.get(index));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imagePoster;
        private final TextView textName, ratingBarNum;
        private final RatingBar ratingBar;

        // 레이아웃과 연결
        public MovieViewHolder(@NonNull View view) {
            super(view);
            imagePoster = view.findViewById(R.id.imagePoster);
            textName = view.findViewById(R.id.textName);
            ratingBar = view.findViewById(R.id.ratingBar);
            ratingBarNum = view.findViewById(R.id.ratingBarNum);
        }

        // 값 적용
        void setMovie(Movie movie) {
            Picasso.get().load(movie.getMainImg()).into(imagePoster);
            textName.setText(movie.getTitle());
            ratingBar.setRating(Float.parseFloat(movie.getRating()) / 2);
            ratingBarNum.setText((movie.getRating()));
        }
    }

}
