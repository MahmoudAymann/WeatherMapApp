package com.iotblue.weathermapapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.iotblue.weathermapapp.R;
import com.iotblue.weathermapapp.databinding.FragmentBookmarkBinding;

import org.jetbrains.annotations.NotNull;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding data;

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

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
//        }
        data.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                Bundle bundle = new Bundle();
                bundle.putString("cc", "asdasndjndjkajnsjkdnfkjadfdhsbfhbsdfhfhadsfhadsbfshadbfbasdf");
                DetailsFragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), "detailsFragment");
            }
        });

        return data.getRoot();
    }


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
