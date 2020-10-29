package com.rogernkosi.rainassessment;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rogernkosi.rainassessment.adapters.DeviceSignalListAdapter;
import com.rogernkosi.rainassessment.service.PhoneSignalStrengthService;
import com.rogernkosi.rainassessment.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceSignalStrengthActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 3455;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DeviceSignalStrengthActivity.class);
        return intent;
    }

    @BindView(R.id.device_connections_list) RecyclerView deviceConnectionsList;
    @BindView(R.id.load_signal) ProgressBar progressBar;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_strength_signal);
        ButterKnife.bind(this);

        setupToolBar();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startPhoneSignalStrengthService();
        }

        listenConnectionEvents();
        listFailedEvents();
    }

    private void listFailedEvents() {
        PhoneSignalStrengthService.failedResult.observe(this, s -> {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                        dialog.dismiss();
                    }).setTitle(s).create().show();
        });
    }

    private void listenConnectionEvents() {
        PhoneSignalStrengthService.listMutableLiveData.observe(this, signalPowerModels -> {
            DeviceSignalListAdapter deviceSignalListAdapter = new DeviceSignalListAdapter();
            deviceConnectionsList.setLayoutManager(new LinearLayoutManager(this));
            deviceConnectionsList.setAdapter(deviceSignalListAdapter);
            deviceSignalListAdapter.setPowerModels(signalPowerModels);
            progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            startPhoneSignalStrengthService();
        }
    }

    private boolean isDeviceStrengthServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (PhoneSignalStrengthService.class.getName().equals(serviceInfo.service.getClassName())) {
                    if (serviceInfo.started) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void stopPhoneSignalStrengthService() {
       if (isDeviceStrengthServiceRunning()){
           Intent intent = new Intent(getApplicationContext(), PhoneSignalStrengthService.class);
           intent.setAction(Constants.STOP_DEVICE_SIGNAL_STRENGTH_SERVICE);
           startService(intent);
       }
    }

    private void startPhoneSignalStrengthService() {
        if (!isDeviceStrengthServiceRunning()){
            Intent intent = new Intent(getApplicationContext(), PhoneSignalStrengthService.class);
            intent.setAction(Constants.START_DEVICE_SIGNAL_STRENGTH_SERVICE);
            startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        stopPhoneSignalStrengthService();
        super.onDestroy();
    }

    // I should add a base activity to avoid so much boiler duplicates
    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}