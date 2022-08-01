package com.example.justmoveit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.justmoveit.R;
import com.example.justmoveit.model.Movie;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private final List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_movie,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        int index = position % movies.size();

        holder.setMovie(movies.get(index));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ""+movies.get(index), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{




        private final RoundedImageView imagePoster;
        private final TextView textName,ratingBarNum;
        private  final RatingBar ratingBar;

        public MovieViewHolder(@NonNull View view) {
            super(view);

            imagePoster = view.findViewById(R.id.imagePoster);
            textName = view.findViewById(R.id.textName);

            ratingBar=view.findViewById(R.id.ratingBar);
            ratingBarNum = view.findViewById(R.id.ratingBarNum);

        }

        void setMovie(Movie movie){

            Picasso.get().load(movie.getImg1()).into(imagePoster);

            textName.setText(movie.getTitle());

            ratingBar.setRating(Float.parseFloat(movie.getRating())/2);
            ratingBarNum.setText((movie.getRating()));
        }


    }

}
