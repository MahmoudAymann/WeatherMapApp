package com.maymanm.weathermapapp.data.network;

import com.maymanm.weathermapapp.data.models.WeatherModel;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public interface ApiService
{
    @GET("weather/")
    Single<WeatherModel> loadChanges(@QueryMap HashMap<String,Object>data);
}
