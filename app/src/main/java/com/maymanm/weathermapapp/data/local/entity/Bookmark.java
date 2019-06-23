package com.maymanm.weathermapapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
@Entity
public class Bookmark {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double lat;
    private double lon;

    //we don't add id cuz it will added auto
    public Bookmark(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    //important only set value
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
