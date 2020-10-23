package com.rogernkosi.rainassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.rogernkosi.rainassessment.model.Response;
import com.rogernkosi.rainassessment.networking.ForeCastRepository;

public class ForecastViewModel extends ViewModel {

    private MutableLiveData<Response> mutableLiveData;
    private ForeCastRepository foreCastRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        foreCastRepository = ForeCastRepository.getInstance();
        mutableLiveData = foreCastRepository.getForecast("23,34");
    }

    public LiveData<Response> getForecastRepository() {
        return mutableLiveData;
    }

}
