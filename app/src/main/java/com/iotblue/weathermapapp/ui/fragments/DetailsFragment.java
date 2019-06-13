package com.iotblue.weathermapapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iotblue.weathermapapp.R;
import com.iotblue.weathermapapp.databinding.FragmentDetailsBinding;

import org.jetbrains.annotations.NotNull;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     DetailsFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class DetailsFragment extends BottomSheetDialogFragment {

    private FragmentDetailsBinding data;
    private String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        data = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        data.txt.setText(text);

        return data.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            text = bundle.getString("cc");
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        //set listeners to null here
        super.onDetach();
    }

}