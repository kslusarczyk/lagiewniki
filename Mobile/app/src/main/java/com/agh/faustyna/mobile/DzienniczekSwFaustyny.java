package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agh.faustyna.mobile.http.tasks.GetDzienniczekSwFaustynyTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karolina on 2015-11-23.
 */

public class DzienniczekSwFaustyny extends ProgressBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzienniczek);

        Date todayDate = new Date();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(todayDate);

        TextView dzienniczekDate = (TextView) findViewById(R.id.dzienniczek_date);
        dzienniczekDate.setText(date);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new GetDzienniczekSwFaustynyTask(this).execute();
    }
}