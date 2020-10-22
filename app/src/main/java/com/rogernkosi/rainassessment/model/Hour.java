package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Hour implements Parcelable {
    @SerializedName("time_epoch")
    @Expose
    private Integer timeEpoch;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("temp_c")
    @Expose
    private Double tempC;
    @SerializedName("temp_f")
    @Expose
    private Double tempF;
    @SerializedName("is_day")
    @Expose
    private Integer isDay;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("wind_mph")
    @Expose
    private Double windMph;
    @SerializedName("wind_kph")
    @Expose
    private Double windKph;
    @SerializedName("wind_degree")
    @Expose
    private Integer windDegree;
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    @SerializedName("pressure_mb")
    @Expose
    private Double pressureMb;
    @SerializedName("pressure_in")
    @Expose
    private Double pressureIn;
    @SerializedName("precip_mm")
    @Expose
    private Double precipMm;
    @SerializedName("precip_in")
    @Expose
    private Double precipIn;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("cloud")
    @Expose
    private Integer cloud;
    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;
    @SerializedName("feelslike_f")
    @Expose
    private Double feelslikeF;
    @SerializedName("windchill_c")
    @Expose
    private Double windchillC;
    @SerializedName("windchill_f")
    @Expose
    private Double windchillF;
    @SerializedName("heatindex_c")
    @Expose
    private Double heatindexC;
    @SerializedName("heatindex_f")
    @Expose
    private Double heatindexF;
    @SerializedName("dewpoint_c")
    @Expose
    private Double dewpointC;
    @SerializedName("dewpoint_f")
    @Expose
    private Double dewpointF;
    @SerializedName("will_it_rain")
    @Expose
    private Integer willItRain;
    @SerializedName("chance_of_rain")
    @Expose
    private String chanceOfRain;
    @SerializedName("will_it_snow")
    @Expose
    private Integer willItSnow;
    @SerializedName("chance_of_snow")
    @Expose
    private String chanceOfSnow;
    @SerializedName("vis_km")
    @Expose
    private Double visKm;
    @SerializedName("vis_miles")
    @Expose
    private Double visMiles;
    @SerializedName("gust_mph")
    @Expose
    private Double gustMph;
    @SerializedName("gust_kph")
    @Expose
    private Double gustKph;

    public final static Parcelable.Creator<Hour> CREATOR = new Creator<Hour>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        public Hour[] newArray(int size) {
            return (new Hour[size]);
        }
    };

    protected Hour(Parcel in) {
        this.timeEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.tempC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tempF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.isDay = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.condition = ((Condition) in.readValue((Condition.class.getClassLoader())));
        this.windMph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windKph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windDegree = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.windDir = ((String) in.readValue((String.class.getClassLoader())));
        this.pressureMb = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pressureIn = ((Double) in.readValue((Double.class.getClassLoader())));
        this.precipMm = ((Double) in.readValue((Double.class.getClassLoader())));
        this.precipIn = ((Double) in.readValue((Double.class.getClassLoader())));
        this.humidity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cloud = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.feelslikeC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.feelslikeF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windchillC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windchillF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.heatindexC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.heatindexF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.dewpointC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.dewpointF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.willItRain = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.chanceOfRain = ((String) in.readValue((String.class.getClassLoader())));
        this.willItSnow = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.chanceOfSnow = ((String) in.readValue((String.class.getClassLoader())));
        this.visKm = ((Double) in.readValue((Double.class.getClassLoader())));
        this.visMiles = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gustMph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gustKph = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Hour() {
    }

    public Integer getTimeEpoch() {
        return timeEpoch;
    }

    public void setTimeEpoch(Integer timeEpoch) {
        this.timeEpoch = timeEpoch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTempC() {
        return tempC;
    }

    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

    public Double getTempF() {
        return tempF;
    }

    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Integer getIsDay() {
        return isDay;
    }

    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Double getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(Double pressureMb) {
        this.pressureMb = pressureMb;
    }

    public Double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public Double getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(Double precipMm) {
        this.precipMm = precipMm;
    }

    public Double getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(Double precipIn) {
        this.precipIn = precipIn;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloud() {
        return cloud;
    }

    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public Double getWindchillC() {
        return windchillC;
    }

    public void setWindchillC(Double windchillC) {
        this.windchillC = windchillC;
    }

    public Double getWindchillF() {
        return windchillF;
    }

    public void setWindchillF(Double windchillF) {
        this.windchillF = windchillF;
    }

    public Double getHeatindexC() {
        return heatindexC;
    }

    public void setHeatindexC(Double heatindexC) {
        this.heatindexC = heatindexC;
    }

    public Double getHeatindexF() {
        return heatindexF;
    }

    public void setHeatindexF(Double heatindexF) {
        this.heatindexF = heatindexF;
    }

    public Double getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(Double dewpointC) {
        this.dewpointC = dewpointC;
    }

    public Double getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(Double dewpointF) {
        this.dewpointF = dewpointF;
    }

    public Integer getWillItRain() {
        return willItRain;
    }

    public void setWillItRain(Integer willItRain) {
        this.willItRain = willItRain;
    }

    public String getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(String chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public Integer getWillItSnow() {
        return willItSnow;
    }

    public void setWillItSnow(Integer willItSnow) {
        this.willItSnow = willItSnow;
    }

    public String getChanceOfSnow() {
        return chanceOfSnow;
    }

    public void setChanceOfSnow(String chanceOfSnow) {
        this.chanceOfSnow = chanceOfSnow;
    }

    public Double getVisKm() {
        return visKm;
    }

    public void setVisKm(Double visKm) {
        this.visKm = visKm;
    }

    public Double getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(Double visMiles) {
        this.visMiles = visMiles;
    }

    public Double getGustMph() {
        return gustMph;
    }

    public void setGustMph(Double gustMph) {
        this.gustMph = gustMph;
    }

    public Double getGustKph() {
        return gustKph;
    }

    public void setGustKph(Double gustKph) {
        this.gustKph = gustKph;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("timeEpoch", timeEpoch).append("time", time).append("tempC", tempC).append("tempF", tempF).append("isDay", isDay).append("condition", condition).append("windMph", windMph).append("windKph", windKph).append("windDegree", windDegree).append("windDir", windDir).append("pressureMb", pressureMb).append("pressureIn", pressureIn).append("precipMm", precipMm).append("precipIn", precipIn).append("humidity", humidity).append("cloud", cloud).append("feelslikeC", feelslikeC).append("feelslikeF", feelslikeF).append("windchillC", windchillC).append("windchillF", windchillF).append("heatindexC", heatindexC).append("heatindexF", heatindexF).append("dewpointC", dewpointC).append("dewpointF", dewpointF).append("willItRain", willItRain).append("chanceOfRain", chanceOfRain).append("willItSnow", willItSnow).append("chanceOfSnow", chanceOfSnow).append("visKm", visKm).append("visMiles", visMiles).append("gustMph", gustMph).append("gustKph", gustKph).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(precipMm).append(heatindexF).append(timeEpoch).append(feelslikeC).append(heatindexC).append(gustMph).append(gustKph).append(dewpointC).append(windDir).append(pressureIn).append(precipIn).append(chanceOfRain).append(dewpointF).append(cloud).append(windMph).append(visKm).append(windKph).append(humidity).append(windDegree).append(chanceOfSnow).append(visMiles).append(tempF).append(willItRain).append(windchillC).append(windchillF).append(isDay).append(willItSnow).append(condition).append(feelslikeF).append(time).append(pressureMb).append(tempC).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Hour) == false) {
            return false;
        }
        Hour rhs = ((Hour) other);
        return new EqualsBuilder().append(precipMm, rhs.precipMm).append(heatindexF, rhs.heatindexF).append(timeEpoch, rhs.timeEpoch).append(feelslikeC, rhs.feelslikeC).append(heatindexC, rhs.heatindexC).append(gustMph, rhs.gustMph).append(gustKph, rhs.gustKph).append(dewpointC, rhs.dewpointC).append(windDir, rhs.windDir).append(pressureIn, rhs.pressureIn).append(precipIn, rhs.precipIn).append(chanceOfRain, rhs.chanceOfRain).append(dewpointF, rhs.dewpointF).append(cloud, rhs.cloud).append(windMph, rhs.windMph).append(visKm, rhs.visKm).append(windKph, rhs.windKph).append(humidity, rhs.humidity).append(windDegree, rhs.windDegree).append(chanceOfSnow, rhs.chanceOfSnow).append(visMiles, rhs.visMiles).append(tempF, rhs.tempF).append(willItRain, rhs.willItRain).append(windchillC, rhs.windchillC).append(windchillF, rhs.windchillF).append(isDay, rhs.isDay).append(willItSnow, rhs.willItSnow).append(condition, rhs.condition).append(feelslikeF, rhs.feelslikeF).append(time, rhs.time).append(pressureMb, rhs.pressureMb).append(tempC, rhs.tempC).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(timeEpoch);
        dest.writeValue(time);
        dest.writeValue(tempC);
        dest.writeValue(tempF);
        dest.writeValue(isDay);
        dest.writeValue(condition);
        dest.writeValue(windMph);
        dest.writeValue(windKph);
        dest.writeValue(windDegree);
        dest.writeValue(windDir);
        dest.writeValue(pressureMb);
        dest.writeValue(pressureIn);
        dest.writeValue(precipMm);
        dest.writeValue(precipIn);
        dest.writeValue(humidity);
        dest.writeValue(cloud);
        dest.writeValue(feelslikeC);
        dest.writeValue(feelslikeF);
        dest.writeValue(windchillC);
        dest.writeValue(windchillF);
        dest.writeValue(heatindexC);
        dest.writeValue(heatindexF);
        dest.writeValue(dewpointC);
        dest.writeValue(dewpointF);
        dest.writeValue(willItRain);
        dest.writeValue(chanceOfRain);
        dest.writeValue(willItSnow);
        dest.writeValue(chanceOfSnow);
        dest.writeValue(visKm);
        dest.writeValue(visMiles);
        dest.writeValue(gustMph);
        dest.writeValue(gustKph);
    }

    public int describeContents() {
        return 0;
    }

}