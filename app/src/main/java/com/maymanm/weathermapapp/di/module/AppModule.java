package com.maymanm.weathermapapp.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.maymanm.weathermapapp.data.network.ApiService;
import com.maymanm.weathermapapp.di.component.ViewModelSubComponent;
import com.maymanm.weathermapapp.di.factory.ViewModelFactory;
import com.maymanm.weathermapapp.helper.MyConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MahmoudAyman on 6/20/2019.
 **/
@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Singleton
    @Provides
    ApiService apiService() {
        return new Retrofit.Builder()
                .baseUrl(MyConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build()
                .create(ApiService.class);
    }



    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder builder){
        return new ViewModelFactory(builder.build());
    }

}
