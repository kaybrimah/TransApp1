package com.khadeeja.sapp.retrofit;

import com.khadeeja.sapp.model.DirectionResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Owner G on 13/07/2017.
 */

public interface PickNDropApiInterface {


    @GET("maps/api/directions/json")
    Call<DirectionResults> getJson
            (@Query("origin") String origin,
             @Query("destination") String destination);




}

