package com.agh.faustyna.mobile.listadapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agh.faustyna.mobile.R;
import com.agh.faustyna.mobile.http.parsedjson.News;
import com.agh.faustyna.mobile.http.tasks.DownloadImageTask;

import java.util.List;

/**
 * Created by Klaudia on 14.11.2015.
 */
public class NewsAdapter extends BaseAdapter{
    private List<News> newsList;
    private Context context;

    public NewsAdapter(Context context, List<News> newsList ) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_news, null);
        }

        News news = newsList.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.news_title_textview);
        titleTextView.setText(Html.fromHtml(news.title));

        TextView dateTextView = (TextView) convertView.findViewById(R.id.news_date_textview);
        dateTextView.setText(news.date);

        ImageView thumbnailImageView = (ImageView) convertView.findViewById(R.id.news_thumbnail_image);
        new DownloadImageTask(thumbnailImageView).execute(news.thumbnailUrl);

        final WebView contentWebView = (WebView) convertView.findViewById(R.id.news_content_webview);
        contentWebView.getSettings().setJavaScriptEnabled(true);
        contentWebView.loadDataWithBaseURL("", news.content, "text/html", "UTF-8", "");

        //make news expandable
        View newsHeader = convertView.findViewById(R.id.new_header);
        newsHeader.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contentWebView.setVisibility(contentWebView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        return convertView;
    }
}
