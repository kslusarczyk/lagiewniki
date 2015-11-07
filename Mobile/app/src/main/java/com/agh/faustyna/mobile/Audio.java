package com.agh.faustyna.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.agh.faustyna.mobile.streaming.CustomWebView;

public class Audio extends AppCompatActivity {

    private CustomWebView player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        player = (CustomWebView)findViewById(R.id.video_view);
        String url = getString(R.string.urlAudio);
        player.loadUrl(url);
    }

    @Override
    public void onBackPressed(){
        player.destroy();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
