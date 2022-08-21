package com.example.justmoveit.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.justmoveit.R;
import com.example.justmoveit.api.MovieApi;
import com.example.justmoveit.model.Movie;
import com.google.gson.Gson;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends Activity {
    public static SharedPreferences movieSP, userSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Log.i("LoadingActivity", "onCreate");

        movieSP = getSharedPreferences("movieInfo", MODE_PRIVATE);
        userSP = getSharedPreferences("userInfo", MODE_PRIVATE);

        // 서버 통신 스레드
        ConnectionThread thread = new ConnectionThread();
        Log.d("LoadingActivity", "connection thread start");
        thread.start();
        /*try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        synchronized (thread){
            try {
                Log.d("LoadingActivity", "main thread waiting");
                thread.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

//        Handler handler = new Handler();
//        handler.postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            Log.i("LoadingActivity", "move to mainActivity");
            startActivity(intent);
            finish();
//        }, 1000);
    }

    private static class ConnectionThread extends Thread {
        @Override
        public void run() {
            synchronized (this){
                // 메인 스레드 멈추고 실행할 부분
                getMoviesFromServer();
                Log.d("LoadingActivity", "connection thread end");
                notify();
            }
        }

        private void getMoviesFromServer() {
            SharedPreferences.Editor editor = LoadingActivity.movieSP.edit();

            MovieApi service = MovieApi.retrofit.create(MovieApi.class);
            service.getMovieList().enqueue(new Callback<Movie[]>() {
                @Override
                public void onResponse(Call<Movie[]> call, Response<Movie[]> response) {
                    Movie[] movies = response.body();
                    Log.d("LoadingActivity - getMoviesFromServer", "onResponse()");
                    if(movies == null){
                        Log.e("LoadingActivity - getMoviesFromServer", "onResponse(): " + response.message());
                        return;
                    }

                    // SP에 저장
                    Gson gson = new Gson();

                    for(Movie m: movies){
                        editor.putString(m.getMovieId(), gson.toJson(m));
                    }
                    editor.apply();
                    Log.d("movieSP", "모든 영화 삽입 완료");
                }

                @Override
                public void onFailure(Call<Movie[]> call, Throwable t) {
                    Log.e("LoadingActivity - getMoviesFromServer", "onFailure(): " + t.getMessage());
                }
            });
        }
    }
}