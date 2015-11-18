package com.agh.faustyna.mobile.http.tasks;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.HttpTask;
import com.agh.faustyna.mobile.listadapters.GalleryListAdapter;
import com.agh.faustyna.mobile.listadapters.GalleryListEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 18.11.2015.
 */
public class GetGalleryListTask extends HttpTask {
    private final int NUMBER_OF_GALLERY = 5 ;
    private final ListView galleriesListView;

    public GetGalleryListTask(ProgressBarActivity context, ListView galleriesListView) {
        super(context, R.string.rest_galeria);
        this.galleriesListView = galleriesListView;
    }

    @Override
    protected void processResult(JSONObject responseData) {
        final String imagesUrl = context.getString(R.string.url_images);

        List<GalleryListEntry> entries = new ArrayList<GalleryListEntry>(NUMBER_OF_GALLERY);
        for (int i = 0; i < NUMBER_OF_GALLERY; i++){
            try {
                if(!responseData.has(String.valueOf(i))) continue;
                JSONObject gallery = responseData.getJSONObject(String.valueOf(i));
                String thumbnailUrl = imagesUrl + gallery.getString("1");
                String  galleryTitle = gallery.getString("2");
                GalleryListEntry entry = new GalleryListEntry(galleryTitle,thumbnailUrl);

                int imageId = 3;
                while (gallery.has(String.valueOf(imageId))){
                    String imageUrl = gallery.getString(String.valueOf(imageId));
                    entry.addImageUrl(imageUrl);

                    imageId+=2;
                }
                entries.add(entry);
            } catch (JSONException e) {
                Log.d("galeria", e.getMessage(), e);
            }
        }

        if (entries.size()==0){
            Toast.makeText(context, R.string.error_gallery,Toast.LENGTH_LONG).show();
            try{
                context.onBackPressed();
            } catch (IllegalStateException e) {
                Log.d("galeria", e.getMessage(), e);
            }
        }

        GalleryListAdapter adapter = new GalleryListAdapter(context,entries);
        galleriesListView.setAdapter(adapter);
    }
}
