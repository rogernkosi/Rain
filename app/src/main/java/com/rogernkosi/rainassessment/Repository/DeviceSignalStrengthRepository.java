package com.rogernkosi.rainassessment.Repository;

import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.util.Log;

public class DeviceSignalStrengthRepository {

    private static DeviceSignalStrengthRepository repository;

    private DeviceSignalStrengthRepository(){}

    private static CellInfoGsm cellInfoGsm;
    private static CellInfoCdma cellInfoCdma;
    private static CellInfoLte cellInfoLte;
    private static CellInfoWcdma cellInfoWcdma;

    public synchronized static DeviceSignalStrengthRepository getInstance(){
        if (repository == null){
            return new DeviceSignalStrengthRepository();
        }
        return repository;
    }

    public CellInfoGsm getCellInfoGsm() {
        return cellInfoGsm;
    }

    public void setCellInfoGsm(CellInfoGsm cellInfoGsm) {
        this.cellInfoGsm = cellInfoGsm;
    }

    public CellInfoCdma getCellInfoCdma() {
        return cellInfoCdma;
    }

    public void setCellInfoCdma(CellInfoCdma cellInfoCdma) {
        this.cellInfoCdma = cellInfoCdma;
    }

    public CellInfoLte getCellInfoLte() {
        return cellInfoLte;
    }

    public void setCellInfoLte(CellInfoLte cellInfoLte) {
        this.cellInfoLte = cellInfoLte;
    }

    public CellInfoWcdma getCellInfoWcdma() {
        return cellInfoWcdma;
    }

    public void setCellInfoWcdma(CellInfoWcdma cellInfoWcdma) {
        this.cellInfoWcdma = cellInfoWcdma;
    }
}
