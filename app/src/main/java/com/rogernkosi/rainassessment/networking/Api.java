package com.rogernkosi.rainassessment.networking;

import com.rogernkosi.rainassessment.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("forecast.json")
    Call<Response> getForeCast(@Query("key") String key, @Query("q") String location, @Query("days") int numberOfDays);
}
