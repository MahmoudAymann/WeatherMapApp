package com.iotblue.weathermapapp.util;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.iotblue.weathermapapp.R;
import com.iotblue.weathermapapp.helper.MyAlertDialogue;
import com.iotblue.weathermapapp.helper.MyConstants;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by MahmoudAyman on 6/13/2019.
 **/
@Singleton
public class DialogueUtily {
    @Inject
    public DialogueUtily() {
    }

    public DialogFragment createDialouge(Fragment fragment, String title, String msg, String buttonYesTxt, String buttonNoTxt,int requestCode){
        DialogFragment dialog = new MyAlertDialogue();
        Bundle args = new Bundle();
        args.putString(MyAlertDialogue.ARG_TITLE, title);
        args.putString(MyAlertDialogue.ARG_MESSAGE, msg);
        args.putString(MyAlertDialogue.ARG_BTN_YES, buttonYesTxt);
        args.putString(MyAlertDialogue.ARG_BTN_NO, buttonNoTxt);
        dialog.setArguments(args);
        dialog.setTargetFragment(fragment, requestCode);
        return dialog;
    }
}
