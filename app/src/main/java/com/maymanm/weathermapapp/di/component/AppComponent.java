package com.maymanm.weathermapapp.di.component;

import android.app.Application;

import com.maymanm.weathermapapp.application.MyApp;
import com.maymanm.weathermapapp.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
/**
 * Created by MahmoudAyman on 6/20/2019.
 **/
@Singleton
@Component(modules = {
        AppModule.class

})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }


    void inject(MyApp myApp);


}
