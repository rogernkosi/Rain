package com.rogernkosi.rainassessment.model;

public class LatitudeLongitude {
    private String lat;
    private String lng;
    private String cityName;

    public LatitudeLongitude(String lat, String lng, String cityName) {
        this.lat = lat;
        this.lng = lng;
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getCityName() {
        return cityName;
    }
}
