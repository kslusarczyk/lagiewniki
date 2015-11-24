package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by Klaudia on 16.11.2015.
 */
public class SwietoMilosierdzia extends GetResourceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.istota);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.content_webview);
        displayResource(R.string.rest_swieto_milosierdzia, webView);
    }
}
