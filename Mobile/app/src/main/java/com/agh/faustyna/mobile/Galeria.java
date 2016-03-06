package com.agh.faustyna.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.tasks.GetGalleryListTask;
import com.agh.faustyna.mobile.listadapters.GalleryListEntry;

public class Galeria extends ProgressBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final ListView listView = (ListView) findViewById(R.id.gallery_list_view);

        final Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(context, DisplayGallery.class);

                GalleryListEntry entry = (GalleryListEntry) listView.getAdapter().getItem(position);

                intent.putExtra("galleryEntry", entry);
                context.startActivity(intent);
            }
        });

        //fill list
        new GetGalleryListTask(this,listView).execute();
    }

}
