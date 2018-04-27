package com.khadeeja.sapp.custom;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Owner G on 22/07/2017.
 */

public class MyLocationListener implements android.location.LocationListener {

    private static final String TAG = "locationlistner";

    String my_auth_token;
    String my_user_id;
    MySharedPreference mySharedPreference;
    Context context;


    public MyLocationListener(Context context) {
        this.context = context;
        mySharedPreference = new MySharedPreference(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    @Override
    public void onLocationChanged(Location location) {


        String auth_token = "";
        String user_id = "";
        double currentlatitude = location.getLatitude();
        double cureentlongtiude = location.getLongitude();

       // Log.i(TAG, "Location Data Received " + currentlatitude + " " + cureentlongtiude + " "  + "success");

        try {
            //sendUserCurrentLocationData(my_auth_token, my_user_id, currentlatitude, cureentlongtiude);
        }
        catch (Exception e){
            Log.d(TAG, "Send User Current Loation :" + e);
        }
        //shareCurrentLocationData(currentlatitude,cureentlongtiude);



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {


    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private void shareCurrentLocationData(double clatitude, double clongtitude) {

        mySharedPreference.writeDoubledata("l_cu_latitude",clatitude);
        mySharedPreference.writeDoubledata("l_cu_longitude",clongtitude);

        Log.d(TAG, "CurrentLocationData : " +clatitude+ clongtitude + " sent success");
    }

}
