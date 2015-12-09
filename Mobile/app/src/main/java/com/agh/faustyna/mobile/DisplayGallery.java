package com.agh.faustyna.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.agh.faustyna.mobile.listadapters.GalleryImagesAdapter;
import com.agh.faustyna.mobile.listadapters.GalleryListEntry;

public class DisplayGallery extends ProgressBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gallery);

        //get gallery info passed with intent
        Intent intent = getIntent();
        GalleryListEntry galleryEntry = intent.getParcelableExtra("galleryEntry");

        //setting gallery title
        TextView titleTextView = (TextView) findViewById(R.id.gallery_title_textview);
        titleTextView.setText(galleryEntry.getTitle());

        //getting listview
        ListView imagesListview = (ListView) findViewById(R.id.gallery_images_listview);
        GalleryImagesAdapter adapter = new GalleryImagesAdapter(this, galleryEntry.getImagesUrls());
        imagesListview.setAdapter(adapter);
    }
}