package com.robillo.readrush.ui.rushread.custom_dialog_fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.Toast;

import com.robillo.readrush.R;

/**
 * Created by robinkamboj on 22/12/17.
 */

public class CustomDialogFragment extends DialogFragment {

//    private int num;

    public static CustomDialogFragment newInstance(int num){
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle args = new Bundle();
//        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
//        if (bundle != null){
//            num = bundle.getInt("num");
//        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_fragment);
        return dialog;
//        return new AlertDialog.Builder(getActivity())
//                .setIcon(R.mipmap.ic_launcher_round)
//                .setTitle("Test Dialog")
//                .setMessage("This Is A Test Dialog With Num")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), "Pressed OK", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
//                    }
//                }).create();
//                .setMessage("This Is A Test Dialog With Num = " + num)
    }

}
