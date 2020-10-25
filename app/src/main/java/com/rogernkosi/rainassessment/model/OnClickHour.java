package com.rogernkosi.rainassessment.model;

public class OnClickHour {
    private Hour hour;
    private String selectedHour;

    public OnClickHour(Hour hour, String selectedHour) {
        this.hour = hour;
        this.selectedHour = selectedHour;
    }

    public String getSelectedHour() {
        return selectedHour;
    }

    public Hour getHour() {
        return hour;
    }
}
