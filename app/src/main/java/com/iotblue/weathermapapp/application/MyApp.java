package com.iotblue.weathermapapp.application;

import android.app.Application;
import com.iotblue.weathermapapp.di.component.DaggerMainComponent;
import com.iotblue.weathermapapp.di.component.MainComponent;
import org.jetbrains.annotations.NotNull;
import javax.inject.Singleton;

import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Singleton
public class MyApp extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        mainComponent = DaggerMainComponent.create();
        RxJavaPlugins.setErrorHandler(Timber::e); // nothing or some logging
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree(){
            @Override
            protected String createStackElementTag(@NotNull StackTraceElement element) {
                return super.createStackElementTag(element) + " line:"+ element.getLineNumber();
            }
        });
    }
}
