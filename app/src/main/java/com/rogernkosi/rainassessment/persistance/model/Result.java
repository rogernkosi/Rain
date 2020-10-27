package com.rogernkosi.rainassessment.persistance.model;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    private long id;
    private PinnedLocation location;
    private List<PinnedLocation> locations;
    private boolean isSuccessful;

    public Result() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PinnedLocation getLocation() {
        return location;
    }

    public void setLocation(PinnedLocation location) {
        this.location = location;
    }

    public List<PinnedLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<PinnedLocation> locations) {
        this.locations = locations;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
