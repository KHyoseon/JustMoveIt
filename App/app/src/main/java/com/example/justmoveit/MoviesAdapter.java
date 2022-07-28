package com.example.justmoveit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



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
        private final TextView textName, textCategory, textReleaseDate;
        private  final RatingBar ratingBar;

        public MovieViewHolder(@NonNull View view) {
            super(view);

            imagePoster = view.findViewById(R.id.imagePoster);
            textName = view.findViewById(R.id.textName);
            textCategory = view.findViewById(R.id.textCategory);
            textReleaseDate=view.findViewById(R.id.textRealeaseDate);
            ratingBar=view.findViewById(R.id.ratingBar);


        }

        void setMovie(Movie movie){

            Picasso.get().load(movie.img).into(imagePoster);

            textName.setText(movie.name);
            textCategory.setText(movie.category);
            textReleaseDate.setText(movie.releaseDate);
            ratingBar.setRating(movie.rating);
        }


    }

}
