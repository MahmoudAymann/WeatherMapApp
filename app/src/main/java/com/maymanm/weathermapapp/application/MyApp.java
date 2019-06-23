package com.maymanm.weathermapapp.application;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.maymanm.weathermapapp.di.AppInjector;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Singleton
public class MyApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
//    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        RxJavaPlugins.setErrorHandler(Timber::e);
//        mainComponent = DaggerMainComponent.builder()
//                .appModule(new MyModule())
//                .build();

        AppInjector.init(this);

    }//end onCreate

//    public static MainComponent getMainComponent() {
//        return mainComponent;
//    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(@NotNull StackTraceElement element) {
                return super.createStackElementTag(element) + " line:" + element.getLineNumber();
            }
        });
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
