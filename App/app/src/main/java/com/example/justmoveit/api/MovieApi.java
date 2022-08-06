package com.example.justmoveit.api;

import com.example.justmoveit.model.Movie;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieApi {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // 상영 중인 전체 영화 목록
    @GET("/justmoveit/movies")
    Call<Movie[]> getMovieList();
    /*
    {
        "actor": "string",
        "ageLimit": "string",
        "country": "string",
        "director": "string",
        "engTitle": "string",
        "genre": "string",
        "img": "string",
        "img2": "string",
        "img3": "string",
        "img4": "string",
        "img5": "string",
        "img6": "string",
        "movieCode": "string",
        "moviePlayingInfoList": [
          {
            "ageLimit": "string",
            "endTime": "string",
            "movieId": 0,
            "movieTitle": "string",
            "startTime": "string",
            "theaterNo": "string",
            "tickets": [
              {
                "ageLimit": "string",
                "classification": "ADULT",
                "endTime": "string",
                "movieId": 0,
                "moviePlayingInfoId": 0,
                "movieTitle": "string",
                "phoneNumber": "string",
                "reservationTime": "2022-08-05T16:54:22.626Z",
                "seat": "string",
                "startTime": "string"
              }
            ]
          }
        ],
        "rating": "string",
        "releaseDate": "string",
        "runningTime": "string",
        "summary": "string",
        "title": "string",
        "totalCustomer": "string"
      }
     */

    /*// 영화 상영 정보
    @GET("/justmoveit/info")
    Call<JsonArray> getMoviePlayingInfo();
    *//* return
    {
        "ageLimit": "string",
        "endTime": "string",
        "movieId": 0,
        "movieTitle": "string",
        "startTime": "string",
        "theaterNo": "string",
        "tickets": [
          {
            "ageLimit": "string",
            "classification": "ADULT",
            "endTime": "string",
            "movieId": 0,
            "moviePlayingInfoId": 0,
            "movieTitle": "string",
            "phoneNumber": "string",
            "reservationTime": "2022-08-05T16:54:47.561Z",
            "seat": "string",
            "startTime": "string"
          }
        ]
      }
     *//*
*/
}
