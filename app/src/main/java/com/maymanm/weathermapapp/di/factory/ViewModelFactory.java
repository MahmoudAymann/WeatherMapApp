package com.maymanm.weathermapapp.di.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.maymanm.weathermapapp.di.component.ViewModelSubComponent;
import com.maymanm.weathermapapp.viewmodel.DirectionsViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by MahmoudAyman on 6/20/2019.
 **/
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final HashMap<Class, Callable<? extends ViewModel>> creators;

    public ViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new HashMap<>();
        creators.put(DirectionsViewModel.class, viewModelSubComponent::directionsViewModel);
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Timber.d("modelClass: %s", modelClass);
        Callable<?extends ViewModel> creator = creators.get(modelClass);
        if (creator == null){
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()){ //h3ml for loop 3la el creators.entrySet() y3ni el creators kollha
                if (modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }//end for loop
        }//end null check

        if (creator == null){
            Timber.e(new IllegalArgumentException("Unknown class model" + modelClass));
        }

        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
