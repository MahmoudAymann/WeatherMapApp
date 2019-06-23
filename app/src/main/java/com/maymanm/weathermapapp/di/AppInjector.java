package com.maymanm.weathermapapp.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maymanm.weathermapapp.application.MyApp;
import com.maymanm.weathermapapp.di.component.DaggerAppComponent;

import dagger.android.AndroidInjection;
import dagger.android.DaggerApplication;

/**
 * Created by MahmoudAyman on 6/20/2019.
 **/

public class AppInjector {

    private AppInjector() {
    }

    public static void init(MyApp app){
        DaggerAppComponent.builder().application(app)
                .build().inject(app);

        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                handleOnActivityCreated(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    private static void handleOnActivityCreated(Activity activity) {
        if (activity instanceof Injectable)
            AndroidInjection.inject(activity);
    }

}
