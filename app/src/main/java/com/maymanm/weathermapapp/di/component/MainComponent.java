package com.maymanm.weathermapapp.di.component;

import androidx.appcompat.app.AppCompatActivity;

import com.maymanm.weathermapapp.di.module.MyModule;
import com.maymanm.weathermapapp.ui.fragments.MapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Component(modules = {MyModule.class})
@Singleton
public interface MainComponent {
    void inject(MapFragment mapFragment);
    void inject(AppCompatActivity activity);
}
