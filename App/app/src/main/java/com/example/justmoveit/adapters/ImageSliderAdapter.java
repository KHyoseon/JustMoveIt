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

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.UrlViewHolder>{

    private final List<String> url;

    public ImageSliderAdapter(List<String> url) {
        this.url = url;
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UrlViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_slider_image,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {

        int index = position % url.size();

        holder.setUrl(url.get(index));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), ""+movies.get(index), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class UrlViewHolder extends RecyclerView.ViewHolder{




        private final RoundedImageView moviePhoto;

        public UrlViewHolder(@NonNull View view) {
            super(view);

            moviePhoto = view.findViewById(R.id.moviePhoto);

        }

        void setUrl(String url){

            Picasso.get().load(url).into(moviePhoto);

        }


    }

}
