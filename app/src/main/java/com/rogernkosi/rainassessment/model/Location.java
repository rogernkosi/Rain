package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Location implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("tz_id")
    @Expose
    private String tzId;
    @SerializedName("localtime_epoch")
    @Expose
    private Integer localtimeEpoch;
    @SerializedName("localtime")
    @Expose
    private String localtime;

    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {
        @SuppressWarnings({"unchecked"})
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }
    };

    protected Location(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.region = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lon = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tzId = ((String) in.readValue((String.class.getClassLoader())));
        this.localtimeEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.localtime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTzId() {
        return tzId;
    }

    public void setTzId(String tzId) {
        this.tzId = tzId;
    }

    public Integer getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public void setLocaltimeEpoch(Integer localtimeEpoch) {
        this.localtimeEpoch = localtimeEpoch;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("region", region).append("country", country).append("lat", lat).append("lon", lon).append("tzId", tzId).append("localtimeEpoch", localtimeEpoch).append("localtime", localtime).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(localtime).append(country).append(tzId).append(localtimeEpoch).append(name).append(lon).append(region).append(lat).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Location) == false) {
            return false;
        }
        Location rhs = ((Location) other);
        return new EqualsBuilder().append(localtime, rhs.localtime).append(country, rhs.country).append(tzId, rhs.tzId).append(localtimeEpoch, rhs.localtimeEpoch).append(name, rhs.name).append(lon, rhs.lon).append(region, rhs.region).append(lat, rhs.lat).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(region);
        dest.writeValue(country);
        dest.writeValue(lat);
        dest.writeValue(lon);
        dest.writeValue(tzId);
        dest.writeValue(localtimeEpoch);
        dest.writeValue(localtime);
    }

    public int describeContents() {
        return 0;
    }

}
