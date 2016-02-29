package com.agh.faustyna.mobile.http.tasks;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.HttpTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Klaudia on 17.11.2015.
 */
public class GetResourceTask extends HttpTask {
    private File file;
    private WebView contentWebView;

    public GetResourceTask(ProgressBarActivity context, int restResourceId, File file, WebView contentWebView) {
        super(context, restResourceId);
        this.file = file;
        this.contentWebView = contentWebView;
    }

    @Override
    protected void processResult(JSONObject responseData) {
        try {
            final String status = responseData.getString("status");
            if (status.equals("ok")){
                JSONObject page = responseData.getJSONObject("page");
                String content = page.getString("content");
                content = content.replace("\"//www.", "\"https://www.");

                Log.d("debug", content);
                try {
                    PrintWriter writer = new PrintWriter(new FileOutputStream(file, false));
                    writer.write(content);
                    writer.close();
                    contentWebView.getSettings().setJavaScriptEnabled(true);
                    contentWebView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");
                    Log.d("http", "saved: " + file.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    Log.d("http", e.getMessage(), e);
                }


            } else if (status.equals("exception")){
                throw new JSONException(responseData.getString("message"));
            } else {
                throw new JSONException("Unknown error");
            }
        } catch (JSONException e) {
            Toast.makeText(context, R.string.brak_zasobu, Toast.LENGTH_LONG).show();
            Log.d("http", e.getMessage(), e);
        }
    }
}
