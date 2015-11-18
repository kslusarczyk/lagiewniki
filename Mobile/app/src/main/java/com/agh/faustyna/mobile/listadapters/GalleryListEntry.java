package com.agh.faustyna.mobile.listadapters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 18.11.2015.
 */
public class GalleryListEntry {
    private final String title;
    private final String thumbnailUrl;
    private final List<String> imagesUrls;

    public GalleryListEntry(String title, String thumbnailUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        imagesUrls = new ArrayList<String>();
    }

    public void addImageUrl(String url){
        imagesUrls.add(url);
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public List<String> getImagesUrls() {
        return imagesUrls;
    }
}
