package com.rogernkosi.rainassessment.model;

public class SignalPowerModel {
    private String dataConnectionName;
    private String dataConnectionValue;

    public SignalPowerModel(String dataConnectionName, String dataConnectionValue) {
        this.dataConnectionName = dataConnectionName;
        this.dataConnectionValue = dataConnectionValue;
    }

    public String getDataConnectionName() {
        return dataConnectionName;
    }

    public String getDataConnectionValue() {
        return dataConnectionValue;
    }

    @Override
    public String toString() {
        return "SignalPowerModel{" +
                "dataConnectionName='" + dataConnectionName + '\'' +
                ", dataConnectionValue='" + dataConnectionValue + '\'' +
                '}';
    }
}
