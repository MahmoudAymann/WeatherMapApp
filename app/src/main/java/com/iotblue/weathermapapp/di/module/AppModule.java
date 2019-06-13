package com.iotblue.weathermapapp.di.module;

import com.iotblue.weathermapapp.util.DialogueUtily;
import com.iotblue.weathermapapp.util.MapsUtily;
import com.iotblue.weathermapapp.util.PermissionUtily;
import com.iotblue.weathermapapp.util.SettingManager;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
@Module
public class AppModule {

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
