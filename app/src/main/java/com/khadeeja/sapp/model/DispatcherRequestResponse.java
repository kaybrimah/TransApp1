package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 07/09/2017.
 */

public class DispatcherRequestResponse {

    @SerializedName("error")
    String error;

    @SerializedName("message")
    String message;

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
