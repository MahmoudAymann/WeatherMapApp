package com.iotblue.weathermapapp.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.iotblue.weathermapapp.adapter.callback.BookmarkCallBack;
import com.iotblue.weathermapapp.data.local.entity.Bookmark;
import com.iotblue.weathermapapp.databinding.SingleItemBookmarkBinding;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public class BookmarkHolder extends RecyclerView.ViewHolder {
    private SingleItemBookmarkBinding itemView;
    public BookmarkHolder(@NonNull SingleItemBookmarkBinding itemView) {
        super(itemView.getRoot());
        this.itemView = itemView;
    }

    public void onBind(Bookmark bookmark, BookmarkCallBack bookmarkCallBack) {
        if (bookmarkCallBack != null && getAdapterPosition() != NO_POSITION) {
            itemView.deleteBtn.setOnClickListener(view -> bookmarkCallBack.onDeleteBtnClick(bookmark));
            itemView.rootLayout.setOnClickListener(view -> bookmarkCallBack.onItemClick(bookmark));
        }

        itemView.nameTxt.setText(bookmark.getName());
        itemView.latTxt.setText(String.valueOf(bookmark.getLat()));
        itemView.lonTxt.setText(String.valueOf(bookmark.getLon()));

    }
}
