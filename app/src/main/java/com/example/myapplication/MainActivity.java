package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends  Activity implements LocationListener {
    FirebaseAuth fAuth;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the edittext and button instance
        txtLat = (TextView) findViewById(R.id.location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        button1 = (Button) findViewById(R.id.pol);
        button2 = (Button) findViewById(R.id.amb);
        button3 = (Button) findViewById(R.id.fire);
        button4 = (Button) findViewById(R.id.send);
        //Button login_btn = findViewById(R.id.log_btn);
       // Button register_btn = findViewById(R.id.res_btn);
        Button logout_btn = findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            //login_btn.setVisibility(View.GONE);
            //register_btn.setVisibility((View.GONE));
            logout_btn.setVisibility(View.VISIBLE);
        }
        else
        {
            logout_btn.setVisibility(View.GONE);
            startActivity(new Intent(getApplicationContext(),login.class));
            finish();
        }
       // button5 = (Button) findViewById(R.id.login);
       // button6 = (Button) findViewById(R.id.register);
                //address for the address bar

        //Performing action on button click
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String userID = fAuth.getCurrentUser().getUid();
                DatabaseReference myRef = database.getReference(userID);
                myRef.child("Status").setValue("healthy");
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:100"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:108"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:101"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:101"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String userID = fAuth.getCurrentUser().getUid();
                DatabaseReference myRef = database.getReference(userID);
                myRef.child("Status").setValue("Emergency");
            }

        });

/*
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),login.class);
                startActivityForResult(myIntent,0);


            }
        });

        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),register.class);
                startActivityForResult(myIntent,0);


            }
        });

*/
    }
    @Override
    public void onLocationChanged(Location location) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
     if(fAuth.getCurrentUser()!=null) {
         String userID = fAuth.getCurrentUser().getUid();
         DatabaseReference myRef = database.getReference(userID);
         myRef.child("Latitude").setValue(location.getLatitude());
         myRef.child("Longitude").setValue(location.getLongitude());
     }
        txtLat = (TextView) findViewById(R.id.location);
        txtLat.setText("Latitude:" + location.getLatitude() + ",\nLongitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

}
