package com.maymanm.weathermapapp.di.module;

import com.maymanm.weathermapapp.util.DialogueUtily;
import com.maymanm.weathermapapp.util.MapsUtily;
import com.maymanm.weathermapapp.util.PermissionUtily;
import com.maymanm.weathermapapp.util.SettingManager;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Module
public class MyModule {

    @Inject
    MapsUtily mapsUtily() {
        return new MapsUtily();
    }

    @Inject
    PermissionUtily permissionUtily() {
        return new PermissionUtily();
    }

    @Inject
    SettingManager settingManager() {
        return new SettingManager();
    }

    @Inject
    DialogueUtily dialogueUtily() {
        return new DialogueUtily();
    }

}
