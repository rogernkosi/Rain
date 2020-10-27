package com.rogernkosi.rainassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rogernkosi.rainassessment.Repository.PinLocationRepository;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;
import com.rogernkosi.rainassessment.persistance.model.Result;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LocationViewModel extends AndroidViewModel {

    private final PinLocationRepository locationRepository;

    public MutableLiveData<List<PinnedLocation>> favoritesLisResult = new MutableLiveData<>();
    public MutableLiveData<Long> insertLocationResult = new MutableLiveData<>();
    private DisposableObserver<List<PinnedLocation>> disposableObserver;
    private DisposableMaybeObserver<Long> disposableInsertResult;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        locationRepository = PinLocationRepository.getInstance(application);
    }

    public void loadFavorites() {
        disposableObserver = new DisposableObserver<List<PinnedLocation>>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<PinnedLocation> pinnedLocations) {
                favoritesLisResult.postValue(pinnedLocations);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };

        locationRepository.getPinnedLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver);
    }

    public void insert(PinnedLocation location) {
        disposableInsertResult = new DisposableMaybeObserver<Long>() {
            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull Long aLong) {
                insertLocationResult.postValue(aLong);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        locationRepository.insert(location)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableInsertResult);
    }

    public void disposeElements(){
        if (disposableInsertResult != null && !disposableInsertResult.isDisposed())
            disposableInsertResult.dispose();

        if (disposableObserver != null && !disposableObserver.isDisposed())
            disposableObserver.dispose();
    }
}
