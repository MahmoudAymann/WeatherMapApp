package com.maymanm.weathermapapp.adapter.parent;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.maymanm.weathermapapp.R;
import com.maymanm.weathermapapp.adapter.callback.BookmarkCallBack;
import com.maymanm.weathermapapp.adapter.viewholder.BookmarkHolder;
import com.maymanm.weathermapapp.data.local.entity.Bookmark;
import com.maymanm.weathermapapp.databinding.SingleItemBookmarkBinding;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class BookmarkAdapter extends ListAdapter<Bookmark, BookmarkHolder> {


    private final BookmarkCallBack bookmarkCallBack;

    public BookmarkAdapter(BookmarkCallBack bookmarkCallBack) {
        super(DIFF_CALLBACK);
        this.bookmarkCallBack = bookmarkCallBack;
    }


    private static final DiffUtil.ItemCallback<Bookmark> DIFF_CALLBACK = new DiffUtil.ItemCallback<Bookmark>() {
        @Override
        public boolean areItemsTheSame(@NonNull Bookmark oldItem, @NonNull Bookmark newItem) {
            //this will return true cuz the id is the unique part of Bookmark.class
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Bookmark oldItem, @NonNull Bookmark newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getLat() == newItem.getLat() &&
                    oldItem.getLon() == newItem.getLon();
        }
    };


    @NonNull
    @Override
    public BookmarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //parent.getContext() is the context of the activity/fragment the the recyclerView is into.
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        SingleItemBookmarkBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.single_item_bookmark, parent, false);
        return new BookmarkHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkHolder holder, int position) {
        holder.onBind(getCurrentNode(position), bookmarkCallBack);
    }

    public Bookmark getCurrentNode(int pos) {
        return getItem(pos);
    }


}
