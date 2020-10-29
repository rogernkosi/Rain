package com.rogernkosi.rainassessment;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;
import com.rogernkosi.rainassessment.service.LocationService;
import com.rogernkosi.rainassessment.util.Constants;
import com.rogernkosi.rainassessment.util.TimeUtils;
import com.rogernkosi.rainassessment.viewmodel.LocationViewModel;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String EXTRA_SHOW_FAVOURITES = "EXTRA_SHOW_FAVOURITES";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2355;

    private GoogleMap mMap;
    private LocationViewModel locationViewModel;
    private View parentView;
    private Marker marker;
    private boolean showFavorites;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.anchor_view) View anchorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        parentView = findViewById(android.R.id.content); // root view group
        setSupportActionBar(toolbar);

        showFavorites = getIntent().getBooleanExtra(EXTRA_SHOW_FAVOURITES, false);

        checkPermissions();

        locationViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LocationViewModel.class);
        if (showFavorites) {
            locationViewModel.loadFavorites();
        }
        listenEvents();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            startLocationService();
        } else {
            retry();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorites_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_favorites: {
                startActivity(DisplayMapActivity.getStartIntent(this, true));
                return true;
            }
            case R.id.device_signal: {
                startActivity(DeviceSignalStrengthActivity.getStartIntent(this));
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateMap(showFavorites);
    }

    private boolean isLocationServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (LocationService.class.getName().equals(serviceInfo.service.getClassName())) {
                    if (serviceInfo.foreground) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void retry() {
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    checkPermissions();
                }).setTitle(R.string.grant_permission).create().show();
    }

    private void stopLocationService() {
        if (isLocationServiceRunning()) {
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.STOP_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "We stopped tracking your live location", Toast.LENGTH_LONG).show();
        }
    }

    private void startLocationService() {
        if (!isLocationServiceRunning()) {
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.START_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "We are tracking your live location", Toast.LENGTH_LONG).show();
        }
    }

    private void updateMap(boolean showFavorites) {
        // No need to go through the ViewModel as Service is an Android component.
        LocationService.latLngMutableLiveData.observe(this, latLng -> {
            if (!showFavorites) {
                LatLng location = new LatLng(Double.valueOf(latLng.getLat()), Double.valueOf(latLng.getLng()));
                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions().position(location).title(latLng.getCityName()).draggable(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                mMap.setOnMarkerClickListener(marker -> {
                    PopupMenu popup = new PopupMenu(this, anchorView);
                    popup.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()) {
                            case R.id.view_weather:
                                startActivity(WeatherDetailsActivity.getStartIntent(DisplayMapActivity.this, marker.getPosition()));
                                return true;
                            case R.id.add_to_favorites:
                                showSaveLocationDialog(marker.getPosition());
                                return true;
                            default:
                                return false;
                        }
                    });
                    popup.inflate(R.menu.location_options);
                    popup.show();
                    return false;
                });
            } else {
                LocationService.latLngMutableLiveData.observe(DisplayMapActivity.this, latitudeLongitude -> {
                    LatLng location = new LatLng(Double.valueOf(latLng.getLat()), Double.valueOf(latLng.getLng()));
                    if (marker != null) {
                        marker.remove();
                    }
                    marker = mMap.addMarker(new MarkerOptions().position(location).title(latLng.getCityName()).draggable(true));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                    locationViewModel.favoritesLisResult
                            .observe(DisplayMapActivity.this, pinnedLocations -> {
                                for (PinnedLocation pinnedLocation : pinnedLocations) {
                                    LatLng lng = new LatLng(pinnedLocation.getLatitude(), pinnedLocation.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(lng).title(pinnedLocation.getLocationName()).draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.saved_location)));
                                }
                            });
                    mMap.setOnMarkerClickListener(marker -> {
                        PopupMenu popup = new PopupMenu(this, anchorView);
                        popup.setOnMenuItemClickListener(item -> {
                            switch (item.getItemId()) {
                                case R.id.view_weather:
                                    startActivity(WeatherDetailsActivity.getStartIntent(DisplayMapActivity.this, marker.getPosition()));
                                    return true;
                                case R.id.add_to_favorites:
                                    showSaveLocationDialog(marker.getPosition());
                                    return true;
                                default:
                                    return false;
                            }
                        });
                        popup.inflate(R.menu.location_options);
                        popup.show();
                        return false;
                    });

                });
            }
        });
    }

    private void showSnackMessage(String message) {
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
                .setTextColor(getResources().getColor(R.color.teal_700))
                .show();
    }

    public void showSaveLocationDialog(LatLng location) {
        LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.input_location, null);
        AppCompatEditText name = view.findViewById(R.id.location_name);
        AppCompatEditText description = view.findViewById(R.id.description);

        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                .setPositiveButton(R.string.save_location, (dialogInterface, i) -> {
                    String descriptionData = description.getText().toString();
                    String nameData = name.getText().toString();
                    if (validFields(descriptionData, nameData)) {
                        PinnedLocation pinnedLocation = new PinnedLocation(nameData, descriptionData, TimeUtils.getCurrentDateTime(), location.latitude, location.longitude);
                        locationViewModel.insert(pinnedLocation);
                    } else {
                        showSnackMessage(getString(R.string.fields_not_valid));
                    }
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss())
                .create();
        alertDialog.setTitle(getString(R.string.save_location_title));
        alertDialog.setCancelable(false);
        alertDialog.setView(view);
        alertDialog.show();
    }

    private boolean validFields(String descriptionData, String nameData) {
        if ((descriptionData != null && StringUtils.isNotEmpty(descriptionData)) && (nameData != null && StringUtils.isNotEmpty(nameData))) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        stopLocationService();
        locationViewModel.disposeElements();
        super.onDestroy();
    }

    public static Intent getStartIntent(Context context, boolean isShowFavorites) {
        Intent intent = new Intent(context, DisplayMapActivity.class);
        intent.putExtra(EXTRA_SHOW_FAVOURITES, isShowFavorites);
        return intent;
    }

    private void listenEvents() {
        locationViewModel.insertLocationResult
                .observe(this, aLong -> showSnackMessage(getString(R.string.successful_general_message)));
    }

}