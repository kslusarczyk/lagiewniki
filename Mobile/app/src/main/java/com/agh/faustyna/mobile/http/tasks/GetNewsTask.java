package com.agh.faustyna.mobile.http.tasks;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.agh.faustyna.mobile.ProgressBarActivity;
import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.HttpTask;
import com.agh.faustyna.mobile.http.parsedjson.News;
import com.agh.faustyna.mobile.listadapters.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 14.11.2015.
 */
public class GetNewsTask extends HttpTask {
    private final int NEWS_CNT = 10;

    public GetNewsTask(ProgressBarActivity context) {
        super(context, R.string.rest_news);
    }

    @Override
    protected void processResult(JSONObject responseData){
        try {
            final String status = responseData.getString("status");
            if (status.equals("ok")){
                JSONArray posts = responseData.getJSONArray("posts");

                List<News> newsList = new ArrayList<News>(NEWS_CNT);

                for (int i=0; i < Math.min(NEWS_CNT, posts.length()); i++){
                    News news = new News(posts.getJSONObject(i));
                    newsList.add(news);
                }

                ListView newsListView = (ListView) context.findViewById(R.id.new_list_view);
                final NewsAdapter adapter = new NewsAdapter(context, newsList);
                newsListView.setAdapter(adapter);
            } else if (status.equals("exception")){
                throw new JSONException(responseData.getString("message"));
            } else {
                throw new JSONException("Unknown error");
            }

        } catch (JSONException e) {
            Toast.makeText(context,R.string.news_load_error, Toast.LENGTH_LONG).show();
            Log.d("http", e.getMessage(), e);
        }

        List<JSONObject> news = new ArrayList<JSONObject>(NEWS_CNT);

    }
}
