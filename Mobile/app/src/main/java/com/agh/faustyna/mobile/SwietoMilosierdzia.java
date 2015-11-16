package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.tasks.GetSwietoMilosierdziaTask;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class SwietoMilosierdzia extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.istota);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            new GetSwietoMilosierdziaTask(this).execute();
        }
}
