package com.mohsendeadspace.app.shopmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityShow extends AppCompatActivity  implements BaseSliderView.OnSliderClickListener {

    SliderLayout sliderShow;
    ArrayList<String> urlPics;
    ArrayList<String> names;
    AppBarLayout appBarLayout;
    ImageView wbasket,wsearch,wmenu;
    public static String data= "";
    public static String info= "";
    public static String timer= "";
    TextView txttitle_one,txttitle_two,txtcolor,txtGaurantee,txtDesc,txtPrice,txtMore,txtMark;
    LinearLayout linearPoint,btnComment,btnfani;
    LinearLayout.LayoutParams layoutParams;
    public int pointLenth = 0;


    RatingBar ratingBar;
    public static Handler handler;
    public int hourTimer = 0;
    public int minTimer = 0;
    public int secTimer = 0;
     public TextView txt_exit,txtsecond,txtmin,txthour;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle =getIntent().getExtras();
        final String id = bundle.getString("id");
        //Toast.makeText(G.context,id,Toast.LENGTH_SHORT).show();
        // Log.i("Log",info);
        //Toast.makeText(G.context,info,Toast.LENGTH_LONG).show();

        txttitle_one =(TextView) findViewById(R.id.txttitle_one);
        txttitle_two =(TextView) findViewById(R.id.txttitle_two);
        txtcolor =(TextView) findViewById(R.id.txtcolor);
        txtGaurantee =(TextView) findViewById(R.id.txtGaurantee);
        txtDesc =(TextView) findViewById(R.id.txtDesc);
        txtPrice =(TextView) findViewById(R.id.txtPrice);
        txtMore =(TextView) findViewById(R.id.txtMore);
        txtMark =(TextView) findViewById(R.id.txtMark);
        linearPoint =(LinearLayout) findViewById(R.id.linearPoint);
        btnComment =(LinearLayout) findViewById(R.id.btnComment);
        btnfani =(LinearLayout) findViewById(R.id.btnfani);
        ratingBar =(RatingBar) findViewById(R.id.ratingBar);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(G.context,ActivityComment.class);
                startActivity(i);

            }
        });

        btnfani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTaskGetProperties("http://192.168.1.5/AndroidProject/MasterShop/fani.php",id).execute();

                final ProgressDialog dialog = new ProgressDialog(ActivityShow.this);
                dialog.setMessage("لطفا منتظر بمانید ...");
                dialog.show();

                final Timer timer =  new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!ActivityProperties.data.equals("")){

                                    dialog.cancel();
                                    Intent intent = new Intent(G.context,ActivityProperties.class);
                                    startActivity(intent);
                                    timer.cancel();

                                }
                            }
                        });
                    }
                },1,1000);


            }
        });


        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtMore.getText().equals("ادامه مطلب")){
                    txtDesc.setLayoutParams
                            (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    txtMore.setText("بستن");
                }else {
                    txtDesc.setLayoutParams
                            (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    100));
                    txtMore.setText("ادامه مطلب");
                }
            }
        });



        urlPics = new ArrayList<>();
        names = new ArrayList<>();

        names.add("گوشی");
        names.add("لپ تاپ");
        names.add("دوچرخه");
        names.add("هووای");

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0;i< jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String pic = object.getString("pic");
                String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

                urlPics.add(picUrl);
                Log.i("LOG",urlPics.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONArray jsonArray = new JSONArray(info);
            for (int i = 0;i< jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                String price = String.valueOf(object.getInt("price")) ;
                String desc = object.getString("intro");
                String color = object.getString("color");
                String gaurantee = object.getString("gaurantee");

                float rating = Float.valueOf(object.getString("rating"));
                ratingBar.setRating(rating);
                txtMark.setText(rating+" از 5");

                JSONArray points = object.getJSONArray("point");
                for (int j = 0; j< points.length(); j++){
                    String title2 = (String) points.get(j);
                    pointLenth = points.length();
                    //Toast.makeText(G.context,pointLenth+"",Toast.LENGTH_SHORT).show();
                    CustomPoint customPoint = new CustomPoint(G.context);
                    customPoint.txtPoint.setText(title2);
                    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearPoint.addView(customPoint);
                }


              /*  JSONArray rate = object.getJSONArray("comment");
                for (int k = 0;k<rate.length();k++){
                    JSONObject object2 = rate.getJSONObject(k);
                    String one = object2.getString("1");
                    String two = object2.getString("2");
                    String three = object2.getString("3");
                    String four = object2.getString("4");
                    String five = object2.getString("5");
                    Log.i("LOG",one+"/"+two+"/"+three+"/"+four+"/"+five);
                }*/

                txttitle_one.setText(title);
                txttitle_two.setText(title);
                txtPrice.setText(price+" تومان");
                txtDesc.setText(Html.fromHtml(desc));
                txtcolor.setText(color);
                txtGaurantee.setText(gaurantee);




            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        txtsecond = (TextView) findViewById(R.id.txtsecond);
        txtmin = (TextView) findViewById(R.id.txtmin);
        txthour = (TextView) findViewById(R.id.txthour);

        String[] time = timer.split(":");
        final int hour = Integer.parseInt(time[0]);
        final int min = Integer.parseInt(time[1]);
        final int sec = Integer.parseInt(time[2]);

        hourTimer = hour;
        minTimer = min;
        secTimer = sec;

        txthour.setText(time[0]);
        txtmin.setText(time[1]);
        txtsecond.setText(time[2]);
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        Thread.sleep(1000);
                        if (secTimer == 0) {
                            if (minTimer != 0) {
                                minTimer--;
                                secTimer = 59;
                            } else {
                                hourTimer--;
                                minTimer = 59;
                                secTimer = 59;
                            }
                        } else {
                            secTimer--;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (hourTimer <10){
                                    txthour.setText("0"+ hourTimer + "");
                                }else{
                                    txthour.setText( hourTimer + "");
                                }

                                if (minTimer <10){
                                    txtmin.setText("0"+ minTimer + "");
                                }else{
                                    txtmin.setText( minTimer + "");
                                }

                                if (secTimer <10){
                                    txtsecond.setText("0"+secTimer + "");
                                }else{
                                    txtsecond.setText( secTimer + "");
                                }


                            }
                        });
                    }

                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();







        sliderShow = (SliderLayout) findViewById(R.id.sliderShow);

        for (int i = 0; i < urlPics.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(urlPics.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", names.get(i));

            sliderShow.addSlider(textSliderView);
        }


        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        wbasket = (ImageView) findViewById(R.id.wbasket);
        wsearch = (ImageView) findViewById(R.id.wsearch);
        wmenu = (ImageView) findViewById(R.id.wmenu);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scroll = -(verticalOffset);
                if (scroll >= 358) {
                    wbasket.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
                    wsearch.setImageResource(R.drawable.ic_search_black_24dp);
                    wmenu.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
                }
                else {
                    wbasket.setImageResource(R.drawable.ic_baseline_local_grocery_store_24);
                    wsearch.setImageResource(R.drawable.ic_baseline_search_black);
                    wmenu.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
                }
            }
        });

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
