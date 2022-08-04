package com.example.justmoveit.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRetrofit {
    // /justmoveit 생략되어있음


    /* example */
    // https://www.unsplash.com/search/photos?query="{searchTerm}"
    @GET("/search/photos")
    Call<JsonElement> searchPhotos(@Query("query") String searchTerm);

    /* 영화 목록 관련 */

    // 영화 목록 가져오기
//    @GET("/movies")
//    Call<> getMovieList();
//    // 영화 하나의 정보 가져오기
//    @GET("/movies/{id}")
//    Call<> getMovieInfo();


    /* 유저 목록 관련 */
    // 유저의 티켓 예매 리스트 가져오기
    @GET("/tickets/{phoneNumber}")
    Call<JsonArray> getUserTicketList(@Path("phoneNumber") String phoneNumber);

}
