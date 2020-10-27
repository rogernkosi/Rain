package com.rogernkosi.rainassessment.Repository;

import android.app.Application;

import com.rogernkosi.rainassessment.persistance.LocationDatabase;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public class PinLocationRepository {

    private static PinLocationRepository repository;

    private LocationDatabase database;

    // TODO implement dagger
    private PinLocationRepository(LocationDatabase database) {
        this.database = database;
    }


    public static PinLocationRepository getInstance(Application application) {
        if (repository == null){
            return new PinLocationRepository(LocationDatabase.getInstance(application));
        }
        return repository;
    }

    public Observable<List<PinnedLocation>> getPinnedLocations(){
        return database
                .locationDao()
                .getAllLocations();
    }

    public Flowable<List<PinnedLocation>> getLocation(int id){
        return database
                .locationDao()
                .getLocation(id);
    }

    public Maybe<Long> insert(PinnedLocation location){
        return database
                .locationDao()
                .insert(location);
    }

    public Completable delete(PinnedLocation location){
        return database
                .locationDao()
                .delete(location);
    }
}
