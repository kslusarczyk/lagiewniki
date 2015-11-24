package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by Klaudia on 2015-11-16.
 */
public class Obraz extends GetResourceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.obraz);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.content_webview);
        displayResource(R.string.rest_obraz, webView);
    }

}