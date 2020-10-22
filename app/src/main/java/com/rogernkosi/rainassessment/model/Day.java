package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Day implements Parcelable {
    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("maxtemp_f")
    @Expose
    private Double maxtempF;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
    @SerializedName("mintemp_f")
    @Expose
    private Double mintempF;
    @SerializedName("avgtemp_c")
    @Expose
    private Double avgtempC;
    @SerializedName("avgtemp_f")
    @Expose
    private Double avgtempF;
    @SerializedName("maxwind_mph")
    @Expose
    private Double maxwindMph;
    @SerializedName("maxwind_kph")
    @Expose
    private Double maxwindKph;
    @SerializedName("totalprecip_mm")
    @Expose
    private Double totalprecipMm;
    @SerializedName("totalprecip_in")
    @Expose
    private Double totalprecipIn;
    @SerializedName("avgvis_km")
    @Expose
    private Double avgvisKm;
    @SerializedName("avgvis_miles")
    @Expose
    private Double avgvisMiles;
    @SerializedName("avghumidity")
    @Expose
    private Double avghumidity;
    @SerializedName("daily_will_it_rain")
    @Expose
    private Integer dailyWillItRain;
    @SerializedName("daily_chance_of_rain")
    @Expose
    private String dailyChanceOfRain;
    @SerializedName("daily_will_it_snow")
    @Expose
    private Integer dailyWillItSnow;
    @SerializedName("daily_chance_of_snow")
    @Expose
    private String dailyChanceOfSnow;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("uv")
    @Expose
    private Double uv;

    public final static Parcelable.Creator<Day> CREATOR = new Creator<Day>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        public Day[] newArray(int size) {
            return (new Day[size]);
        }
    };

    protected Day(Parcel in) {
        this.maxtempC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maxtempF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.mintempC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.mintempF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.avgtempC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.avgtempF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maxwindMph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maxwindKph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalprecipMm = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalprecipIn = ((Double) in.readValue((Double.class.getClassLoader())));
        this.avgvisKm = ((Double) in.readValue((Double.class.getClassLoader())));
        this.avgvisMiles = ((Double) in.readValue((Double.class.getClassLoader())));
        this.avghumidity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.dailyWillItRain = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dailyChanceOfRain = ((String) in.readValue((String.class.getClassLoader())));
        this.dailyWillItSnow = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dailyChanceOfSnow = ((String) in.readValue((String.class.getClassLoader())));
        this.condition = ((Condition) in.readValue((Condition.class.getClassLoader())));
        this.uv = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Day() {
    }

    public Double getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(Double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public Double getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(Double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public Double getMintempC() {
        return mintempC;
    }

    public void setMintempC(Double mintempC) {
        this.mintempC = mintempC;
    }

    public Double getMintempF() {
        return mintempF;
    }

    public void setMintempF(Double mintempF) {
        this.mintempF = mintempF;
    }

    public Double getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(Double avgtempC) {
        this.avgtempC = avgtempC;
    }

    public Double getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(Double avgtempF) {
        this.avgtempF = avgtempF;
    }

    public Double getMaxwindMph() {
        return maxwindMph;
    }

    public void setMaxwindMph(Double maxwindMph) {
        this.maxwindMph = maxwindMph;
    }

    public Double getMaxwindKph() {
        return maxwindKph;
    }

    public void setMaxwindKph(Double maxwindKph) {
        this.maxwindKph = maxwindKph;
    }

    public Double getTotalprecipMm() {
        return totalprecipMm;
    }

    public void setTotalprecipMm(Double totalprecipMm) {
        this.totalprecipMm = totalprecipMm;
    }

    public Double getTotalprecipIn() {
        return totalprecipIn;
    }

    public void setTotalprecipIn(Double totalprecipIn) {
        this.totalprecipIn = totalprecipIn;
    }

    public Double getAvgvisKm() {
        return avgvisKm;
    }

    public void setAvgvisKm(Double avgvisKm) {
        this.avgvisKm = avgvisKm;
    }

    public Double getAvgvisMiles() {
        return avgvisMiles;
    }

    public void setAvgvisMiles(Double avgvisMiles) {
        this.avgvisMiles = avgvisMiles;
    }

    public Double getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(Double avghumidity) {
        this.avghumidity = avghumidity;
    }

    public Integer getDailyWillItRain() {
        return dailyWillItRain;
    }

    public void setDailyWillItRain(Integer dailyWillItRain) {
        this.dailyWillItRain = dailyWillItRain;
    }

    public String getDailyChanceOfRain() {
        return dailyChanceOfRain;
    }

    public void setDailyChanceOfRain(String dailyChanceOfRain) {
        this.dailyChanceOfRain = dailyChanceOfRain;
    }

    public Integer getDailyWillItSnow() {
        return dailyWillItSnow;
    }

    public void setDailyWillItSnow(Integer dailyWillItSnow) {
        this.dailyWillItSnow = dailyWillItSnow;
    }

    public String getDailyChanceOfSnow() {
        return dailyChanceOfSnow;
    }

    public void setDailyChanceOfSnow(String dailyChanceOfSnow) {
        this.dailyChanceOfSnow = dailyChanceOfSnow;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("maxtempC", maxtempC).append("maxtempF", maxtempF).append("mintempC", mintempC).append("mintempF", mintempF).append("avgtempC", avgtempC).append("avgtempF", avgtempF).append("maxwindMph", maxwindMph).append("maxwindKph", maxwindKph).append("totalprecipMm", totalprecipMm).append("totalprecipIn", totalprecipIn).append("avgvisKm", avgvisKm).append("avgvisMiles", avgvisMiles).append("avghumidity", avghumidity).append("dailyWillItRain", dailyWillItRain).append("dailyChanceOfRain", dailyChanceOfRain).append("dailyWillItSnow", dailyWillItSnow).append("dailyChanceOfSnow", dailyChanceOfSnow).append("condition", condition).append("uv", uv).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(uv).append(dailyWillItRain).append(dailyChanceOfRain).append(avgtempF).append(mintempC).append(totalprecipMm).append(dailyWillItSnow).append(totalprecipIn).append(avghumidity).append(condition).append(avgvisKm).append(mintempF).append(maxwindMph).append(maxwindKph).append(maxtempF).append(dailyChanceOfSnow).append(avgtempC).append(maxtempC).append(avgvisMiles).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Day) == false) {
            return false;
        }
        Day rhs = ((Day) other);
        return new EqualsBuilder().append(uv, rhs.uv).append(dailyWillItRain, rhs.dailyWillItRain).append(dailyChanceOfRain, rhs.dailyChanceOfRain).append(avgtempF, rhs.avgtempF).append(mintempC, rhs.mintempC).append(totalprecipMm, rhs.totalprecipMm).append(dailyWillItSnow, rhs.dailyWillItSnow).append(totalprecipIn, rhs.totalprecipIn).append(avghumidity, rhs.avghumidity).append(condition, rhs.condition).append(avgvisKm, rhs.avgvisKm).append(mintempF, rhs.mintempF).append(maxwindMph, rhs.maxwindMph).append(maxwindKph, rhs.maxwindKph).append(maxtempF, rhs.maxtempF).append(dailyChanceOfSnow, rhs.dailyChanceOfSnow).append(avgtempC, rhs.avgtempC).append(maxtempC, rhs.maxtempC).append(avgvisMiles, rhs.avgvisMiles).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxtempC);
        dest.writeValue(maxtempF);
        dest.writeValue(mintempC);
        dest.writeValue(mintempF);
        dest.writeValue(avgtempC);
        dest.writeValue(avgtempF);
        dest.writeValue(maxwindMph);
        dest.writeValue(maxwindKph);
        dest.writeValue(totalprecipMm);
        dest.writeValue(totalprecipIn);
        dest.writeValue(avgvisKm);
        dest.writeValue(avgvisMiles);
        dest.writeValue(avghumidity);
        dest.writeValue(dailyWillItRain);
        dest.writeValue(dailyChanceOfRain);
        dest.writeValue(dailyWillItSnow);
        dest.writeValue(dailyChanceOfSnow);
        dest.writeValue(condition);
        dest.writeValue(uv);
    }

    public int describeContents() {
        return 0;
    }

}