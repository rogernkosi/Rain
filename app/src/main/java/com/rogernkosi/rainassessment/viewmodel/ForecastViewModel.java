package com.rogernkosi.rainassessment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.rogernkosi.rainassessment.model.Response;
import com.rogernkosi.rainassessment.Repository.ForecastRepository;

public class ForecastViewModel extends ViewModel {

    private MutableLiveData<Response> mutableLiveData;
    private ForecastRepository foreCastRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        foreCastRepository = ForecastRepository.getInstance();
    }

    public LiveData<Response> getForecastRepository(String location) {
        mutableLiveData = foreCastRepository.getForecast(location);
        return mutableLiveData;
    }

}
