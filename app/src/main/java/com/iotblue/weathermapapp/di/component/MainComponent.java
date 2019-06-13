package com.iotblue.weathermapapp.di.component;

import com.iotblue.weathermapapp.di.module.AppModule;
import com.iotblue.weathermapapp.ui.fragments.MapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Singleton
@Component(modules = {AppModule.class})
public interface MainComponent {

    void inject(MapFragment mapFragment);

}
