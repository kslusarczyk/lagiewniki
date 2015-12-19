package com.agh.faustyna.mobile.http.tasks;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.agh.faustyna.mobile.OdsluchAudioGodzinyMilosierdziaIKoronki;
import com.agh.faustyna.mobile.R;

import java.io.IOException;

/**
 * Created by Klaudia on 15.11.2015.
 */
public class PreparePlayerTask extends AsyncTask<MediaPlayer, Void, Boolean>{
    private OdsluchAudioGodzinyMilosierdziaIKoronki context;

    public PreparePlayerTask(OdsluchAudioGodzinyMilosierdziaIKoronki context) {
        this.context = context;
        context.registerTask(this);
    }

    @Override
    protected Boolean doInBackground(MediaPlayer... players) {
        Log.d("player", "start prepare");
        for(MediaPlayer player : players){
            try {
                player.prepare();
            } catch (IOException e) {
                Log.d("player", e.getMessage(), e);
                return Boolean.FALSE;
            }
        }
        Log.d("player", "end prepare");
        return Boolean.TRUE;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success) {
            context.onPlayerPrepared();
        } else {
            context.hideProgressBar();
            Toast.makeText(context, R.string.audio_url_error, Toast.LENGTH_LONG).show();
        }
    }
}
