package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 07/09/2017.
 */

public class DispatcherRequest {

    @SerializedName("distance")
    float distance;

    @SerializedName("duration")
    double duration;

    @SerializedName("vehicle")
    String  vehicle;


    @SerializedName("start_loc")
    StartLoc  startLoc;

    @SerializedName("end_loc")
    EndLoc endLoc;

    public DispatcherRequest(float distance, double duration, StartLoc startLoc, EndLoc endLoc) {
        this.distance = distance;
        this.duration = duration;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
    }

    public DispatcherRequest(float distance, double duration, String vehicle, StartLoc startLoc, EndLoc endLoc) {
        this.distance = distance;
        this.duration = duration;
        this.vehicle = vehicle;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public StartLoc getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(StartLoc startLoc) {
        this.startLoc = startLoc;
    }

    public EndLoc getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(EndLoc endLoc) {
        this.endLoc = endLoc;
    }
}
