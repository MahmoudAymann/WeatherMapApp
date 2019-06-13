package com.iotblue.weathermapapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iotblue.weathermapapp.data.Repository;
import com.iotblue.weathermapapp.data.local.entity.Bookmark;

import java.util.List;

import javax.inject.Singleton;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class BookmarkViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Bookmark>> allBookmarks;


    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allBookmarks = repository.getAllBookmarks();
    }

    public void  insert (Bookmark note){
        repository.insert(note);
    }
    public void  update (Bookmark note){
        repository.update(note);
    }
    public void  delete (Bookmark note){
        repository.delete(note);
    }
    public void  deleteAllBookmarks (){
        repository.deleteAllBookmarks();
    }

    public LiveData<List<Bookmark>> getAllBookmarks() {
        return allBookmarks;
    }

}
