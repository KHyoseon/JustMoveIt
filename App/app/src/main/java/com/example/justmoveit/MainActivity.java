package com.example.justmoveit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        

        setupMoviesViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile){
            Toast.makeText(this,"Clicked on profile",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
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
            passengers.name="탑건: 매버릭";
            passengers.category = "Science Fiction";
            passengers.releaseDate = "2022-06-29";
            passengers.rating = 2.9f;
            movies.add(passengers);

            Movie theTomorrowWar = new Movie();
            theTomorrowWar.img = "https://movie-phinf.pstatic.net/20220516_144/1652665409592Chvey_JPEG/movie_image.jpg";
            theTomorrowWar.name="범죄도시2";
            theTomorrowWar.category = "Science Fiction";
            theTomorrowWar.releaseDate = "2022-06-15";
            theTomorrowWar.rating = 3.6f;
            movies.add(theTomorrowWar);

            Movie annihilation = new Movie();
            annihilation.img = "https://movie-phinf.pstatic.net/20220607_129/16545872892918GA4h_JPEG/movie_image.jpg";
            annihilation.name="헤어질 결심";
            annihilation.category = "Science Fiction";
            annihilation.releaseDate = "2022-07-06";
            annihilation.rating = 4.6f;
            movies.add(annihilation);

            return movies;
        }

}