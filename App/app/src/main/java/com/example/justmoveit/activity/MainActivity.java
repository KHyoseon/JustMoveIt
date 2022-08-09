package com.example.justmoveit.activity;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.justmoveit.R;
import com.example.justmoveit.adapters.MoviesAdapter;
import com.example.justmoveit.api.MovieApi;
import com.example.justmoveit.model.Movie;
import com.example.justmoveit.model.MoviePlayingInfo;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.kakao.auth.Session;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static SharedPreferences movieSP, userSP;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieSP = getSharedPreferences("movieInfo", MODE_PRIVATE);
        userSP = getSharedPreferences("userInfo", MODE_PRIVATE);

        // 툴바 등록
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // 서버 통신 스레드
        ConnectionThread thread = new ConnectionThread();
        Log.d("MainActivity", "connection thread start");
        thread.start();
        synchronized (thread){
            try {
                Log.d("MainActivity", "main thread waiting");
                thread.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        Log.d("MainActivity", "view Pager start");
        setupMoviesViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 옵션 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it = new Intent();
        // 프로필 클릭시 로그인 페이지 or 티켓 예매 내역 페이지
        if (item.getItemId() == R.id.profile) {
            if (Session.getCurrentSession().isOpened()) {
//                Toast.makeText(this, "CurrentSession is opend ", Toast.LENGTH_SHORT).show();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.MyTicketsListActivity");
            } else {
//                Toast.makeText(this, "CurrentSession is closed", Toast.LENGTH_SHORT).show();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.LoginActivity");
            }
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupMoviesViewPager() {
        // 메인 페이지 - 영화 목록 (카드)
        ViewPager2 moviesViewPager = findViewById(R.id.moviesViewPager);
        moviesViewPager.setClipToPadding(false);
        moviesViewPager.setClipChildren(false);
        moviesViewPager.setOffscreenPageLimit(3);
        moviesViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        }));
        moviesViewPager.setPageTransformer(compositePageTransformer);

        // SP에 저장된 무비 리스트 가져와서 어댑트
        String json = movieSP.getString("movie_list", "");
        if (json.equals("")) {
            Log.e("setupMoviesViewPager", "json is null");
            return;
        }
        Gson gson = new Gson();
        List<Movie> movies = gson.fromJson(json, TypeToken.getParameterized(List.class, Movie.class).getType());
        if (movies == null) {
            Log.e("setupMoviesViewPager", "movies is null");
            return;
        }
        moviesViewPager.setAdapter(new MoviesAdapter(movies));
    }

    static class ConnectionThread extends Thread {
        @Override
        public void run() {
            synchronized (this){
                // 메인 스레드 멈추고 실행할 부분
                getMoviesFromServer();
                Log.d("MainActivity", "connection thread end");
                notify();
            }
        }

        private void getMoviesFromServer() {
            MovieApi service = MovieApi.retrofit.create(MovieApi.class);
            service.getMovieList().enqueue(new Callback<Movie[]>() {
                @Override
                public void onResponse(Call<Movie[]> call, Response<Movie[]> response) {
                    Movie[] movies = response.body();
                    if(movies == null){
                        Log.e("getMoviesFromServer", "there are no movies in db");
                        return;
                    }

                    // SP에 저장
                    SharedPreferences.Editor editor = MainActivity.movieSP.edit();
                    Gson gson = new Gson();
                    editor.putString("movie_list", gson.toJson(Arrays.asList(movies)));
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Movie[]> call, Throwable t) {
                    Log.e("get Movies from server failed", t.getMessage());
                }
            });
        }
    }

}