package com.example.justmoveit.retrofit;

import android.util.Log;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 싱글톤
public class RetrofitManager {
    private static RetrofitManager retrofitManager = null;
    private RetrofitManager() {
    }

    public static RetrofitManager getInstance(){
        if(retrofitManager == null){
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    // 로컬호스트 연결
    //https://velog.io/@jeep_chief_14/%ED%86%A0%EB%A7%89%EA%B8%80Retrofit%EC%97%90%EC%84%9C-localhost-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0
    IRetrofit iRetrofit = RetrofitClient.getClient("https://www.naver.com").create(IRetrofit.class);

    public void searchPhotos(String searchTerm, Comp comp){
        String term = searchTerm==null? "it": searchTerm;
        Call<JsonElement> call = iRetrofit.searchPhotos(term);
        if(call == null)    return;
        call.enqueue(new Callback<JsonElement>(){
            // 응답 성공시
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("TAG", "RetrofitManager - onResponse() called / response : "+response.message());
                comp.completion("success", "response.message()");
            }

            // 응답 실패시
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("TAG", "RetrofitManager - onFailure() called /t"+t.getMessage());
                comp.completion("fail", t.getMessage());
            }
        });
    }

    interface Comp {
        void completion(String responseState, String responseBody);
    }
}
