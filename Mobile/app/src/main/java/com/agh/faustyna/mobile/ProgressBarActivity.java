package com.agh.faustyna.mobile;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.agh.faustyna.mobile.http.HttpTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 14.11.2015.
 */
public abstract class ProgressBarActivity extends Activity {
    protected ProgressBar progressBar;
    private List<HttpTask> httpTasks;

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void registerTask(HttpTask task){
        if(httpTasks == null) httpTasks = new ArrayList<HttpTask>();
        httpTasks.add(task);
    }

    @Override
    public void onBackPressed(){
        for(HttpTask task : httpTasks){
            task.cancel(true);
        }

        super.onBackPressed();
    }
}
