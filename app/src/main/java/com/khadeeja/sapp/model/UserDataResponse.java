package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 15/08/2017.
 */

public class UserDataResponse {

    @SerializedName("error")
    private String error;


    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private UserInfo user;


    public UserDataResponse() {

    }


    public UserDataResponse(String error, String message) {
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

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
