package com.agh.faustyna.mobile;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by Karolina on 2015-11-15.
 */
public class NoInternetConnectionDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.wifi_dialog_message))
                .setTitle(getString(R.string.wifi_dialog_title))
                .setPositiveButton(getString(R.string.wifi_dialog_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setPositiveButton", "Ustawienia");
                        openSettings();
                    }
                })
                .setNegativeButton(getString(R.string.wifi_dialog_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setNegativeButton", "ignoruj");
                    }
                });
        return builder.create();
    }

    public void openSettings(){
        Intent openSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        if (openSettingsIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(openSettingsIntent);
        }
    }
}