package com.agh.faustyna.mobile.listadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.tasks.DownloadImageTask;

import java.util.List;

/**
 * Created by Klaudia on 18.11.2015.
 */
public class GalleryListAdapter extends BaseAdapter {
    private final List<GalleryListEntry> entries;
    private ProgressBarActivity context;


    public GalleryListAdapter(ProgressBarActivity context, List<GalleryListEntry> entries) {
        this.entries = entries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_gallery, null);
        }

        GalleryListEntry entry = entries.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.gallery_title_textview);
        titleTextView.setText(entry.getTitle());
        ImageView thumbnailImageview = (ImageView) convertView.findViewById(R.id.gallery_thumbnail_image_view);
        new DownloadImageTask(context, thumbnailImageview).execute(entry.getThumbnailUrl());
        return convertView;
    }
}
