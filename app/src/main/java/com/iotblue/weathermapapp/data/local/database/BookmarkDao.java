package com.iotblue.weathermapapp.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iotblue.weathermapapp.data.local.entity.Bookmark;

import java.util.List;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
@Dao
public interface BookmarkDao {

    @Insert
    void insert(Bookmark bookmark);

    @Update
    void update(Bookmark bookmark);

    @Delete
    void delete(Bookmark bookmark);

    @Query("DELETE FROM bookmark")
    void deleteAllBookmarks();

    @Query("SELECT * FROM bookmark ORDER BY id DESC")
    LiveData<List<Bookmark>> getAllBookmarks();

}
