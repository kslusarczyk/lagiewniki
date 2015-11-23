package com.agh.faustyna.mobile.http.tasks;

import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.HttpTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Karolina on 2015-11-23.
 */
public class GetDzienniczekSwFaustynyTask extends HttpTask {

    public GetDzienniczekSwFaustynyTask(ProgressBarActivity context) {
        super(context, R.string.rest_dzienniczek_siostry_faustyny);
    }

    @Override
    protected void processResult(JSONObject responseData) {
        try {
            String content = responseData.getString("content");

            WebView contentWebView = (WebView) context.findViewById(R.id.content_webview);
            contentWebView.getSettings().setJavaScriptEnabled(true);
            contentWebView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");

        } catch (JSONException e) {
            Toast.makeText(context, R.string.load_error, Toast.LENGTH_LONG).show();
            Log.d("http", e.getMessage(), e);
        }
    }

}
