package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;





import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;


public class MainActivity extends  Activity implements LocationListener, NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth fAuth;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar mToolbar;
    private ActionBarDrawerToggle t;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    private int STORAGE_PERMISSION_CODE=1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        super.onCreate(savedInstanceState);
        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
        setContentView(R.layout.activity_main);
        //Getting the edittext and button instance
                drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav);
        txtLat = (TextView) findViewById(R.id.location);
                            mToolbar=findViewById(R.id.Toolbar);

        setActionBar(mToolbar);
        getActionBar().setDisplayShowTitleEnabled(true);



        navigationView.bringToFront();;
        t = new ActionBarDrawerToggle(this, drawerLayout,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(t);
        t.syncState();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);



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
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }


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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_medical:
                    Intent intent =new Intent(MainActivity.this,Medical_Details.class);
                    startActivity(intent);
                    break;
            case R.id.nav_emergency:
                intent =new Intent(MainActivity.this,Emergency_Contacts.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String userID = fAuth.getCurrentUser().getUid();
                DatabaseReference myRef = database.getReference(userID);
                myRef.child("Status").setValue("healthy");
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
                break;
            case R.id.nav_info:intent =new Intent(MainActivity.this,info.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent =new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
