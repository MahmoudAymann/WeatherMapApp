package com.iotblue.weathermapapp.util;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.iotblue.weathermapapp.callbacks.LocationInfoCallBack;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/

@Singleton
public class MapsUtily {

    private GoogleMap mMap;

    @Inject
    PermissionUtily permissionUtily;

    @Inject
    public MapsUtily() {
    }

    public Marker addMarker(String title, LatLng latLng) {

        if (mMap != null) {
            MarkerOptions options = new MarkerOptions();
            options.title(title)
                    .position(latLng);

            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                    latLng, //coordinates
                    16); // zoom
            mMap.animateCamera(location);
            return mMap.addMarker(options);
        } else {
            Timber.d("google map in null");
            return null;
        }
    }

    public void showCurrentLocationBtn(boolean show) {
        try {
            if (show)
                mMap.setMyLocationEnabled(true);
            else
                mMap.setMyLocationEnabled(false);
        } catch (SecurityException e) {
            Timber.e(e);
        }
    }

    public void showZoomBtn(boolean show) {
        if (show)
            mMap.getUiSettings().setZoomControlsEnabled(true);
        else
            mMap.getUiSettings().setZoomControlsEnabled(false);
    }

    public void showCompassBtn(boolean show) {
        if (show)
            mMap.getUiSettings().setCompassEnabled(true);
        else
            mMap.getUiSettings().setCompassEnabled(false);
    }

    public void setMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public Task<Location> getLocationTask(Context mContext) {
        try {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
            return fusedLocationClient.getLastLocation();
        } catch (SecurityException e) {
            Timber.e(e);
            return null;
        }
    }

    public Address getLocationInfo(Context context, LatLng latLng) {
           Geocoder geocoder = new Geocoder(context, Locale.getDefault());
           try {
               List<Address> address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
               return address.get(0);
           } catch (Exception e1) {
               Timber.e(e1);
               return null;
           }
    }

}
