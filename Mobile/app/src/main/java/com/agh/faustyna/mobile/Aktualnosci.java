package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetNewsTask;

/**
 * Created by Karolina on 2015-11-06.
 */
public class Aktualnosci extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktualnosci);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        new GetNewsTask(this).execute();
    }
}