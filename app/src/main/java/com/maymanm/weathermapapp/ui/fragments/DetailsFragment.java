package com.maymanm.weathermapapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maymanm.weathermapapp.R;
import com.maymanm.weathermapapp.data.models.WeatherModel;
import com.maymanm.weathermapapp.databinding.FragmentDetailsBinding;

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

    WeatherModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        data = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        initUI();
        return data.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void initUI() {
        data.txtTemp.setText(new StringBuilder().append("Tempreture: ").append(model.getMain().getTemp()).toString());
        data.txtHum.setText(new StringBuilder().append("Humidity: ").append(model.getMain().getHumidity()).toString());
        data.txtWind.setText(new StringBuilder().append("Wind Deg: ").append(model.getWind().getDeg()).append(",\n").append("Wind Speed: ").append(model.getWind().getSpeed()).toString());
        if (model.getWeather().get(0).getMain().equals("Rain"))
            data.txtRainChances.setText("Rain chances: YES");
        else
            data.txtRainChances.setText("Rain chances: NO");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.model = bundle.getParcelable("model");
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
