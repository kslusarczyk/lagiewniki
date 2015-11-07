package com.agh.faustyna.mobile.streaming;

/**
 * Created by Klaudia on 07.11.2015.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class CustomWebView extends WebView {

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
        super(context, attrs, defStyle, privateBrowsing);
        init();
    }

    protected void init() {
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new CustomWebViewClient());
        setDownloadListener(new CustomDownloadListener());
    }


    protected boolean overrideUrlLoading(WebView view, String url) {

        // ANY CUSTOM LOGIC GOES HERE

        view.loadUrl(url);
        return true;
    }

    protected void pageFinished(WebView view, String url) {
    }

    protected void downloadStarted(String url, String mimetype) {
        try {
            //Start new activity to load the specific url
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), mimetype);
            getContext().startActivity(intent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Default webview client.
     */
    protected class CustomWebViewClient extends WebViewClient {

        /**
         * Constructor, default.
         */
        public CustomWebViewClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return overrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pageFinished(view, url);
        }
    }

    protected class CustomDownloadListener implements DownloadListener {

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            downloadStarted(url, mimetype);
        }
    }
}