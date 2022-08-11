package com.example.justmoveit.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.justmoveit.R;
import com.example.justmoveit.api.MovieApi;
import com.example.justmoveit.model.Movie;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends Activity {
    public static SharedPreferences movieSP, userSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        movieSP = getSharedPreferences("movieInfo", MODE_PRIVATE);
        userSP = getSharedPreferences("userInfo", MODE_PRIVATE);

        // 서버 통신 스레드
//        ConnectionThread thread = new ConnectionThread();
//        Log.d("LoadingActivity", "connection thread start");
//        thread.start();
//        synchronized (thread){
//            try {
//                Log.d("LoadingActivity", "main thread waiting");
//                thread.wait();
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                // 서버 통신 스레드
                finish();
            }
        }, 2000);
    }

    private static class ConnectionThread extends Thread {
        @Override
        public void run() {
            synchronized (this){
                // 메인 스레드 멈추고 실행할 부분
                getMoviesFromServer();
                Log.d("LoadingActivity", "connection thread end");
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                    int i=0;
                    for(Movie movie: movies) {
                        if(i == 10)   break;
                        editor.putString(movie.getMoviePlayingInfoByIndex(0).getMovieId()+"", gson.toJson(movie));
                        Log.i("movieSP", movie.getMoviePlayingInfoByIndex(0).getMovieId()+"" + " 삽입");
                        ++i;
                    }
                    editor.apply();
                    Log.i("movieSP", "모든 영화 삽입 완료");
                }

                @Override
                public void onFailure(Call<Movie[]> call, Throwable t) {
                    Log.e("LoadingActivity - getMoviesFromServer", "onFailure(): " + t.getMessage());
                }
            });
        }
    }
}