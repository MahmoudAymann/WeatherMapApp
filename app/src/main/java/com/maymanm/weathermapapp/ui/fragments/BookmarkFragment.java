package com.maymanm.weathermapapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;

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

import com.maymanm.weathermapapp.R;
import com.maymanm.weathermapapp.adapter.callback.BookmarkCallBack;
import com.maymanm.weathermapapp.adapter.parent.BookmarkAdapter;
import com.maymanm.weathermapapp.data.local.entity.Bookmark;
import com.maymanm.weathermapapp.data.models.WeatherModel;
import com.maymanm.weathermapapp.data.remote.RemoteRebo;
import com.maymanm.weathermapapp.databinding.FragmentBookmarkBinding;
import com.maymanm.weathermapapp.helper.MyConstants;
import com.maymanm.weathermapapp.viewmodel.BookmarkViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.widget.Toast.LENGTH_SHORT;

public class BookmarkFragment extends Fragment implements BookmarkCallBack {

    private FragmentBookmarkBinding data;
    private BookmarkViewModel  bookmarkViewModel;
    BookmarkAdapter bookmarkAdapter;
    private RecyclerView recyclerView;
    private FragmentActivity mContext;
    private RemoteRebo Remote;
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
        Remote=new RemoteRebo(Objects.requireNonNull(getActivity()).getApplicationContext());
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
        //do thest backobground task
        HashMap<String,Object>data=new HashMap<>();
        data.put("lat",bookmark.getLat());
        data.put("lon",bookmark.getLon());
        data.put("appid", MyConstants.APP_ID);
        data.put("unit",MyConstants.UNIT);
        Disposable disposable=
        Remote.getData(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s->{
                    Toast.makeText(BookmarkFragment.this.mContext,"Sucess", LENGTH_SHORT).show();
                    Toast.makeText(BookmarkFragment.this.mContext,s.getName(), LENGTH_SHORT).show();
                    openFragment(s);
                },
                e->{
            e.printStackTrace();
            Toast.makeText(BookmarkFragment.this.mContext,"Faild To Get Data", LENGTH_SHORT).show();
                });

    }

    private void openFragment(WeatherModel model) {
        assert getFragmentManager() != null;
        Bundle bundle = new Bundle();
        bundle.putParcelable("model", model);
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
