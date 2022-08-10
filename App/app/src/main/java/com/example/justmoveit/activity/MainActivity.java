package com.example.justmoveit.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import com.example.justmoveit.fragment.BlankFragment;
import com.example.justmoveit.fragment.ViewPagerFragment;
import com.example.justmoveit.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakao.auth.Session;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static SharedPreferences movieSP, userSP;

    private ViewPagerFragment viewPagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieSP = getSharedPreferences("movieInfo", MODE_PRIVATE);
        userSP = getSharedPreferences("userInfo", MODE_PRIVATE);

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

        setContentView(R.layout.activity_main);

        // 툴바 등록
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        viewPagerFragment = (ViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.VP_fragment);
        BlankFragment blankFragment = new BlankFragment("상영 중인 영화가 없습니다.");

        // 서버에서 받아왔는데 아무것도 없으면 빈 프래그먼트로 교체
        if(movieSP.getString("movie_list", "").equals("")){
            getSupportFragmentManager().beginTransaction().replace(R.id.VP_container, blankFragment).commit();
        }

        Log.d("MainActivity", "view Pager start");
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
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.MyTicketListActivity");
            } else {
//                Toast.makeText(this, "CurrentSession is closed", Toast.LENGTH_SHORT).show();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.LoginActivity");
            }
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
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
            SharedPreferences.Editor editor = MainActivity.movieSP.edit();

//            editor.remove("movie_list");
//            editor.apply();

            MovieApi service = MovieApi.retrofit.create(MovieApi.class);
            service.getMovieList().enqueue(new Callback<Movie[]>() {
                @Override
                public void onResponse(Call<Movie[]> call, Response<Movie[]> response) {
                    Movie[] movies = response.body();
                    Log.d("MainActivity - getMoviesFromServer", "onResponse()");
                    if(movies == null){
                        Log.e("MainActivity - getMoviesFromServer", "onResponse(): " + response.message());
                        return;
                    }

                    // SP에 저장
                    Gson gson = new Gson();
                    editor.putString("movie_list", gson.toJson(Arrays.asList(movies)));
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Movie[]> call, Throwable t) {
                    Log.e("MainActivity - getMoviesFromServer", "onFailure(): " + t.getMessage());
                }
            });
        }
    }

}