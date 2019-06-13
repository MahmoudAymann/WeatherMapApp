package com.iotblue.weathermapapp.util;

import android.content.Context;
import android.location.LocationManager;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
@Singleton
public class SettingManager {
    @Inject
    public SettingManager() {

    }

    public boolean isGPSEnabled(FragmentActivity context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert manager != null;
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


}
