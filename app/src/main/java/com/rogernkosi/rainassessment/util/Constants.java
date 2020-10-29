package com.rogernkosi.rainassessment.util;

public interface Constants {
    String BASE_URI = "http://api.weatherapi.com/v1/";
    int DAYS = 1;
    String KEY = "1dcddfb052de4040a27214431202410";
    int LOCATION_SERVICE_ID = 345;
    String START_LOCATION_SERVICE = "startLocationService";
    String STOP_LOCATION_SERVICE = "stopLocationService";
    String START_DEVICE_SIGNAL_STRENGTH_SERVICE = "startDeviceSignalService";
    String STOP_DEVICE_SIGNAL_STRENGTH_SERVICE = "stopDeviceSignalService";
    int NOTIFICATION_INTENT = 3456;
    String LOCATION_NOTIFICATION_TITLE = "Rain";
    CharSequence LOCATION_NOTIFICATION_TEXT = "Current Location being shared";
    CharSequence NOTIFICATION_CHANNEL = "Rain Service";
    String CHANNEL_DESCRIPTION = "This channel is being used by Rain";
    long LOCATION_INTERVAL = 10000;
    long FASTEST_LOCATION_INTERVAL = 10000;
    String ACTION_VIEW_CURRENT_LOCATION = "currentLocation";
    String SIGNAL_INDICATOR_KEY = "RSSI"; // received signal strength indicator
    String REFERENCE_SIGNAL_RECEIVED = "RSRP"; // Reference signal received power
    String RECEIVED_SIGNAL_POWER = "RSCP"; // received signal code power
}
