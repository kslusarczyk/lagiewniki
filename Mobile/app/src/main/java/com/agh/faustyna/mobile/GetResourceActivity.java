package com.agh.faustyna.mobile;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.agh.faustyna.mobile.http.tasks.GetResourceTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Klaudia on 17.11.2015.
 */
public abstract class GetResourceActivity extends ProgressBarActivity {
    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private File getFile(String fileName){
        ContextWrapper contextWrapper = new ContextWrapper(this);
        File dataDir = contextWrapper.getFilesDir();
        File file = new File(dataDir, fileName);
        return file;
    }

    private boolean isUpToDate(File file){
        long modificationTime = file.lastModified();
        long yesterday = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
        return modificationTime > yesterday;
    }

    private void loadFromFile(File file, WebView webView){
        String line;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, R.string.brak_zasobu, Toast.LENGTH_LONG).show();
            Log.d("file", e.getMessage(), e);
        } catch (IOException e) {
            Toast.makeText(this, R.string.brak_zasobu, Toast.LENGTH_LONG).show();
            Log.d("file", e.getMessage(), e);
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", stringBuffer.toString(), "text/html", "UTF-8", "");
    }

    public void displayResource(int resorceId, WebView webView){
        hideProgressBar();
        String resourcePath = getString(resorceId);
        String fileName = resourcePath.replace("/?json=1", "").replace("/", "_");
        File file = getFile(fileName);

        if (file.exists()){
            // file exist - checking if it can be used
            if (isUpToDate(file)){
                // if file is not older than 24h it will be used to display on screen
                Log.d("file", "loading from file");
                loadFromFile(file, webView);
            } else {
                if(isOnline()){
                    // trying to load from internet
                    new GetResourceTask(this, resorceId, file, webView).execute();
                }else {
                    // no Internet, loading deprecated content
                    loadFromFile(file, webView);
                }
            }
        }else {
            // no file - trying to download
            if(isOnline()){
                new GetResourceTask(this, resorceId, file, webView).execute();
            }else {
                Toast.makeText(this,R.string.brak_zasobu, Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}
