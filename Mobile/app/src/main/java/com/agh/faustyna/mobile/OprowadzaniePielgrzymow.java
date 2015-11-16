package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetPielgrzymiTask;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class OprowadzaniePielgrzymow extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_content);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetPielgrzymiTask(this).execute();
    }
}
