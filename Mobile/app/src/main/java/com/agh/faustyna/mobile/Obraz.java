package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.tasks.GetObrazTask;

/**
 * Created by Klaudia on 2015-11-16.
 */
public class Obraz extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.obraz);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetObrazTask(this).execute();
    }

}