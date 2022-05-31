package com.moringaschool.myapplication.network;

import com.moringaschool.myapplication.models.YelpApiBusinessSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpApiBusinessSearchResponse> getRestaurants(
            @Query("location") String location,
            @Query("term") String term
    );
}
