package com.maymanm.weathermapapp.di.component;

import com.maymanm.weathermapapp.viewmodel.DirectionsViewModel;

import dagger.Subcomponent;

/**
 * Created by MahmoudAyman on 6/20/2019.
 **/
//its mission to create instance of view model
@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }

    DirectionsViewModel directionsViewModel();


}
