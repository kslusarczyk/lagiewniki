package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetCudownyObrazTask;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class CudownyObraz extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_content);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetCudownyObrazTask(this).execute();
    }
}