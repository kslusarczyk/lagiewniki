package com.agh.faustyna.mobile.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Klaudia on 14.11.2015.
 */
public class HttpUtils {
    public static JSONObject doGet(String urlString) throws URISyntaxException, IOException, JSONException {
        HttpClient httpClient = new DefaultHttpClient();
        URL url = new URL(urlString);
        HttpGet httpGet = new HttpGet(url.toURI());

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity == null ) throw new IOException("Empty response");

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        JSONObject responseData = new JSONObject(stringBuilder.toString());

        return responseData;
    }
}
