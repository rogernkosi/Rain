package com.rogernkosi.rainassessment.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.rogernkosi.rainassessment.DisplayMapActivity;
import com.rogernkosi.rainassessment.R;
import com.rogernkosi.rainassessment.model.LatitudeLongitude;
import com.rogernkosi.rainassessment.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationService extends LifecycleService {

    private static final int MAX_ADDRESS_RESULTS = 1;
    String TAG = LocationService.class.getSimpleName();

    public final static MutableLiveData<LatitudeLongitude> latLngMutableLiveData = new MutableLiveData<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(Constants.START_LOCATION_SERVICE)) {
                    startLocationService();
                } else if (action.equals(Constants.STOP_LOCATION_SERVICE)) {
                    stopLocationService();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private LocationCallback locationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult != null && locationResult.getLastLocation() != null){
                double latitude = locationResult.getLastLocation().getLatitude();
                double longitude = locationResult.getLastLocation().getLongitude();
                Log.e("locationtest", ""+latitude+""+longitude);
                getLocation(latitude, longitude);
            }
        }
    };

    private void getLocation(double lat, double lng){
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String locality = null;
        try {
            List<Address> fromLocation = geocoder.getFromLocation(lat, lng, MAX_ADDRESS_RESULTS);
            locality = fromLocation.get(0).getLocality(); // I will always use the first element in the list since I am getting only 1 result
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatitudeLongitude latLng = new LatitudeLongitude(String.valueOf(lat), String.valueOf(lng), locality);
        latLngMutableLiveData.setValue(latLng);
    }

    private void startLocationService(){
        String channelId = "location_notification_channel";

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(this, DisplayMapActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                Constants.NOTIFICATION_INTENT,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(),
                channelId
        );
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(null);
        builder.setContentTitle(Constants.LOCATION_NOTIFICATION_TITLE);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentText(Constants.LOCATION_NOTIFICATION_TEXT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setPriority(NotificationCompat.PRIORITY_MAX); // I will be tracking the users location, so I should make it easier for a user to notice their location is being tracked

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null){
                NotificationChannel notificationChannel = new NotificationChannel(
                        channelId,
                        Constants.NOTIFICATION_CHANNEL,
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(Constants.LOCATION_INTERVAL);
        locationRequest.setFastestInterval(Constants.FASTEST_LOCATION_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        startForeground(Constants.LOCATION_SERVICE_ID, builder.build());
    }

    private void stopLocationService(){
        LocationServices.getFusedLocationProviderClient(this)
                .removeLocationUpdates(locationCallback);
        stopForeground(true);
        stopSelf();
    }
}
