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

}
