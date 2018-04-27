package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 16/08/2017.
 */

public class MyLocation {
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("created_at")
    private MyLocation created_at;

    @SerializedName("user_location_id")
    private String user_location_id;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public MyLocation getCreated_at() {
        return created_at;
    }

    public void setCreated_at(MyLocation created_at) {
        this.created_at = created_at;
    }

    public String getUser_location_id() {
        return user_location_id;
    }

    public void setUser_location_id(String user_location_id) {
        this.user_location_id = user_location_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
