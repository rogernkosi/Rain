package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Forecast implements Parcelable {

    @SerializedName("forecastday")
    @Expose
    private List<Forecastday> forecastday = null;
    public final static Parcelable.Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @SuppressWarnings({"unchecked"})
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        public Forecast[] newArray(int size) {
            return (new Forecast[size]);
        }
    };

    protected Forecast(Parcel in) {
        in.readList(this.forecastday, (Forecastday.class.getClassLoader()));
    }

    public Forecast() {
    }

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("forecastday", forecastday).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(forecastday).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Forecast) == false) {
            return false;
        }
        Forecast rhs = ((Forecast) other);
        return new EqualsBuilder().append(forecastday, rhs.forecastday).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(forecastday);
    }

    public int describeContents() {
        return 0;
    }

}