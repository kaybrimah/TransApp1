package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 07/09/2017.
 */

public class StartLoc {

    @SerializedName("lat")
    double latitude;

    @SerializedName("lng")
    double longtitude;

    public StartLoc(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}

