package com.rogernkosi.rainassessment.util;

public interface Constants {
    String BASE_URI = "http://api.weatherapi.com/v1/";
    int DAYS = 1;
    String KEY = "7f9e0fd779c3490cb17173416202210";
    int LOCATION_SERVICE_ID = 345;
    String START_LOCATION_SERVICE = "startLocationService";
    String STOP_LOCATION_SERVICE = "stopLocationService";
    int NOTIFICATION_INTENT = 3456;
    String LOCATION_NOTIFICATION_TITLE = "Rain";
    CharSequence LOCATION_NOTIFICATION_TEXT = "Current Location being shared";
    CharSequence NOTIFICATION_CHANNEL = "Rain Service";
    String CHANNEL_DESCRIPTION = "This channel is being used by Rain";
    long LOCATION_INTERVAL = 5000L;
    long FASTEST_LOCATION_INTERVAL = 2000L;
    String ACTION_VIEW_CURRENT_LOCATION = "currentLocation";
}
