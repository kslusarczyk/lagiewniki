package com.agh.faustyna.mobile.http.parsedjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Klaudia on 14.11.2015.
 */
public class News {
    public final String title;
    public final String content;
    public final String date;
    public final String excerpt;
    public final String imageUrl;
    public final String thumbnailUrl;

    public News(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        content = jsonObject.getString("content");
        date = jsonObject.getString("date");
        excerpt = jsonObject.getString("excerpt");

        JSONArray attachments = jsonObject.getJSONArray("attachments");

        if(attachments.length() > 0) {
            JSONObject images = attachments.getJSONObject(0).getJSONObject("images");
            imageUrl = images.getJSONObject("full").getString("url");
            thumbnailUrl = images.getJSONObject("thumbnail").getString("url");
        } else {
            imageUrl = "";
            thumbnailUrl = "";
        }
    }


}
