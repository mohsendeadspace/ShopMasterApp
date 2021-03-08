package com.mohsendeadspace.app.shopmaster;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskMostSellProduct extends AsyncTask {

    public String link = "";
    public AsyncTaskMostSellProduct(String link) {
        this.link = link;

    }
    @Override
    protected Object doInBackground(Object[] objects) {

        try {


            URL url = new URL(link);
            URLConnection connection = url.openConnection();


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            MainActivity.mostSellProduct = builder.toString();

        }catch (Exception e) {

        }
        return "";
    }
}
