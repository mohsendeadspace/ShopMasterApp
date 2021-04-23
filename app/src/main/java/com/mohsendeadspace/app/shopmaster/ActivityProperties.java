package com.mohsendeadspace.app.shopmaster;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityProperties extends AppCompatActivity {
    public static String data = "";
    public ArrayList<String> titles;
    public ArrayList<String> values;
    TextView productname;
    public String pName;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearCustomProperties;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);
        titles = new ArrayList<>();
        values = new ArrayList<>();

        productname= findViewById(R.id.productname);
        linearCustomProperties= findViewById(R.id.linearCustomProperties);

        //CustomProperties customProperties = new CustomProperties(G.context);

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0;i< jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                pName=title;


                JSONArray protitle = object.getJSONArray("protitle");
                for (int j = 0; j< protitle.length(); j++){
                    String protitle2 = (String) protitle.get(j);
                    Log.i("PROTITLE",protitle2);

                    titles.add(protitle2);


                    /*CustomPoint customPoint = new CustomPoint(G.context);
                    customPoint.txtPoint.setText(title2);
                    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearPoint.addView(customPoint);*/
                }

                    JSONArray val = object.getJSONArray("val");
                for (int k = 0;k<val.length();k++){
                    String val2 = (String) val.get(k);
                    values.add(val2);
                    //Log.i("LOG",one+"/"+two+"/"+three+"/"+four+"/"+five);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        productname.setText(pName);
        for (int i=0;i<titles.size();i++){

            CustomProperties customProperties = new CustomProperties(G.context);
            customProperties.pname.setText(titles.get(i));
            customProperties.pvalue.setText(values.get(i));

            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linearCustomProperties.addView(customProperties);

        }



    }
}
