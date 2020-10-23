package com.rogernkosi.rainassessment.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rogernkosi.rainassessment.model.Response;
import com.rogernkosi.rainassessment.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;

public class ForeCastRepository {
    private static ForeCastRepository repository;

    public static ForeCastRepository getInstance() {
        if (repository == null){
            repository = new ForeCastRepository();
        }
        return repository;
    }

    private Api api;

    public ForeCastRepository(){
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
