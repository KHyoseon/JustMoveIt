package com.example.justmoveit.api;

import com.example.justmoveit.model.kakaopay.PayApprove;
import com.example.justmoveit.model.kakaopay.PayReady;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentApi {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Headers ({"Authorization: KakaoAK 50572afa1934558863f2a3be50221aa2",
            "Content-Type:application/x-www-form-urlencoded;charset=utf-8"})
    @POST("v1/payment/ready")
    Call<PayReady> paymentReady(
            @Query("cid") String cid,
            @Query("partner_order_id") String partner_order_id,
            @Query("partner_user_id") String partner_user_id,
            @Query("item_name") String item_name,
            @Query("quantity") Integer quantity,
            @Query("total_amount") Integer total_amount,
            @Query("tax_free_amount") Integer tax_free_amount,
            @Query("approval_url") String approval_url,
            @Query("fail_url") String fail_url,
            @Query("cancel_url") String cancel_url
    );

    @Headers ({"Authorization: KakaoAK 50572afa1934558863f2a3be50221aa2",
            "Content-Type:application/x-www-form-urlencoded;charset=utf-8"})
    @POST("v1/payment/approve")
    Call<PayApprove> paymentApprove(
            @Query("cid") String cid,
            @Query("tid") String tid,
            @Query("partner_order_id") String partner_order_id,
            @Query("partner_user_id") String partner_user_id,
            @Query("pg_token") String pg_token,
            @Query("total_amount") Integer total_amount);
}
