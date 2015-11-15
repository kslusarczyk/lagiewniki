package com.agh.faustyna.mobile;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 14.11.2015.
 */
public abstract class ProgressBarActivity extends Activity {
    protected ProgressBar progressBar;
    private List<AsyncTask> httpTasks;

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void registerTask(AsyncTask task){
        if(httpTasks == null) httpTasks = new ArrayList<AsyncTask>();
        httpTasks.add(task);
    }

    @Override
    public void onBackPressed(){
        if(httpTasks != null) {
            for (AsyncTask task : httpTasks) {
                task.cancel(true);
            }
        }

        super.onBackPressed();
    }
}
