package com.maymanm.weathermapapp.data.local.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.maymanm.weathermapapp.data.local.entity.Bookmark;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/

@Database(entities = {Bookmark.class}, version = 1, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase {

    //(singleton)
    private static BookmarkDatabase instance;

    public abstract BookmarkDao bookmarkDao();


    public static BookmarkDatabase getInstance(Context context) {
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BookmarkDatabase.class,"notes_database")
                    .fallbackToDestructiveMigration() //this can delete all database when updating the database without changing the version num
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    //TODO implement Rx here
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BookmarkDao bookmarkDao;

        PopulateDbAsyncTask(BookmarkDatabase db) {
            this.bookmarkDao = db.bookmarkDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookmarkDao.insert(new Bookmark("Cairo", 30.0444, 31.2357));
            return null;
        }
    }


}
