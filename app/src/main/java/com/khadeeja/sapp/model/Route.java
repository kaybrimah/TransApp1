package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Owner G on 29/07/2017.
 */

public class Route {
    @SerializedName("legs")
    private List<Legs> legs;

    @SerializedName("overview_polyline")
    private OverviewPolyLine overviewPolyLine;

    public List<Legs> getLegs() {
        return legs;
    }


    public OverviewPolyLine getOverviewPolyLine() {
        return overviewPolyLine;
    }
}
