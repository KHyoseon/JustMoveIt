package com.example.justmoveit.payment;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.model.kakaoDto.PayApprove;
import com.example.justmoveit.model.kakaoDto.PayApproveParam;
import com.example.justmoveit.model.kakaoDto.PayReady;
import com.google.gson.JsonObject;

import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private WebView webView;
    private MyWebViewClient myWebViewClient;
    private String tidPin; //결제 고유 번호
    private String pgToken; //결제 요청 토큰
    private static String PRODUCT_NAME; //상품 이름
    private static Integer PRODUCT_PRICE; //상품 가격

    public PaymentActivity() {
    }

    public PaymentActivity(String productName, Integer productPrice) {
        PRODUCT_NAME = productName;
        PRODUCT_PRICE = productPrice;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toast.makeText(this, "PaymentActivity 진입", Toast.LENGTH_SHORT).show();

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);
        myWebViewClient.readyAndCallPay();
    }

    class MyWebViewClient extends WebViewClient {

        RetrofitService service = RetrofitService.retrofit.create(RetrofitService.class);

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
                        Log.e("Retrofit onResponse", "response was not successful");
                    } else {
                        if (ready != null) {
                            String url = ready.getNext_redirect_mobile_url();
                            String tid = ready.getTid();

                            Log.i(">>>>>>>>>>>>> redirect_mobile_url", url);
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

        public void approvePayment() {
            String cid = "TC0ONETIME";
            String tid = tidPin;
            String partner_order_id = "1001";
            String partner_user_id = "gorany";
            String pg_token = pgToken;
            Integer total_amount = PRODUCT_PRICE;

            PayApproveParam param = new PayApproveParam(cid, tid, partner_order_id, partner_user_id, pg_token, total_amount);

            service.paymentApprove(param).enqueue(new Callback<PayApprove>() {
                @Override
                public void onResponse(Call<PayApprove> call, Response<PayApprove> response) {
                    if (response.isSuccessful()) {
                        Log.e("Debug", "payment approve success");
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
