package com.mohsendeadspace.app.shopmaster;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class AsyncTaskGetProperties  extends AsyncTask {


    public String link = "";
    public String id = "";

    public AsyncTaskGetProperties(String link,  String id) {
        this.link = link;
        this.id = id;

    }
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            String data = URLEncoder.encode("id","UTF8")+"="+URLEncoder.encode(id,"UTF8");


            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            ActivityProperties.data = builder.toString();

        }catch (Exception e) {

        }
        return "";
    }

}
