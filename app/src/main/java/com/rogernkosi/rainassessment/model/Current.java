package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Current implements Parcelable {
    @SerializedName("last_updated_epoch")
    @Expose
    private Integer lastUpdatedEpoch;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
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
    @SerializedName("vis_km")
    @Expose
    private Double visKm;
    @SerializedName("vis_miles")
    @Expose
    private Double visMiles;
    @SerializedName("uv")
    @Expose
    private Double uv;
    @SerializedName("gust_mph")
    @Expose
    private Double gustMph;
    @SerializedName("gust_kph")
    @Expose
    private Double gustKph;

    public final static Parcelable.Creator<Current> CREATOR = new Creator<Current>() {
        @SuppressWarnings({"unchecked"})
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        public Current[] newArray(int size) {
            return (new Current[size]);
        }
    };

    protected Current(Parcel in) {
        this.lastUpdatedEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastUpdated = ((String) in.readValue((String.class.getClassLoader())));
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
        this.visKm = ((Double) in.readValue((Double.class.getClassLoader())));
        this.visMiles = ((Double) in.readValue((Double.class.getClassLoader())));
        this.uv = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gustMph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gustKph = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Current() {
    }

    public Integer getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Integer lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
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
        return new ToStringBuilder(this).append("lastUpdatedEpoch", lastUpdatedEpoch).append("lastUpdated", lastUpdated).append("tempC", tempC).append("tempF", tempF).append("isDay", isDay).append("condition", condition).append("windMph", windMph).append("windKph", windKph).append("windDegree", windDegree).append("windDir", windDir).append("pressureMb", pressureMb).append("pressureIn", pressureIn).append("precipMm", precipMm).append("precipIn", precipIn).append("humidity", humidity).append("cloud", cloud).append("feelslikeC", feelslikeC).append("feelslikeF", feelslikeF).append("visKm", visKm).append("visMiles", visMiles).append("uv", uv).append("gustMph", gustMph).append("gustKph", gustKph).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tempF).append(precipMm).append(uv).append(feelslikeC).append(gustMph).append(gustKph).append(windDir).append(pressureIn).append(precipIn).append(isDay).append(cloud).append(lastUpdated).append(condition).append(windMph).append(visKm).append(windKph).append(humidity).append(feelslikeF).append(windDegree).append(visMiles).append(pressureMb).append(lastUpdatedEpoch).append(tempC).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Current) == false) {
            return false;
        }
        Current rhs = ((Current) other);
        return new EqualsBuilder().append(tempF, rhs.tempF).append(precipMm, rhs.precipMm).append(uv, rhs.uv).append(feelslikeC, rhs.feelslikeC).append(gustMph, rhs.gustMph).append(gustKph, rhs.gustKph).append(windDir, rhs.windDir).append(pressureIn, rhs.pressureIn).append(precipIn, rhs.precipIn).append(isDay, rhs.isDay).append(cloud, rhs.cloud).append(lastUpdated, rhs.lastUpdated).append(condition, rhs.condition).append(windMph, rhs.windMph).append(visKm, rhs.visKm).append(windKph, rhs.windKph).append(humidity, rhs.humidity).append(feelslikeF, rhs.feelslikeF).append(windDegree, rhs.windDegree).append(visMiles, rhs.visMiles).append(pressureMb, rhs.pressureMb).append(lastUpdatedEpoch, rhs.lastUpdatedEpoch).append(tempC, rhs.tempC).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lastUpdatedEpoch);
        dest.writeValue(lastUpdated);
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
        dest.writeValue(visKm);
        dest.writeValue(visMiles);
        dest.writeValue(uv);
        dest.writeValue(gustMph);
        dest.writeValue(gustKph);
    }

    public int describeContents() {
        return 0;
    }

}
