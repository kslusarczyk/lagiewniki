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
 * Created by Klaudia on 16.11.2015.
 */
public class GetNieustannaKoronkaTask extends HttpTask {
    public GetNieustannaKoronkaTask(ProgressBarActivity context){
        super(context, R.string.rest_nieustanna_koronka);
    }
    @Override
    protected void processResult(JSONObject responseData) {
        try {
            final String status = responseData.getString("status");
            if (status.equals("ok")){
                JSONObject page = responseData.getJSONObject("page");
                String content = page.getString("content");

                WebView contentWebView = (WebView) context.findViewById(R.id.content_webview);
                contentWebView.getSettings().setJavaScriptEnabled(true);
                contentWebView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");
            } else if (status.equals("exception")){
                throw new JSONException(responseData.getString("message"));
            } else {
                throw new JSONException("Unknown error");
            }
        } catch (JSONException e) {
            Toast.makeText(context, R.string.load_error, Toast.LENGTH_LONG).show();
            Log.d("http", e.getMessage(), e);
        }
    }
}
