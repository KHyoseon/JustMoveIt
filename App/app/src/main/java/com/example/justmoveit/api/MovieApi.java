package com.example.justmoveit.api;

import com.example.justmoveit.model.Movie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieApi {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /* 영화 목록 관련 */

    // 영화 목록 가져오기
    @GET("/movies")
    Call<Movie[]> getMovieList();

    // 영화 하나의 정보 가져오기
    @GET("/movies/{id}")
    Call<Movie> getMovieInfo();
}
