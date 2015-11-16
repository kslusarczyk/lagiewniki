package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetLitaniaTask;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class LitaniaDoSwSiostryFaustyny extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_content);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetLitaniaTask(this).execute();
    }
}
