package com.rogernkosi.rainassessment.persistance.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface LocationDao {
    @Insert
    Maybe<Long> insert(PinnedLocation location);

    @Delete
    Completable delete(PinnedLocation location);

    @Query("SELECT * FROM location_table ORDER BY id DESC")
    Observable<List<PinnedLocation>> getAllLocations();

    @Query("SELECT * FROM location_table WHERE id = :locationId ORDER BY id DESC")
    Flowable<List<PinnedLocation>> getLocation(int locationId);
}
