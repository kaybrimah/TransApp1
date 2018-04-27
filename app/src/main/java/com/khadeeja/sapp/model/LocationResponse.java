package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 05/08/2017.
 */

public class LocationResponse {

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    @SerializedName("Location")
    private MyLocation myLocation;

    public LocationResponse() {

    }

    public LocationResponse(String error, String message, MyLocation myLocation) {
        this.error = error;
        this.message = message;
        this.myLocation = myLocation;
    }

    public MyLocation getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(MyLocation myLocation) {
        this.myLocation = myLocation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

