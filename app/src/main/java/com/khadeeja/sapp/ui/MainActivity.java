package com.khadeeja.sapp.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mainToolbar;

    private FirebaseAuth mAuth;

    private CardView taxiCard, strackCard, mobilemoneyCard, campusnavCard;
    public static final int MULTIPLE_PERMISSION_CODE= 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        // defining Cards
        taxiCard = (CardView) findViewById(R.id.taxi_card);
        strackCard = (CardView) findViewById(R.id.sTrack_card);
        mobilemoneyCard = (CardView) findViewById(R.id.mobileMoney_card);
        campusnavCard = (CardView) findViewById(R.id.campusNav_card);
        // Add Click listener to the cards
        taxiCard.setOnClickListener(this);
        strackCard.setOnClickListener(this);
        mobilemoneyCard.setOnClickListener(this);
        campusnavCard.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("TransApp");


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {

            sendToLogin();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_button:
                logOut();
                return true;


            default:
                return false;


        }
    }

    private void logOut() {


        mAuth.signOut();
        sendToLogin();

    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this, new String[]{ android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MULTIPLE_PERMISSION_CODE);

            }


        }
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.taxi_card:
                i = new Intent(this, Taxi.class);startActivity(i);
                break;

            case R.id.sTrack_card:
                i = new Intent(this, TrackShuttle.class);startActivity(i);
                break;

            case R.id.mobileMoney_card:
                i = new Intent(this, MobileMoney.class);startActivity(i);
                break;

            case R.id.campusNav_card:
                i = new Intent(this, SearchMap.class);startActivity(i);
                break;
                default:break;








        }
    }
}
