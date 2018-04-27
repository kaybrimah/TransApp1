package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 05/08/2017.
 */

public class EmergencyContactResponse {

    @SerializedName("error")
    private String error;

    @SerializedName("longitude")
    private String message;

    public EmergencyContactResponse() {
    }

    public EmergencyContactResponse(String error, String message) {
        this.error = error;
        this.message = message;
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
