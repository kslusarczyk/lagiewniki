package com.agh.faustyna.mobile.http.tasks;

import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.agh.faustyna.mobile.OdsluchajKoronke;

/**
 * Created by Klaudia on 15.11.2015.
 */
public class PlayerProgressTask extends AsyncTask<Void, Void, Void>{
    private OdsluchajKoronke context;
    private MediaPlayer player;

    public PlayerProgressTask(OdsluchajKoronke context, MediaPlayer player) {
        this.context = context;
        this.player = player;

        context.registerTask(this);
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(;;){
            if(player.isPlaying()){
                publishProgress();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... progressParams){
        int duration = player.getDuration();
        int elapsed = player.getCurrentPosition();
        context.setPlayerProgress(elapsed, duration);

        String durationText = getDurationText(elapsed) + " / " + getDurationText(duration);
        context.setDurationText(durationText);
    }

    private String getDurationText(int time){
        time = time / 1000;
        int seconds = time % 60;
        int minutes = time / 60;
        String durationText = String.format("%02d:%02d", minutes, seconds);
        return durationText;
    }
}
