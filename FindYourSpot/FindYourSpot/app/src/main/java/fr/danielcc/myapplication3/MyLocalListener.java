package fr.danielcc.myapplication3;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

class MyLocationListener implements LocationListener {

    private double Lat;
    private double Lgt;

    @Override
    public void onLocationChanged(Location loc) {
        Lat = loc.getLatitude();
        Lgt = loc.getLongitude();
    }

    public double getLat() {
        return Lat;
    }

    public double getLgt() {
        return Lgt;
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
