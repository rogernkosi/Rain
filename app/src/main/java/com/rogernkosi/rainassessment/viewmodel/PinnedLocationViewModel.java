package com.rogernkosi.rainassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.rogernkosi.rainassessment.Repository.PinLocationRepository;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class PinnedLocationViewModel extends AndroidViewModel {

    private final PinLocationRepository locationRepository;

    public PinnedLocationViewModel(@NonNull Application application) {
        super(application);
        locationRepository = PinLocationRepository.getInstance(application);
    }



}
