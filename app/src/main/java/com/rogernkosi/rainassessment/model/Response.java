package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Response implements Parcelable {
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public final static Parcelable.Creator<Response> CREATOR = new Creator<Response>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        public Response[] newArray(int size) {
            return (new Response[size]);
        }

    };

    protected Response(Parcel in) {
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
        this.forecast = ((Forecast) in.readValue((Forecast.class.getClassLoader())));
    }

    public Response() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("location", location).append("current", current).append("forecast", forecast).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(location).append(current).append(forecast).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Response) == false) {
            return false;
        }
        Response rhs = ((Response) other);
        return new EqualsBuilder().append(location, rhs.location).append(current, rhs.current).append(forecast, rhs.forecast).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(location);
        dest.writeValue(current);
        dest.writeValue(forecast);
    }

    public int describeContents() {
        return 0;
    }

}