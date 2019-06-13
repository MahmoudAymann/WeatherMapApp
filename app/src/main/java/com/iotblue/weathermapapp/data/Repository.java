package com.iotblue.weathermapapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.iotblue.weathermapapp.data.local.database.BookmarkDao;
import com.iotblue.weathermapapp.data.local.database.BookmarkDatabase;
import com.iotblue.weathermapapp.data.local.entity.Bookmark;

import java.util.List;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class Repository {
    private BookmarkDao bookmarkDao;
    private LiveData<List<Bookmark>> allBookmarks;

    public Repository(Application application){
        BookmarkDatabase database = BookmarkDatabase.getInstance(application);
        bookmarkDao = database.bookmarkDao(); // Room subclasses our abstract class.
        allBookmarks = bookmarkDao.getAllBookmarks();
    }


    public void insert(Bookmark note){
        new InsertBookmarkAsyncTask(bookmarkDao).execute(note);
    }

    public void update(Bookmark note){
        new UpdateBookmarkAsyncTask(bookmarkDao).execute(note);
    }

    public void delete(Bookmark note){
        new DeleteBookmarkAsyncTask(bookmarkDao).execute(note);
    }

    public void deleteAllBookmarks(){
        new DeleteAllBookmarksAsyncTask(bookmarkDao).execute();
    }

    public LiveData<List<Bookmark>> getAllBookmarks() {
        return allBookmarks;
    }

    private static class InsertBookmarkAsyncTask extends AsyncTask<Bookmark , Void, Void> {
        private BookmarkDao noteDao;
        private InsertBookmarkAsyncTask(BookmarkDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Bookmark... notes) { //Bookmark... means a list of notes
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateBookmarkAsyncTask extends AsyncTask<Bookmark , Void, Void>{
        private BookmarkDao noteDao;
        private UpdateBookmarkAsyncTask(BookmarkDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Bookmark... notes) { //Bookmark... means a list of notes
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteBookmarkAsyncTask extends AsyncTask<Bookmark , Void, Void>{
        private BookmarkDao noteDao;
        private DeleteBookmarkAsyncTask(BookmarkDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Bookmark... notes) { //Bookmark... means a list of notes
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllBookmarksAsyncTask extends AsyncTask<Void , Void, Void>{
        private BookmarkDao noteDao;
        private DeleteAllBookmarksAsyncTask(BookmarkDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) { //Bookmark... means a list of notes
            noteDao.deleteAllBookmarks();
            return null;
        }
    }
}
