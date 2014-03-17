package com.example.GeoLocation;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MyActivity extends Activity implements LocationListener {
    /**
     * Called when the activity is first created.
     */

    private static final String TAG = "MyActivity";
    public Location location;
    LocationManager locationManager;
    String provider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        if(location != null){
            onLocationChanged(location);
            Log.v(TAG, "Location available!");
        } else{
            Log.e(TAG, "Location not available!");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, location.getLongitude() + " " + location.getLatitude());
        TextView t1 = (TextView)findViewById(R.id.lat);
        TextView t2 = (TextView)findViewById(R.id.lng);

        t1.setText(""+location.getLatitude());
        t2.setText(""+location.getLongitude());
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
}
