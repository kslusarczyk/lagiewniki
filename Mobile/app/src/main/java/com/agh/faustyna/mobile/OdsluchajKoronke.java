package com.agh.faustyna.mobile;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agh.faustyna.mobile.http.tasks.PlayerProgressTask;
import com.agh.faustyna.mobile.http.tasks.PreparePlayerTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OdsluchajKoronke extends ProgressBarActivity {
    private ImageButton playButton;
    private MediaPlayer mediaPlayer;
    private TextView stateTextView;
    private ProgressBar playerProgressBar;
    private boolean isPlayerPrepared = false;
    private boolean isPlaying = false;

    private PlayerProgressTask playerProgressTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        progressBar = (ProgressBar) findViewById(R.id.download_progress_bar);
        playButton = (ImageButton) findViewById(R.id.play_button);
        stateTextView = (TextView) findViewById(R.id.player_state_text_view);
        playerProgressBar = (ProgressBar) findViewById(R.id.player_progress);

        //date formats for title and url
        Date today = new Date();
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);

        SimpleDateFormat yesterdayDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat titleDateFormat = new SimpleDateFormat("dd.MM");
        SimpleDateFormat urlDateFormat = new SimpleDateFormat("yyyyMMdd");


        //obtaining url to current transmission
        String baseUrl = getString(R.string.url_audio);

        String urlYesterday = baseUrl.replace("{date}", yesterdayDateFormat.format(yesterday));
        String url = baseUrl.replace("{date}", urlDateFormat.format(today));
        Log.d("url", url);


        // setting title
        TextView titleTextView = (TextView) findViewById(R.id.title_audio_text_view);
        String baseTitle = getString(R.string.odsluchaj_koronke);
        String title = baseTitle.replace("{date}", titleDateFormat.format(today));
        titleTextView.setText(title);

        //setting up player
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        String compare = df.format(dateobj);
        String hours = "15:45:00";
        if (compare.compareTo(hours) < 0) {
            try {
                mediaPlayer.setDataSource(urlYesterday);
            } catch (IOException e) {
                //handling error that is almost impossible to occur :P
                Toast.makeText(this, R.string.audio_url_error, Toast.LENGTH_LONG).show();
                Log.d("player", e.getMessage(), e);
                finish();
            }
        } else {
            try {
                mediaPlayer.setDataSource(url);
            } catch (IOException e) {
                //handling error that is almost impossible to occur :P
                Toast.makeText(this, R.string.audio_url_error, Toast.LENGTH_LONG).show();
                Log.d("player", e.getMessage(), e);
                finish();
            }
        }
    }

    private void play(){
        isPlaying = true;
        playButton.setImageResource(R.drawable.player_pause);
        mediaPlayer.start();

        //start task for updating progress
        if(playerProgressTask == null){
            playerProgressTask = new PlayerProgressTask(this, mediaPlayer);
            playerProgressTask.execute();
        }
    }

    private void pause(){
        isPlaying = false;
        playButton.setImageResource(R.drawable.player_play);
        mediaPlayer.pause();
    }

    public void onPlayerPrepared(){
        hideProgressBar();
        playButton.setImageResource(R.drawable.player_play);
        stateTextView.setText(R.string.pobrana_koronka);
        isPlayerPrepared = true;
    }

    public void handlePlayButton(View view){
        if(!isPlayerPrepared){
            stateTextView.setText(R.string.pobieranie_koronki);
            showProgressBar();
            //start prepare player task
            new PreparePlayerTask(this).execute(mediaPlayer);
        } else if(!isPlaying) {
            play();
        } else {
            pause();
        }
    }

    public void setPlayerProgress(int elapsed, int duration){
        playerProgressBar.setMax(duration);
        playerProgressBar.setProgress(elapsed);
    }

    public void setDurationText(String durationText){
        stateTextView.setText(durationText);
    }

    @Override
    public void showProgressBar(){
        super.showProgressBar();
        playButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar(){
        super.hideProgressBar();
        playButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        try {
            mediaPlayer.stop();
        } catch (IllegalStateException e){}

        try {
            mediaPlayer.release();
        } catch (IllegalStateException e){}

        mediaPlayer = null;
    }
}
