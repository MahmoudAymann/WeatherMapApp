package com.maymanm.weathermapapp.helper;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by MahmoudAyman on 6/12/2019.
 **/
public class MyAlertDialogue extends DialogFragment {
    public static final String ARG_TITLE = "MyAlertDialogue.Title";
    public static final String ARG_MESSAGE = "MyAlertDialogue.Message";
    public static final String ARG_BTN_YES = "MyAlertDialogue.Yes";
    public static final String ARG_BTN_NO = "MyAlertDialogue.No";

    public MyAlertDialogue() {

    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        assert args != null;
        String title = args.getString(ARG_TITLE);
        String message = args.getString(ARG_MESSAGE);
        String buttonYES = args.getString(ARG_BTN_YES);
        String buttonNO = args.getString(ARG_BTN_NO);

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonYES, (dialog, which) ->{
                    assert getTargetFragment() != null;
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, null);
                })
                .setNegativeButton(buttonNO, (dialog, which) ->{
                    assert getTargetFragment() != null;
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_CANCELED, null);
                })
                .create();
    }
}
