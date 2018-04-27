package com.khadeeja.sapp.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.khadeeja.sapp.custom.GPSTracker;
import com.khadeeja.sapp.custom.MyLocationListener;
import com.khadeeja.sapp.custom.MySharedPreference;
import com.khadeeja.sapp.model.DirectionResults;
import com.khadeeja.sapp.retrofit.PickNDropApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.khadeeja.sapp.model.RouteDecode.decodePoly;

public class SearchMap extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    String TAG = "searchmap";
    FragmentManager fragmentManager;

    MySharedPreference mySharedPreference;
    LocationManager locationManager;
    MyLocationListener listener;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    FusedLocationProviderClient mFusedLocationClient;
    double u_latitude, u_longitude, d_latitude, d_longitude;


    EditText currentlocation, destination;
    TextView displayname, distancevalue, pricevalue, pickupadd, destinationadd;

    Polyline line;
    ImageView requestimage;


    // GPSTracker class
    GPSTracker gps;

    String my_auth_token;
    String my_user_id;
    String my_device_id;
    double my_distance, my_time;

    private static final LatLngBounds mybounds = new LatLngBounds(new LatLng(-0, 0), new LatLng(0, 0));
    private final static String API_KEY = "";
    private static final int TRIPDETAILS_STATE = 0;
    private static final int REQUESTRIDE_STATE = 1;

    private static final long POLLING_FREQ = 1000 * 10;
    private static final long FASTEST_UPDATE_FREQ = 1000 * 5;
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final int PICK_UP_PLACE_PICKER_REQUEST = 2;

    private boolean refreshmap = false;
    private boolean placepicker = false;
    private List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getLocationData();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //loadLocationFragement();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        currentlocation = (EditText) findViewById(R.id.currentlocat);
        destination = (EditText) findViewById(R.id.destlocat);

        currentlocation.setOnClickListener(this);
        destination.setOnClickListener(this);

        destination.setFocusable(false);
        currentlocation.setFocusable(false);

        // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }


    public void moveMap(double latitude, double longitude, String address) {

        mMap.clear();

        Log.d(TAG, "showmaplocation:success");
        LatLng latLng = new LatLng(latitude, longitude);
        LatLng sydney = new LatLng(-33.852, 151.211);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(14)
                .build();

        if (mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.addMarker(new MarkerOptions().position(latLng).title(address));
            // mMap.addMarker(new MarkerOptions().position(latLng).title(address).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
            //mMap.addMarker(new MarkerOptions().position(latLng).title(address).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
            // mMap.getUiSettings().setZoomControlsEnabled(true);
        }

    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();
    }

    private void getLocationData() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(POLLING_FREQ);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_FREQ);

        buildGoogleApiClient();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    public void onStart() {
        super.onStart();
        //hidekeyboard(getApplicationContext());
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());

        mySharedPreference = new MySharedPreference(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener(this);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,listener);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);



        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {

                // Got last known location. In some rare situations this can be null.
                Log.d(TAG, "getlocation:success");


                //Check if Current Location is not null
                if (location != null) {


                    u_latitude = location.getLatitude();
                    u_longitude = location.getLongitude();
                    //Move Map to current location after 3 minutes
                    Log.d(TAG, "Current Location fired.........");


                    moveMap(u_latitude, u_longitude, "You are here");
                    setCurrentLocationAddress(u_latitude,u_longitude);
                }

            }


        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {

            Place place = PlacePicker.getPlace(this, data);
            String placename = String.valueOf(place.getName());
            String placeID = place.getId();
            LatLng placelatLng = place.getLatLng();

            double placelat = placelatLng.latitude;
            double placelong = placelatLng.longitude;

            d_latitude = placelat;
            d_longitude = placelong;
            Geocoder geocoder = new Geocoder(this);
            String address ="";

            try
            {
                List<Address> addresses = geocoder.getFromLocation(placelat,placelong, 1);
                address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(1);
                destination.setText(address);

                Log.d(TAG, "City is " + city);
                Log.d(TAG, "Address is " + address);
                //String country = addresses.get(0).getAddressLine(2);

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            getDirectionResults(u_latitude,u_longitude,placelat,placelong,"You are here", address);


            Log.d(TAG, "Destination Placename is : " + placename);
            Log.d(TAG, "Destination PlaceID is : " + placeID);
            Log.d(TAG, "Destination PlacelatLng is : " + placelatLng);
            Log.d(TAG, "Destination Placelat is : " + placelat);
            Log.d(TAG, "Destination Placelong is : " + placelong);
        }
        if (requestCode == PICK_UP_PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {


            Place place = PlacePicker.getPlace(this, data);
            String placename = String.valueOf(place.getName());
            String placeID = place.getId();
            LatLng placelatLng = place.getLatLng();

            double placelat = placelatLng.latitude;
            double placelong = placelatLng.longitude;

            u_latitude = placelat;
            u_longitude = placelong;
            String address ="";

            Geocoder geocoder = new Geocoder(this);
            try
            {
                List<Address> addresses = geocoder.getFromLocation(placelat,placelong, 1);
                address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(1);
                currentlocation.setText(address);

                Log.d(TAG, "City is " + city);
                Log.d(TAG, "Address is " + address);
                //String country = addresses.get(0).getAddressLine(2);

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            getDirectionResults(u_latitude,u_longitude,d_latitude,d_longitude,"You are here", address);

            Log.d(TAG, "PickUp Placename is : " + placename);
            Log.d(TAG, "PickUp PlaceID is : " + placeID);
            Log.d(TAG, "PickUp PlacelatLng is : " + placelatLng);
            Log.d(TAG, "PickUp Placelat is : " + placelat);
            Log.d(TAG, "PickUp Placelong is : " + placelong);
        }
    }

    public void getDirectionResults (double st_lat, double st_long, double end_lat, double end_long, String startadd, String destadd){

        mMap.clear();
        //hidekeyboard(getApplicationContext());

        LatLng startlatLng = new LatLng(st_lat, st_long);
        LatLng endlatLng = new LatLng(end_lat, end_long);

        mMap.addMarker(new MarkerOptions().position(startlatLng).title(startadd));
        mMap.addMarker(new MarkerOptions().position(endlatLng).title(destadd));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(startlatLng)
                .target(endlatLng)
                .zoom(10)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        String str_origin = startlatLng.latitude+","+startlatLng.longitude;

        // Destination of route
        String str_dest = endlatLng.latitude+","+endlatLng.longitude;



        String base_url = "http://maps.googleapis.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PickNDropApiInterface pickNDropApiInterface = retrofit.create(PickNDropApiInterface.class);
        Call<DirectionResults> call = pickNDropApiInterface.getJson(str_origin , str_dest);
        call.enqueue(new Callback<DirectionResults>() {
            @Override
            public void onResponse(Call<DirectionResults> call, Response<DirectionResults> response) {


                Log.d(TAG, "DirectionResults Full JSON Response" + new Gson().toJson(response));


                try {
                    //Remove previous line from map
                    if (line != null) {
                        line.remove();
                    }

                    if(response.body().getRoutes().size() > 30){

                    }

                    // This loop will go through all the results and add marker on each location.
                    for (int i = 0; i < response.body().getRoutes().size(); i++) {
                        Log.i(TAG, "Route Size " +response.body().getRoutes().size());

                        Log.i(TAG, "Leg Size " +response.body().getRoutes().get(i).getLegs().size());

                        String distance = response.body().getRoutes().get(i).getLegs().get(i).getDistance().getText();
                        String time = response.body().getRoutes().get(i).getLegs().get(i).getDuration().getText();

                        //ShowDistanceDuration.setText("Distance:" + distance + ", Duration:" + time);
                        String encodedString = response.body().getRoutes().get(0).getOverviewPolyLine().getPoints();
                        List<LatLng> list = decodePoly(encodedString);
                        line = mMap.addPolyline(new PolylineOptions()
                                .addAll(list)
                                .width(11)
                                .color(Color.BLACK)
                                .geodesic(true)
                        );



                        //loadRequestFragement();


                        Log.i(TAG, "Distance : " +distance);

                        //getTripEstPriceResponse (56,"business");
                        float userdistance = response.body().getRoutes().get(i).getLegs().get(i).getDistance().getValue();
                        float triptime = response.body().getRoutes().get(i).getLegs().get(i).getDuration().getValue();


                    }
                } catch (Exception e) {
                    Log.e(TAG, "Trip Details" + e );
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DirectionResults> call, Throwable t) {

                Log.e(TAG,"onFailure", t);

            }
        });

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void loadPlacePicker() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void loadPlacePicker2() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PICK_UP_PLACE_PICKER_REQUEST);

        }
        catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    private void setCurrentLocationAddress(double u_lat,double u_long){
        Geocoder geocoder = new Geocoder(this);
        String address ="";
        try
        {
            List<Address> addresses = geocoder.getFromLocation(u_lat,u_long, 1);
            address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getAddressLine(1);
            currentlocation.setText(address);

            Log.d(TAG, "City is " + city);
            Log.d(TAG, "Address is " + address);
            //String country = addresses.get(0).getAddressLine(2);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.currentlocat:
                Log.d(TAG, "Current Location Edit Text Clicked");
                loadPlacePicker2();
                break;

            case R.id.destlocat:
                Log.d(TAG, "Destination Edit Text Clicked");
                loadPlacePicker();
                break;
        }
    }
}
