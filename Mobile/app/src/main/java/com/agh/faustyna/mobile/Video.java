package com.agh.faustyna.mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.agh.faustyna.mobile.streaming.CustomWebView;


public class Video extends AppCompatActivity {
    private CustomWebView player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        player = (CustomWebView)findViewById(R.id.video_view);
        String url = getString(R.string.url_stream);
        player.loadUrl(url);
    }

    @Override
    public void onBackPressed(){
        player.destroy();
        super.onBackPressed();
    }
}
