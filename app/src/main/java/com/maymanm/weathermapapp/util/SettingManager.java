package com.maymanm.weathermapapp.util;

import android.content.Context;
import android.location.LocationManager;

import androidx.fragment.app.FragmentActivity;

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
