package com.khadeeja.sapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Owner G on 13/07/2017.
 */

public class PickNDropApiClient {


    public static Retrofit getPickNDropApiClient(String url){

        //creating an instance of retrofit
         Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        return retrofit;
    }
}
