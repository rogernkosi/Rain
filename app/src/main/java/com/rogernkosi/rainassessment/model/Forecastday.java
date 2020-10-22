package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Forecastday implements Parcelable {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_epoch")
    @Expose
    private Integer dateEpoch;
    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("hour")
    @Expose
    private List<Hour> hour = null;
    public final static Parcelable.Creator<Forecastday> CREATOR = new Creator<Forecastday>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Forecastday createFromParcel(Parcel in) {
            return new Forecastday(in);
        }

        public Forecastday[] newArray(int size) {
            return (new Forecastday[size]);
        }
    };

    protected Forecastday(Parcel in) {
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.dateEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.day = ((Day) in.readValue((Day.class.getClassLoader())));
        in.readList(this.hour, (Hour.class.getClassLoader()));
    }

    public Forecastday() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(Integer dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("date", date).append("dateEpoch", dateEpoch).append("day", day).append("hour", hour).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(dateEpoch).append(hour).append(day).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Forecastday) == false) {
            return false;
        }
        Forecastday rhs = ((Forecastday) other);
        return new EqualsBuilder().append(date, rhs.date).append(dateEpoch, rhs.dateEpoch).append(hour, rhs.hour).append(day, rhs.day).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(date);
        dest.writeValue(dateEpoch);
        dest.writeValue(day);
        dest.writeList(hour);
    }

    public int describeContents() {
        return 0;
    }

}