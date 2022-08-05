package com.example.justmoveit.api;

import com.example.justmoveit.model.ReservedTicket;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserTicketApi {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /* 유저 목록 관련 */
    // 유저의 티켓 예매 리스트 가져오기
    @GET("/tickets/{phoneNumber}")
    Call<JsonArray> getUserTicketList(@Path("phoneNumber") String phoneNumber);

    // 티켓 예매하기
    @POST("/ticket")
    Call<Void> reserveTicket(@Body ReservedTicket ticket);
}
