package com.rogernkosi.rainassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Condition implements Parcelable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("code")
    @Expose
    private Integer code;
    public final static Parcelable.Creator<Condition> CREATOR = new Creator<Condition>() {
        @SuppressWarnings({"unchecked"})
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        public Condition[] newArray(int size) {
            return (new Condition[size]);
        }
    };

    protected Condition(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Condition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("text", text).append("icon", icon).append("code", code).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(icon).append(text).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Condition) == false) {
            return false;
        }
        Condition rhs = ((Condition) other);
        return new EqualsBuilder().append(icon, rhs.icon).append(text, rhs.text).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(icon);
        dest.writeValue(code);
    }

    public int describeContents() {
        return 0;
    }

}