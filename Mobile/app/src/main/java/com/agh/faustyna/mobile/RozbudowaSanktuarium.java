package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class RozbudowaSanktuarium extends GetResourceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.obraz);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.content_webview);
        displayResource(R.string.rest_rozbudowa, webView);
    }

}
