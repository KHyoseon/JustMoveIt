package com.example.justmoveit.api;

import com.example.justmoveit.model.Movie;
import com.google.gson.JsonArray;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieApi {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)   // 10초동안 커넥션했는데 반응이 없으면 종료
            .readTimeout(10, TimeUnit.SECONDS)      // 보내고 쓰는거 10초로 제한
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)                // 실패했을 때 다시 시도할 것인가
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://i7d207.p.ssafy.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    // 상영 중인 전체 영화 목록
    @GET("/movies")
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
    */
    /* return
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
