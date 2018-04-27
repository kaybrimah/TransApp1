package com.khadeeja.sapp.custom;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Owner G on 28/08/2017.
 */

public class MySharedPreference {
    Context context;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    public MySharedPreference(Context context) {
        this.context = context;
        this.sharedPreferences =  context.getSharedPreferences("pndsharedpref", context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void writeStringdata(String key, String value){
        editor.putString(key, value);
        editor.commit();

    }

    public void writeDoubledata(String key, double value){
        editor.putLong(key, Double.doubleToLongBits(value));
        editor.commit();
    }

    public String readStringData(String key){

        String value =  sharedPreferences.getString(key,null);
        return value;
    }

    public Double readDoubledata(String key){
        double value =  Double.longBitsToDouble(sharedPreferences.getLong(key,0));
        return value;
    }

    public void clearCount( String key) {
        editor.remove(key);
        editor.commit();
    }

    public int getCount() {
        int count = sharedPreferences.getInt("count", -1);
        return count;
    }
}
