package com.agh.faustyna.mobile.listadapters;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 18.11.2015.
 */
public class GalleryListEntry implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(thumbnailUrl);

        String [] urlsArray = imagesUrls.toArray(new String[imagesUrls.size()]);
        dest.writeStringArray(urlsArray);
    }

    public static final Parcelable.Creator<GalleryListEntry> CREATOR = new Parcelable.Creator<GalleryListEntry>() {
        public GalleryListEntry createFromParcel(Parcel in) {
            String title = in.readString();
            String thumbnail = in.readString();
            String [] urlsArray = in.createStringArray();

            GalleryListEntry result = new GalleryListEntry(title, thumbnail);

            for(String url : urlsArray){
                result.addImageUrl(url);
            }

            return result;
        }

        public GalleryListEntry[] newArray(int size) {
            return new GalleryListEntry[size];
        }
    };
}
