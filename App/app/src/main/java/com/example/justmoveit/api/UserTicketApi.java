package com.example.justmoveit.api;

import com.example.justmoveit.model.ReservedTicket;
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
    Call<JsonArray> getUserTicketList(@Path("phoneNumber") String phoneNumber);
    /*
    {
        "ageLimit": "string",
        "classification": "ADULT",
        "endTime": "string",
        "movieId": 0,
        "moviePlayingInfoId": 0,
        "movieTitle": "string",
        "phoneNumber": "string",
        "reservationTime": "2022-08-05T16:58:18.462Z",
        "seat": "string",
        "startTime": "string"
      }
     */

    // 티켓 예매하기
    @POST("/justmoveit/tickets")
    Call<Void> reserveTicket(@Body ReservedTicket ticket);
    // Todo: classification 어떻게 바꾼다고 햇더라..? 가격 정보도 넣어야할 듯?
    /*
    body {
      "classification": "ADULT",
      "moviePlayingInfoId": 0,
      "phoneNumber": "string",
      "reservationTime": "2022-08-05T16:55:46.124Z",
      "seat": "string"
    }
     */

    // 티켓 취소
    // Todo: 티켓 예매하면 리턴 값으로 티켓 아이디 반환해줘야할 듯..?!
    @DELETE("/justmoveit/tickets/{id}")
    Call<Void> cancleTicket(@Path("id") int id);
}
