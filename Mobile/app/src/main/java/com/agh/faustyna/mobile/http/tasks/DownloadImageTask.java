package com.agh.faustyna.mobile.http.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.agh.faustyna.mobile.ProgressBarActivity;

import java.io.InputStream;

/**
 * Created by Klaudia on 14.11.2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    public DownloadImageTask(ProgressBarActivity context, ImageView imageView){
        this(imageView);
        context.registerTask(this);
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e("imgErr", e.getMessage());
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
