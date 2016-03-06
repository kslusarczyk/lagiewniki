package com.agh.faustyna.mobile;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agh.faustyna.mobile.http.tasks.GetDzienniczekSwFaustynyTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karolina on 2015-11-23.
 */

public class DzienniczekSwFaustyny extends ProgressBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzienniczek);

        Date todayDate = new Date();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(todayDate);

        TextView dzienniczekDate = (TextView) findViewById(R.id.dzienniczek_date);
        dzienniczekDate.setText(date);

        TextView dzienniczekInfo = (TextView) findViewById(R.id.dzienniczek_info);
        dzienniczekInfo.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetDzienniczekSwFaustynyTask(this).execute();
    }

    @Override
    public void onClick(View v) {
        boolean internetState = checkInternetConnection();
        if (internetState){
            openWebPage(getString(R.string.url_faustyna));
        } else {
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