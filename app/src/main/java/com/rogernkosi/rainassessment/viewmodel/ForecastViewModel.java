package com.rogernkosi.rainassessment.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rogernkosi.rainassessment.Repository.PinLocationRepository;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;
import com.rogernkosi.rainassessment.model.Response;
import com.rogernkosi.rainassessment.Repository.ForecastRepository;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class ForecastViewModel extends AndroidViewModel {

    private MutableLiveData<Response> mutableLiveData;
    private final ForecastRepository foreCastRepository;

    public ForecastViewModel(@NonNull Application application) {
        super(application);
        foreCastRepository = ForecastRepository.getInstance();
    }

    public LiveData<Response> getForecastRepository(String location) {
        mutableLiveData = foreCastRepository.getForecast(location);
        return mutableLiveData;
    }

}
