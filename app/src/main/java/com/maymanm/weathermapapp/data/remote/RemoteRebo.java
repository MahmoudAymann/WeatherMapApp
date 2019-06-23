package com.maymanm.weathermapapp.data.remote;

import android.content.Context;

import com.maymanm.weathermapapp.data.models.WeatherModel;
import com.maymanm.weathermapapp.data.network.ApiService;
import com.maymanm.weathermapapp.data.network.CryptoCompareAPI;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class RemoteRebo {
    private Retrofit retrofit;

    public RemoteRebo(Context c) {
        retrofit = CryptoCompareAPI.getClient(c);
    }

    public Single<WeatherModel> getData(HashMap<String, Object> params) {
        ApiService gerritAPI = retrofit.create(ApiService.class);
        try {
            return gerritAPI.loadChanges(params);

        } catch (Exception e) {
            e.printStackTrace();
            return Single.error(e);
        }

    }
}
