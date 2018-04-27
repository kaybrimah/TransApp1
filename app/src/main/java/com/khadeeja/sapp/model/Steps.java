package com.khadeeja.sapp.model;

import android.location.Location;

/**
 * Created by Owner G on 29/07/2017.
 */

public class Steps {

    private Location start_location;
    private Location end_location;
    private OverviewPolyLine polyline;

    public Location getStart_location() {
        return start_location;
    }

    public Location getEnd_location() {
        return end_location;
    }

    public OverviewPolyLine getPolyline() {
        return polyline;
    }

}
