package com.agh.faustyna.mobile.http;

import android.os.AsyncTask;
import android.util.Log;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Klaudia on 14.11.2015.
 */
public abstract class HttpTask extends AsyncTask<Void, Void, JSONObject> {
    protected ProgressBarActivity context;
    private int restResourceId;

    public HttpTask(ProgressBarActivity context, int restResourceId){
        super();
        this.context = context;
        this.restResourceId = restResourceId;

        //register task
        context.registerTask(this);
    }

    protected abstract void processResult(JSONObject responseData);

    @Override
    protected void onPreExecute(){
        context.showProgressBar();
    }

    @Override
    protected void onPostExecute(JSONObject responseData){
        context.hideProgressBar();
        processResult(responseData);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String serwerUrl = context.getString(R.string.url_faustyna);
        String resourcePath = context.getString(restResourceId);

        try {

            return HttpUtils.doGet(serwerUrl + resourcePath);
        } catch (Exception e) {
            Log.d("http", e.getMessage(), e);

            JSONObject errorInfo = new JSONObject();

            try {
                errorInfo.put("status", "exception");
                errorInfo.put("reason", e.toString());
                errorInfo.put("message", e.getMessage());
            } catch (JSONException e1) {}

            return errorInfo;
        }
    }
}
