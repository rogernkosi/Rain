package com.rogernkosi.rainassessment.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rogernkosi.rainassessment.model.Response;
import com.rogernkosi.rainassessment.networking.Api;
import com.rogernkosi.rainassessment.networking.RetrofitService;
import com.rogernkosi.rainassessment.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;

public class ForecastRepository {
    private static ForecastRepository repository;

    public static ForecastRepository getInstance() {
        if (repository == null){
            repository = new ForecastRepository();
        }
        return repository;
    }

    private Api api;

    private ForecastRepository(){
        api = RetrofitService.createService(Api.class);
    }

    public MutableLiveData<Response> getForecast(String location){
        MutableLiveData<Response> data = new MutableLiveData<>();
        api.getForeCast(Constants.KEY, location, Constants.DAYS).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("failure", t.getMessage());
            }
        });
        return data;
    }
}
