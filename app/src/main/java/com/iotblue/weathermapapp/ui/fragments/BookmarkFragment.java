package com.iotblue.weathermapapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iotblue.weathermapapp.R;
import com.iotblue.weathermapapp.adapter.callback.BookmarkCallBack;
import com.iotblue.weathermapapp.adapter.parent.BookmarkAdapter;
import com.iotblue.weathermapapp.data.local.entity.Bookmark;
import com.iotblue.weathermapapp.databinding.FragmentBookmarkBinding;
import com.iotblue.weathermapapp.viewmodel.BookmarkViewModel;

import org.jetbrains.annotations.NotNull;

import static android.widget.Toast.LENGTH_SHORT;

public class BookmarkFragment extends Fragment implements BookmarkCallBack {

    private FragmentBookmarkBinding data;
    private BookmarkViewModel  bookmarkViewModel;
    BookmarkAdapter bookmarkAdapter;
    private RecyclerView recyclerView;
    private FragmentActivity mContext;

    public BookmarkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false);

        initRecyclerView();

        bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        bookmarkViewModel.getAllBookmarks().observe(this, bookmarks -> {
            //update RecyclerView
            bookmarkAdapter.submitList(bookmarks);
        });

        return data.getRoot();
    }

    private void initRecyclerView() {
        recyclerView = data.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //you should set this to true if you u know that recyclerView size wont change(it increases the performance of recyclerView)
        recyclerView.setHasFixedSize(true);
        bookmarkAdapter = new BookmarkAdapter(this);
        recyclerView.setAdapter(bookmarkAdapter);
    }


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mContext = (FragmentActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(Bookmark bookmark) {
        //do the background task
        assert getFragmentManager() != null;
        Bundle bundle = new Bundle();
        bundle.putString("cc", "asdasndjndjkajnsjkdnfkjadfdhsbfhbsdfhfhadsfhadsbfshadbfbasdf");
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "detailsFragment");
    }

    @Override
    public void onDeleteBtnClick(Bookmark bookmark) {
        bookmarkViewModel.delete(bookmark);
        Toast.makeText(mContext, "deleted", LENGTH_SHORT).show();
    }
}
