package com.robillo.readrush.ui.custom;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.robillo.readrush.R;

/**
 * Created by robinkamboj on 17/10/17.
 */

public class ShowLoadingFragment extends DialogFragment {

    private String mTitle, mDescription;

    public static ShowLoadingFragment newInstance(String mTitle, String mDescription){
        ShowLoadingFragment fragment = new ShowLoadingFragment();
        Bundle args = new Bundle();
        args.putString("title", mTitle);
        args.putString("description", mDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            mTitle = bundle.getString("title");
            mDescription = bundle.getString("description");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle(mTitle)
                .setMessage(mDescription)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Pressed OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }

}
