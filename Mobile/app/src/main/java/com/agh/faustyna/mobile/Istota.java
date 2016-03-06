package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by Karolina on 2015-11-05.
 */
public class Istota extends GetResourceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.istota);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.content_webview);
        displayResource(R.string.rest_istota, webView);
    }
}