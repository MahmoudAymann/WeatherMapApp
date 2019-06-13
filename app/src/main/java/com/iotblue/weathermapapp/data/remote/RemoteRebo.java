package com.iotblue.weathermapapp.data.remote;

import android.content.Context;

import com.iotblue.weathermapapp.data.models.WeatherModel;
import com.iotblue.weathermapapp.data.network.ApiIF;
import com.iotblue.weathermapapp.data.network.CryptoCompareAPI;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class RemoteRebo
{
    private Retrofit retrofit;
    public RemoteRebo(Context   c)
    {
retrofit= CryptoCompareAPI.getClient(c);
    }
public Single<WeatherModel>getData(HashMap<String,Object> params)
{
    ApiIF gerritAPI = retrofit.create(ApiIF.class);
 try {
     return   gerritAPI.loadChanges(params);

 }catch (Exception e)
 {
     e.printStackTrace();
     return Single.error(e);
 }

}
}
