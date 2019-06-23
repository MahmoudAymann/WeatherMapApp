package com.maymanm.weathermapapp.adapter.callback;

import com.maymanm.weathermapapp.data.local.entity.Bookmark;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
public interface BookmarkCallBack {
    void onItemClick(Bookmark bookmark);

    void onDeleteBtnClick(Bookmark bookmark);
}
