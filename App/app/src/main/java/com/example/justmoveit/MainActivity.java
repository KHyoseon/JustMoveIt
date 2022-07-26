package com.example.justmoveit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupMoviesViewPager();
    }
        private void setupMoviesViewPager(){

            ViewPager2 moviesViewPager = findViewById(R.id.moviesViewPager);
            moviesViewPager.setClipToPadding(false);
            moviesViewPager.setClipChildren(false);
            moviesViewPager.setOffscreenPageLimit(3);
            moviesViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(10));
            compositePageTransformer.addTransformer(((page, position) -> {
                float r = 1 -Math.abs(position);
                page.setScaleY(0.85f + r* 0.15f);
            }));
            moviesViewPager.setPageTransformer(compositePageTransformer);

            moviesViewPager.setAdapter(new MoviesAdapter(getMovies()));

        }
        private List<Movie> getMovies(){
            List<Movie> movies = new ArrayList<>();

            Movie passengers = new Movie();
            passengers.img = "https://movie-phinf.pstatic.net/20220509_176/1652081912471yhg3N_JPEG/movie_image.jpg";
            passengers.name="Passengers";
            passengers.category = "Science Fiction";
            passengers.releaseDate = "December 22, 2016";
            passengers.rating = 4.6f;
            movies.add(passengers);

            Movie theTomorrowWar = new Movie();
            theTomorrowWar.img = "https://movie-phinf.pstatic.net/20220516_144/1652665409592Chvey_JPEG/movie_image.jpg";
            theTomorrowWar.name="Passengers";
            theTomorrowWar.category = "Science Fiction";
            theTomorrowWar.releaseDate = "December 22, 2016";
            theTomorrowWar.rating = 4.6f;
            movies.add(theTomorrowWar);

            Movie annihilation = new Movie();
            annihilation.img = "https://movie-phinf.pstatic.net/20220607_129/16545872892918GA4h_JPEG/movie_image.jpg";
            annihilation.name="Passengers";
            annihilation.category = "Science Fiction";
            annihilation.releaseDate = "December 22, 2016";
            annihilation.rating = 4.6f;
            movies.add(annihilation);

            return movies;
        }

}