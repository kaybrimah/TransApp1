package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 15/08/2017.
 */

public class Device {
    @SerializedName("user_device_id")
    String user_device_id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("platform")
    String platform;

    @SerializedName("device_id")
    String device_id;

    @SerializedName("push_registration_id")
    String push_registration_id;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

    public Device() {

    }

    public Device(String user_device_id, String user_id, String platform, String device_id, String push_registration_id, String created_at, String updated_at) {

        this.user_device_id = user_device_id;
        this.user_id = user_id;
        this.platform = platform;
        this.device_id = device_id;
        this.push_registration_id = push_registration_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPush_registration_id() {
        return push_registration_id;
    }

    public void setPush_registration_id(String push_registration_id) {
        this.push_registration_id = push_registration_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
