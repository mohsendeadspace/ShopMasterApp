package com.mohsendeadspace.app.shopmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityComment extends AppCompatActivity {

    public static String data = "";
    LinearLayout linearAddCustomComment;
    ArrayList<String> titles;
    ArrayList<String> matns;
    ArrayList<String> positives;
    ArrayList<String> negatives;
    ArrayList<String> likes;
    ArrayList<String> dislikes;
    ArrayList<String> names;
    ArrayList<String> values;
    ArrayList<String> paramstitle;
    LinearLayout.LayoutParams layoutParams;
    int counter = 0;
    int paramlenght = 0;
    FloatingActionButton flotingSetRatingComment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        linearAddCustomComment = (LinearLayout)findViewById(R.id.linearAccomment);
        flotingSetRatingComment = (FloatingActionButton) findViewById(R.id.flotingSetRatingComment);


        flotingSetRatingComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(G.context,ActivitySetPoint.class);
                startActivity(i);
            }
        });


        titles = new ArrayList<>();
        matns = new ArrayList<>();
        positives = new ArrayList<>();
        negatives = new ArrayList<>();
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
        names = new ArrayList<>();
        values = new ArrayList<>();
        paramstitle = new ArrayList<>();


        try {
            JSONObject object = new JSONObject(data);
            JSONArray arrayTitle = new JSONArray(object.getString("title"));
            for (int i=0 ; i<arrayTitle.length();i++){
                String title= arrayTitle.getString(i);
                //Log.i("LOG",title);
                titles.add(title);
            }

            JSONArray arrayMatn = new JSONArray(object.getString("matn"));
            for (int i=0 ; i<arrayMatn.length();i++){
                String matn= arrayMatn.getString(i);
                //Log.i("LOG",matn);
                matns.add(matn);
            }

            JSONArray arrayPositive = new JSONArray(object.getString("positive"));
            for (int i=0 ; i<arrayPositive.length();i++){
                String positive= arrayPositive.getString(i);
                //Log.i("LOG",positive);
                positives.add(positive);
            }

            JSONArray arrayNegatives = new JSONArray(object.getString("negative"));
            for (int i=0 ; i<arrayNegatives.length();i++){
                String negative= arrayNegatives.getString(i);
                //Log.i("LOG",positive);
                negatives.add(negative);
            }

            JSONArray arrayLikes = new JSONArray(object.getString("likecount"));
            for (int i=0 ; i<arrayLikes.length();i++){
                String like= arrayLikes.getString(i);
                //Log.i("LOG",positive);
                likes.add(like);
            }

            JSONArray arrayDislikes = new JSONArray(object.getString("dislikecount"));
            for (int i=0 ; i<arrayDislikes.length();i++){
                String dislike= arrayDislikes.getString(i);
                //Log.i("LOG",positive);
                dislikes.add(dislike);
            }

            JSONArray arrayNames = new JSONArray(object.getString("name"));
            for (int i=0 ; i<arrayNames.length();i++){
                String name= arrayNames.getString(i);
                //Log.i("LOG",positive);
                names.add(name);
            }

            JSONArray arrayParamTitle = new JSONArray(object.getString("paramtitle"));
            for (int i=0 ; i<arrayParamTitle.length();i++){
                String paramtitle= arrayParamTitle.getString(i);
                //Log.i("LOG",positive);
                paramstitle.add(paramtitle);
            }





            JSONArray arrayParam = new JSONArray(object.getString("param"));
            for (int i=0 ; i<arrayParam.length();i++){
                JSONObject paramObject= arrayParam.getJSONObject(i);
                    for (int j = 1;j< paramObject.length(); j ++){
                        String value = (String) paramObject.get(String.valueOf(j));
                        values.add(value);
                    }

            }


            paramlenght = paramstitle.size();
           //Toast.makeText(G.context,paramlenght+"",Toast.LENGTH_SHORT).show();


            for (int k = 0; k<titles.size(); k++){
                String title = titles.get(k);
                String name = names.get(k);
                String like = likes.get(k);
                String dislike = dislikes.get(k);
                String positive = positives.get(k);
                String negative = negatives.get(k);
                String matn = matns.get(k);
                CustomComment customComment = new CustomComment(G.context);
                customComment.txtCommentTitle.setText(title);
                customComment.txtUser.setText(name);
                customComment.txtlike.setText(like);
                customComment.txtdislike.setText(dislike);
                customComment.txtCommentContent.setText(matn);


                CustomPositive customPositive = new CustomPositive(G.context);
                customPositive.txtCustomPositive.setText(positive);
                customComment.linearPositive.addView(customPositive);

                CustomNegative customNegative = new CustomNegative(G.context);
                customNegative.txtCustomNegative.setText(negative);
                customComment.linearPositive.addView(customNegative);



                for (int i = 0; i<paramstitle.size(); i++){
                    if (i+1  % paramlenght == 0 ){
                        counter+=paramlenght-1;
                    }
                    CustomPoint customPoint = new CustomPoint(G.context);
                    customPoint.txtPoint.setText(paramstitle.get(i));
                    customPoint.progressbar.setProgress(Integer.parseInt(values.get(i+counter).trim()));
                    customComment.linearPoints.addView(customPoint);

                }



                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                linearAddCustomComment.addView(customComment);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
