package com.iotblue.weathermapapp.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.iotblue.weathermapapp.helper.MyConstants;
import com.iotblue.weathermapapp.ui.fragments.MapFragment;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Singleton
public class PermissionUtily {

    String[] locationList = new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION};
    String[] contactsList = new String[]{READ_CONTACTS};

    @Inject
    public PermissionUtily() {

    }

    @RequiresApi(api = M) // or higher
    private boolean isGranteed(FragmentActivity contextActivity, String permission) {
        return contextActivity.checkSelfPermission(permission) == PERMISSION_GRANTED;
    }

    @RequiresApi(api = M)
    public boolean hasPermission(Fragment contextFragment, FragmentActivity contextActivity) {
        if (contextFragment != null) {
            Timber.d("frag");
            if (!isGranteed(Objects.requireNonNull(contextFragment.getActivity()), ACCESS_FINE_LOCATION)
                    && !isGranteed(contextFragment.getActivity(), ACCESS_COARSE_LOCATION)) {
                //override onRequest in fragment
                contextFragment.requestPermissions(locationList,
                        MyConstants.LOCATION_REQ_CODE);
                return false;
            }else
                return true;
        }//end its fragment
        else {
            if (!isGranteed(contextActivity, ACCESS_FINE_LOCATION)
                    && !isGranteed(contextActivity, ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(contextActivity,
                        locationList,
                        MyConstants.LOCATION_REQ_CODE);
                return false;
            }else
                return true;
        }//end its activity
    }//end method

//    public void setContext(Fragment contextFragment) {
//        this.contextFragment = contextFragment;
//    }
//    public void setContext(AppCompatActivity contextAppCompatActivity) {
//        this.contextAppCompatActivity = contextAppCompatActivity;
//    }
}
