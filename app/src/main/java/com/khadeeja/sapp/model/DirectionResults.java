package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Owner G on 29/07/2017.
 */

public class DirectionResults {


    @SerializedName("routes")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }
}
