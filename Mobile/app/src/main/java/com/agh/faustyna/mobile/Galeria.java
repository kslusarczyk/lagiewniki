package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetGalleryListTask;

public class Galeria extends ProgressBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ListView listView = (ListView) findViewById(R.id.gallery_list_view);

        new GetGalleryListTask(this,listView).execute();
    }

}
