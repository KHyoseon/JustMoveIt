package com.example.justmoveit.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 싱글톤 적용
public class RetrofitClient {
    // 레트로핏 클라이언트 선언
    private static Retrofit retrofitClient = null;
    private RetrofitClient() {
    }
    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor loggingInterceptor= new HttpLoggingInterceptor(message -> {
            Log.d("TAG", "RetrofitClient - log() called / message: "+message);

            // json 객체라면
            if(isJsonObject(message)) {
                Log.d("TAG", new JsonObject().getAsJsonObject(message).toString());
            } // json 배열이라면
            else if(isJsonArray(message)){
                Log.d("TAG", new JsonObject().getAsJsonObject(message).toString());
            }
            else {
                try{
                    Log.d("TAG", new JsonObject().getAsJsonObject(message).toString());
                }catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // 기본 파라매터 인터셉터 설정
        Interceptor baseParameterInterceptor = new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Log.d("TAG", "RetrofitClient - interceptor() called");
                // 오리지널 리퀘스트
                Request originalRequest = chain.request();
                // 쿼리 파라매터 추가하기. ex) 액세스키
                HttpUrl addedUrl = originalRequest.url().newBuilder().addQueryParameter("client_id", "f5a6d4kfdlf").build();
                Request finalRequest = originalRequest.newBuilder().url(addedUrl).method(originalRequest.method(), originalRequest.body()).build();
                return chain.proceed(finalRequest);
            }
        };

        // 위에서 설정한 로그 인터셉터와 기본 파라매터 인터셉터를 okhttp 클라이언트에 추가한다
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(baseParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)   // 10초동안 커넥션했는데 반응이 없으면 종료
                .readTimeout(10, TimeUnit.SECONDS)      // 보내고 쓰는거 10초로 제한
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)                // 실패했을 때 다시 시도할 것인가
                .build();


        if(retrofitClient == null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    // 위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                    .client(client)
                    .build();
        }
        return retrofitClient;
    }

    public static boolean isJsonObject(String string){
        return string.startsWith("{") && string.endsWith("}");
    }

    public static boolean isJsonArray(String string){
        return string.startsWith("[") && string.endsWith("]");
    }
}
