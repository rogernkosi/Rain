package com.rogernkosi.rainassessment.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.LocationServices;
import com.rogernkosi.rainassessment.Repository.DeviceSignalStrengthRepository;
import com.rogernkosi.rainassessment.model.SignalPowerModel;
import com.rogernkosi.rainassessment.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class PhoneSignalStrengthService extends Service {
    public final static MutableLiveData<List<SignalPowerModel>> listMutableLiveData = new MutableLiveData<>();
    public final static MutableLiveData<String> failedResult = new MutableLiveData<>();
    private static final String TAG = PhoneSignalStrengthService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    SignalStrengthListener signalStrengthListener;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(Constants.START_DEVICE_SIGNAL_STRENGTH_SERVICE)) {
                    startSignalStrengthService();
                } else if (action.equals(Constants.STOP_DEVICE_SIGNAL_STRENGTH_SERVICE)) {
                    stopSignalStrengthService();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private ConnectivityManager connectivityManager() {
        return (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void startSignalStrengthService(){
        //start the signal strength listener
        signalStrengthListener = new SignalStrengthListener();
        ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(signalStrengthListener, SignalStrengthListener.LISTEN_SIGNAL_STRENGTHS);
        thread.start();
    }

    private void stopSignalStrengthService(){
        try {
            thread.interrupt();
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
        stopSelf();
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    sleep(10000);
                    ConnectivityManager connectivityManager = connectivityManager();
                    List<SignalPowerModel> powerModelList = new ArrayList<>();
                    for (NetworkInfo network : connectivityManager.getAllNetworkInfo()) {
                        int type = network.getType();
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            WifiInfo info = wifiManager.getConnectionInfo();
                            StringBuilder builder = new StringBuilder();
                            builder.append(Constants.SIGNAL_INDICATOR_KEY);
                            builder.append(": ");
                            builder.append( info.getRssi());
                            powerModelList.add(new SignalPowerModel(network.getTypeName(),builder.toString()));
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                if (DeviceSignalStrengthRepository.getInstance().getCellInfoGsm() != null) {
                                    CellInfoGsm cellInfoGsm = DeviceSignalStrengthRepository.getInstance().getCellInfoGsm();
                                    int dbm = cellInfoGsm.getCellSignalStrength().getDbm();
                                    StringBuilder builder = new StringBuilder();
                                    builder.append(Constants.SIGNAL_INDICATOR_KEY);
                                    builder.append(": ");
                                    builder.append(dbm);
                                    powerModelList.add(new SignalPowerModel(network.getSubtypeName(), builder.toString()));
                                }
                                if (DeviceSignalStrengthRepository.getInstance().getCellInfoCdma() != null) {
                                    CellInfoCdma cellInfoCdma = DeviceSignalStrengthRepository.getInstance().getCellInfoCdma();
                                    int dbm = cellInfoCdma.getCellSignalStrength().getDbm();
                                    StringBuilder builder = new StringBuilder();
                                    builder.append(Constants.SIGNAL_INDICATOR_KEY);
                                    builder.append(": ");
                                    builder.append(dbm);
                                    powerModelList.add(new SignalPowerModel(network.getSubtypeName(), builder.toString()));
                                }
                                if (DeviceSignalStrengthRepository.getInstance().getCellInfoLte() != null) {
                                    CellInfoLte cellInfoLte = DeviceSignalStrengthRepository.getInstance().getCellInfoLte();
                                    int dbm = cellInfoLte.getCellSignalStrength().getDbm();
                                    StringBuilder builder = new StringBuilder();
                                    builder.append(Constants.REFERENCE_SIGNAL_RECEIVED);
                                    builder.append(": ");
                                    builder.append(dbm);
                                    powerModelList.add(new SignalPowerModel(network.getSubtypeName(), builder.toString()));
                                }
                                if (DeviceSignalStrengthRepository.getInstance().getCellInfoWcdma() != null && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                    CellInfoWcdma cellInfoWcdma = DeviceSignalStrengthRepository.getInstance().getCellInfoWcdma();
                                    int dbm = cellInfoWcdma.getCellSignalStrength().getDbm();
                                    StringBuilder builder = new StringBuilder();
                                    builder.append(Constants.RECEIVED_SIGNAL_POWER);
                                    builder.append(": ");
                                    builder.append(dbm);
                                    powerModelList.add(new SignalPowerModel(network.getSubtypeName(), builder.toString()));
                                }
                            }
                        }
                    }
                    listMutableLiveData.postValue(powerModelList);
                  Log.d("powerModelList", powerModelList.toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private class SignalStrengthListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
            ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(signalStrengthListener, SignalStrengthListener.LISTEN_SIGNAL_STRENGTHS);
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            String ltestr = signalStrength.toString();
            String[] parts = ltestr.split(" ");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                @SuppressLint("MissingPermission") List<CellInfo> allCellInfo = tm.getAllCellInfo(); // requires ACCESS_FINE_LOCATION, will be checked in activity.
                for (CellInfo cellInfo : allCellInfo) {
                    Log.d(TAG, cellInfo.toString());
                    if (cellInfo instanceof CellInfoGsm) {
                        DeviceSignalStrengthRepository.getInstance().setCellInfoGsm((CellInfoGsm) cellInfo);
                    } else if (cellInfo instanceof CellInfoCdma) {
                        DeviceSignalStrengthRepository.getInstance().setCellInfoCdma((CellInfoCdma) cellInfo);
                    } else if (cellInfo instanceof CellInfoLte) {
                        DeviceSignalStrengthRepository.getInstance().setCellInfoLte((CellInfoLte) cellInfo);
                    } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2 && cellInfo instanceof CellInfoWcdma) {
                        DeviceSignalStrengthRepository.getInstance().setCellInfoWcdma(((CellInfoWcdma) cellInfo));
                    }
                }
            } // TODO use reflections to get data from methods hidden from the API for older versions
        }
    }

}
