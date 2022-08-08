package com.example.justmoveit.api;

import com.example.justmoveit.model.Ticket;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserTicketApi {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // 티켓 예매 내역 가져오기
    @GET("/justmoveit/tickets/{phoneNumber}")
    Call<Ticket[]> getUserTicketList(@Path("phoneNumber") String phoneNumber);

    // 티켓 예매하기
    @POST("/justmoveit/tickets")
    Call<Ticket> reserveTicket(@Body Ticket ticket);

    // 티켓 취소
    @DELETE("/justmoveit/tickets/{id}")
    Call<Void> cancelTicket(@Path("id") Long id);
}
