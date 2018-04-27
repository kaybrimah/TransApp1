package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 15/08/2017.
 */

public class Session {

    @SerializedName("user_device_id")
    String user_device_id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("auth_token")
    String auth_token;

    @SerializedName("IP")
    String IP;

    @SerializedName("expired")
    String expired;

    public Session() {
    }

    public Session(String user_device_id, String user_id, String auth_token, String IP, String expired) {
        this.user_device_id = user_device_id;
        this.user_id = user_id;
        this.auth_token = auth_token;
        this.IP = IP;
        this.expired = expired;
    }

    public String getUser_device_id() {
        return user_device_id;
    }

    public void setUser_device_id(String user_device_id) {
        this.user_device_id = user_device_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
