package com.agh.faustyna.mobile;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Karolina on 2015-11-16.
 */
public class SklepMisericordia extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean internetState = checkInternetConnection();
        Log.d("internetState", "" + internetState);
        if (internetState){
            openWebPage(getString(R.string.kontakt_wydawnictwo_sklep));
        } else {
            Log.d("Internet", "nie ma INTERNETOW");

            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
            newFragment.show(getFragmentManager(),"dialog");
        }
    }

    public boolean checkInternetConnection(){
        ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void openWebPage(String url){
        Uri webPageAddress = Uri.parse(url);
        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webPageAddress);
        if (openWebPageIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openWebPageIntent);
        }
    }
}