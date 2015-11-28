package com.agh.faustyna.mobile.http.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.agh.faustyna.mobile.ProgressBarActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        url = url.replace(" ", "%20");
        Bitmap image = null;
        InputStream inputStream = null;

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            if ( responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM ) {
                String location = urlConnection.getHeaderField("Location");
                inputStream = new java.net.URL(location.replace(" ", "%20")).openStream();
            } else if(responseCode == HttpURLConnection.HTTP_OK){
                inputStream = urlConnection.getInputStream();
            }
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
