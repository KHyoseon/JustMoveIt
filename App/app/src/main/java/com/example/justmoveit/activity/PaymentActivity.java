package com.example.justmoveit.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.model.Ticket;
import com.example.justmoveit.model.kakaopay.PayApprove;
import com.example.justmoveit.model.kakaopay.PayReady;
import com.example.justmoveit.api.PaymentApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;

    private WebView webView;
    private Ticket ticket;

    private String tidPin; //결제 고유 번호
    private String pgToken; //결제 요청 토큰
    private static String PRODUCT_NAME; //상품 이름
    private static Integer PRODUCT_PRICE; //상품 가격

    public PaymentActivity() {
    }

    public PaymentActivity(Ticket ticket) {
        this.ticket = ticket;
        PRODUCT_NAME = ticket.getMovieTitle();
        PRODUCT_PRICE = ticket.getTotalCost();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        editor = sharedPref.edit();

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        MyWebViewClient myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);
        myWebViewClient.readyAndCallPay();
    }

    // 카카오페이 결제
    class MyWebViewClient extends WebViewClient {

        PaymentApi service = PaymentApi.retrofit.create(PaymentApi.class);

        // 1단계: 결제 준비
        public void readyAndCallPay() {
            String cid = "TC0ONETIME";
            String partner_order_id = "1001";
            String partner_user_id = "gorany";
            String item_name = PRODUCT_NAME;
            Integer quantity = 1;
            Integer total_amount = PRODUCT_PRICE;
            Integer tax_free_amount = 0;
            String approval_url = "https://developers.kakao.com";
            String cancel_url = "https://naver.com";
            String fail_url = "https://google.com";

            service.paymentReady(cid, partner_order_id, partner_user_id, item_name, quantity,
                    total_amount, tax_free_amount, approval_url, cancel_url, fail_url).enqueue(new Callback<PayReady>() {
                @Override
                public void onResponse(Call<PayReady> call, Response<PayReady> response) {
                    PayReady ready = response.body();
                    if(!response.isSuccessful()){
                        Log.i("Retrofit onResponse", "response was not successful");
                    } else {
                        if (ready != null) {
                            String url = ready.getNext_redirect_mobile_url();
                            String tid = ready.getTid();
                            webView.loadUrl(url);
                            tidPin = tid;
                        } else {
                            Log.e("Retrofit onResponse", "no response body");
                        }
                    }
                }

                @Override
                public void onFailure(Call<PayReady> call, Throwable throwable) {
                    Log.e("Debug", "Error: " + throwable.getMessage());
                }
            });
        }

        // 2단계: 결제 승인
        public void approvePayment() {
            String cid = "TC0ONETIME";
            String tid = tidPin;
            String partner_order_id = "1001";
            String partner_user_id = "gorany";
            String pg_token = pgToken;
            Integer total_amount = PRODUCT_PRICE;

            service.paymentApprove(cid, tid, partner_order_id, partner_user_id, pg_token, total_amount).enqueue(new Callback<PayApprove>() {
                @Override
                public void onResponse(Call<PayApprove> call, Response<PayApprove> response) {
                    if (response.isSuccessful()) {
                        Log.i("Debug", "payment approve success");

                        // Todo: 서버에 등록
                        UserTicketApi ticketService = UserTicketApi.retrofit.create(UserTicketApi.class);
                        ticketService.reserveTicket(ticket).enqueue(new Callback<Ticket>() {
                            @Override
                            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                                // 성공시 앱 캐시에도 저장
                                Ticket ticket = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(ticket);
                                editor.putString("mytickets", json);
                                editor.apply();

                                Log.i("paymentApprove", "success");
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Ticket> call, Throwable t) {
                                // 실패시
                                Log.i("paymentApprove", "fail");
                                finish();
                            }
                        });
                    } else {
                        Log.e("Debug", "response is not successful");
                    }
                }

                @Override
                public void onFailure(Call<PayApprove> call, Throwable throwable) {
                    Log.e("Debug", "Error: " + throwable.getMessage());
                }
            });
        }

        public static final String INTENT_PROTOCOL_START = "intent:";
        public static final String INTENT_PROTOCOL_INTENT = "#Intent;";

        // URL 변경시 발생 이벤트
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("Debug", "change url: " + url);

            if (url != null && url.contains("pg_token=")) {
                Log.i("url loading", "pg_token");
                String pg_Token = url.substring(url.indexOf("pg_token=") + 9);
                pgToken = pg_Token;
                this.approvePayment();
            } else if (url != null && url.startsWith("intent://")) {
                Log.i("url loading", "intent");
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    startActivity(intent);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            view.loadUrl(url);
            return false;
        }
    }
}
