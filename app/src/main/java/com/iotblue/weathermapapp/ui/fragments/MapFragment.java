package com.iotblue.weathermapapp.ui.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.iotblue.weathermapapp.R;
import com.iotblue.weathermapapp.application.MyApp;
import com.iotblue.weathermapapp.callbacks.LocationInfoCallBack;
import com.iotblue.weathermapapp.databinding.MapFragmentBinding;
import com.iotblue.weathermapapp.helper.MyConstants;
import com.iotblue.weathermapapp.util.DialogueUtily;
import com.iotblue.weathermapapp.util.MapsUtily;
import com.iotblue.weathermapapp.util.PermissionUtily;
import com.iotblue.weathermapapp.util.SettingManager;
import com.iotblue.weathermapapp.viewmodel.main.MainViewModel;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMyLocationButtonClickListener {

    private MainViewModel mViewModel;
    private SupportMapFragment mapFragment;
    GoogleMap mMap;
    @Inject
    MapsUtily mapsUtily;
    @Inject
    PermissionUtily permissionUtily;
    @Inject
    SettingManager settingManager;
    @Inject
    DialogueUtily dialogueUtily;
    private FragmentActivity mContext;
    private MapFragmentBinding data;
    private Marker currentMarker;
    private Address address;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mContext = (FragmentActivity) context;
        }
        //inject dagger
        ((MyApp) mContext.getApplication()).getMainComponent()
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.map_fragment, container, false);
        data = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);
        initGMap();
        initUI();
        return data.getRoot();
    }

    private void initUI() {
        data.locLottieView.setAnimation(R.raw.location_anim);
    }

    private void initGMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null && getFragmentManager() != null) {
            mapFragment = SupportMapFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.map, mapFragment).commit();

            checkForGPSEnabled();
        }
    }

    private void checkForGPSEnabled() {
        if (!settingManager.isGPSEnabled(mContext)) {
            showGPSAlertDialogue();
        } else
            checkPermission();
    }

    private void showGPSAlertDialogue() {
        assert getFragmentManager() != null;
        dialogueUtily.createDialouge(this,
                getString(R.string.gps_disabled),
                "",
                getString(R.string.open_settings),
                getString(R.string.cancel),
                MyConstants.ALERT_DIALOGUE_GPS_REQ)
                .show(getFragmentManager(), "");
    }

    private void showLocationPermissionAlertDialogue() {
        assert getFragmentManager() != null;
        dialogueUtily.createDialouge(this,
                getString(R.string.warning),
                getString(R.string.missing_perm_msg),
                getString(R.string.ok),
                getString(R.string.no),
                MyConstants.ALERT_DIALOGUE_LOCATION_REQ)
                .show(getFragmentManager(), "LocationAlertDialogue");
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionUtily.hasPermission(this, null)) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    //after onCreateView
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MyConstants.LOCATION_REQ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    mapFragment.getMapAsync(this);
                } else {
                    //permission denied
                    showLocationPermissionAlertDialogue();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MyConstants.ALERT_DIALOGUE_LOCATION_REQ:
                if (resultCode == RESULT_OK) {
                    checkPermission();
                }
                break;
            case MyConstants.ALERT_DIALOGUE_GPS_REQ:
                if (resultCode == RESULT_OK) {
                    Intent callGPSSettingIntent = new Intent(
                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(callGPSSettingIntent, MyConstants.GPS_SETTINGS_REQ_CODE);
                }
                break;
            case MyConstants.GPS_SETTINGS_REQ_CODE:
                if (settingManager.isGPSEnabled(mContext)) {
                    checkPermission();
                } else
                    showGPSAlertDialogue();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.d("sec");
        mMap = googleMap;
        mapsUtily.setMap(googleMap);
        currentMarker = mapsUtily.addMarker("egypt",
                new LatLng(30.0444, 31.2357));
        mapsUtily.showCurrentLocationBtn(true);
        mapsUtily.showZoomBtn(true);
        mapsUtily.showCompassBtn(true);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (mapsUtily.getLocationInfo(mContext, latLng) != null)
            address = mapsUtily.getLocationInfo(mContext, latLng);

        data.addressText.setText(String.format("%s, %s, %s", address.getLocality(), address.getCountryName(), address.getAdminArea()));
        data.cardView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        data.cardView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        try {
            mapsUtily.getLocationTask(mContext).addOnSuccessListener(mContext, location -> {
                currentMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            });
        } catch (SecurityException e) {
            Timber.e(e);
        }
        return false;
    }
}
