package com.example.justmoveit.api;

import com.example.justmoveit.model.Ticket;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserTicketApi {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)   // 10초동안 커넥션했는데 반응이 없으면 종료
            .readTimeout(10, TimeUnit.SECONDS)      // 보내고 쓰는거 10초로 제한
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)                // 실패했을 때 다시 시도할 것인가
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://i7d207.p.ssafy.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    // 티켓 예매 내역 가져오기
    @GET("/api/tickets/{phoneNumber}")
    Call<Ticket[]> getUserTicketList(@Path("phoneNumber") String phoneNumber);

    // 티켓 예매하기
    @Headers({"Content-Type:application/json;charset=utf-8"})
    @POST("/api/tickets")
    Call<Ticket> reserveTicket(@Body Ticket ticket);

    // 티켓 취소
    @DELETE("/api/tickets/{id}")
    Call<Void> cancelTicket(@Path("id") Long id);

}
