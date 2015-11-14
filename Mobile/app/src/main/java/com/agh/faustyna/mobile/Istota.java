package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetIstotaTask;

/**
 * Created by Karolina on 2015-11-05.
 */
public class Istota extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.istota);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetIstotaTask(this).execute();
    }
}