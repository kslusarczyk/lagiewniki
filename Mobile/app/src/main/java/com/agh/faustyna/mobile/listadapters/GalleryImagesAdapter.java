package com.agh.faustyna.mobile.listadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.tasks.DownloadImageTask;

import java.util.List;

/**
 * Created by Klaudia on 28.11.2015.
 */
public class GalleryImagesAdapter extends BaseAdapter {
    private ProgressBarActivity context;
    private List<String> imageUrls;

    public GalleryImagesAdapter(ProgressBarActivity context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }


    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_image, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        new DownloadImageTask(context, imageView).execute(imageUrls.get(position));

        return convertView;
    }
}
