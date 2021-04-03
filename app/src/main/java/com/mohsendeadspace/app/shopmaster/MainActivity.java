package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

  LinearLayout userEmail;
  LinearLayout linerShowAllProduct;
  SliderLayout sliderShow;
  ArrayList<String> urlPics;
  ArrayList<String> names;
  ImageView img_menu;
  ImageView banner_one,banner_two,banner_three,banner_four,banner_five,banner_six;

  public static SharedPreferences preferences;

  ArrayList<String> bannerArray;

  DrawerLayout drawerLayout;
  String drawermenulist;
  ListView list_navigat;
  ListView list_product;
  ListView setting_list;
  TextView txt_singup;
  TextView txt_basket;
  TextView txt_exit,txtsecond,txtmin,txthour;
  public static String timer = "";
  public static String amaizingProduct = "";
  public static String newProduct = "";
  public static String mostSellProduct = "";
  public static String uniqeProduct = "";
  public static String banners = "";


  public static Handler handler;
  int hourTimer = 0;
  int minTimer = 0;
  int secTimer = 0;
  ProductAmazing productAmazing;
  ProductUniqe productUniqe;
  LinearLayout.LayoutParams layoutParams;
  LinearLayout linearProductAmazing;
  LinearLayout linearProductNew;
  LinearLayout linearProductMostSell;
  LinearLayout linearProductUniqe;
  //String[] menues = {"منو", "ثبت نام", "سبد خرید"};

  ArrayList<BuyMenuListItem> items;
  ArrayList<ProductMenuListItem> productitems;
  ArrayList<SettingMenuListItem> settingitems;

  @Override
  protected void onActivityResult(int requestCode, int resultCode,Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK){
      Bundle bundle = data.getExtras();
      String email = bundle.getString("email");
      txt_singup.setText(email);

      preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
      SharedPreferences.Editor editor = preferences.edit();
      editor.putString("email",email);
      editor.commit();
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_layout);

    bannerArray = new ArrayList<String>();



    sliderShow = (SliderLayout) findViewById(R.id.slider);
    img_menu = (ImageView) findViewById(R.id.img_menu);

    banner_one = (ImageView) findViewById(R.id.banner_one);
    banner_two = (ImageView) findViewById(R.id.banner_two);
    banner_three = (ImageView) findViewById(R.id.banner_three);
    banner_four = (ImageView) findViewById(R.id.banner_four);
    banner_five = (ImageView) findViewById(R.id.banner_five);
    banner_six = (ImageView) findViewById(R.id.banner_six);

    drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    list_navigat = (ListView) findViewById(R.id.list_navigat);
    list_product = (ListView) findViewById(R.id.list_product);
    setting_list = (ListView) findViewById(R.id.setting_list);
    txt_exit = (TextView) findViewById(R.id.txt_exit);
    userEmail = (LinearLayout)findViewById(R.id.userEmail);
    txt_singup = (TextView) findViewById(R.id.txt_singup);
    txtsecond = (TextView) findViewById(R.id.txtsecond);
    txtmin = (TextView) findViewById(R.id.txtmin);
    txthour = (TextView) findViewById(R.id.txthour);

    linearProductAmazing = (LinearLayout) findViewById(R.id.linearProductAmazing);
    linearProductNew = (LinearLayout) findViewById(R.id.linearProductNew);
    linearProductMostSell = (LinearLayout) findViewById(R.id.linearProductMostSell);
    linearProductUniqe = (LinearLayout) findViewById(R.id.linearProductUniqe);

    preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
    String email = preferences.getString("email","ورود/ثبت نام");

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


      ShowAmazingProduct();
      ShowNewProduct();
      ShowMostSellProduct();
      ShowUniqeProduct();
      ShowBanners();



    if (email.equals("")) {
      txt_singup.setText("ورود/ثبت نام");

    }else {
      txt_singup.setText(email);
    }


    txt_singup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (txt_singup.getText().toString().equals("ورود/ثبت نام"))
        {
          Intent intent = new Intent(G.context, ActivityUserSign.class);
          startActivityForResult(intent,0);

        }
        else
          {
          if (userEmail.getVisibility() == View.VISIBLE)
          {
            userEmail.setVisibility(View.GONE);
          }
          else {

            userEmail.setVisibility(View.VISIBLE);
          }

        }
      }
    });

    txt_exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email","");
        editor.putString("do","");
        editor.commit();
        drawerLayout.closeDrawer(Gravity.RIGHT);

      }
    });

    items = new ArrayList<>();
    productitems = new ArrayList<>();
    settingitems = new ArrayList<>();
    items.add(new BuyMenuListItem(R.drawable.ic_home_black_24dp,"خانه"));
    items.add(new BuyMenuListItem(R.drawable.ic_view_list_black_24dp,"لیست دسته بندی محصولات"));
    items.add(new BuyMenuListItem(R.drawable.ic_shopping_basket_black_24dp,"سبد خرید"));

    productitems.add(new ProductMenuListItem("پیشنهاد ویژه مستر شاپ"));
    productitems.add(new ProductMenuListItem("پر فروش ترین ها"));
    productitems.add(new ProductMenuListItem("پر بازدید ترین ها"));
    productitems.add(new ProductMenuListItem("جدیدترین ها"));


    settingitems.add(new SettingMenuListItem("تنظیمات"));
    settingitems.add(new SettingMenuListItem("سوالات متداول"));
    settingitems.add(new SettingMenuListItem("درباره ما"));



    list_navigat.setAdapter(new BuyMenuAdapter(MainActivity.this,R.layout.buy_menu_list,items));
    list_product.setAdapter(new ProductMenuAdapter(MainActivity.this,R.layout.product_menu_list,productitems));
    setting_list.setAdapter(new SettingMenuAdapter(MainActivity.this,R.layout.setting_menu_list,settingitems));
    img_menu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        String email = preferences.getString("email","ورود/ثبت نام");
        String insertDone = preferences.getString("do","");

        if (email.equals("")) {
          if (insertDone.equals("")){
              txt_singup.setText("ورود/ثبت نام");
          }else {
              txt_singup.setText(insertDone);
          }


        }else {
          txt_singup.setText(email);
        }
        userEmail.setVisibility(View.GONE);
       drawerLayout.openDrawer(Gravity.RIGHT);

      }
    });

    urlPics = new ArrayList<>();
    names = new ArrayList<>();
    urlPics.add("http://192.168.1.5/AndroidProject/MasterShop/img/banner_final.jpg");
    urlPics.add("http://192.168.1.5/AndroidProject/MasterShop/img/banner_two.png");
    urlPics.add("http://192.168.1.5/AndroidProject/MasterShop/img/mobile2.jpg");

    names.add("گوشی");
    names.add("لپ تاپ");
    names.add("دوچرخه");

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


    /*TextSliderView textSliderView = new TextSliderView(this);
    textSliderView
      .description("Game of Thrones")
      .image("https://daftareshoma.com/wp-content/uploads/2019/04/Game-of-Thrones.jpg");
    sliderShow.addSlider(textSliderView);*/


    linerShowAllProduct = (LinearLayout) findViewById(R.id.linerShowAllProduct);
    linerShowAllProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(G.context, "clicked", Toast.LENGTH_SHORT).show();
      }
    });

  }

  public void createProductAmazing(int id,String title, String pPrice, String price, String picUrl){

    productAmazing = new ProductAmazing(G.context);
    productAmazing.id = id;
    ProductAmazing.title.setText(title);
    ProductAmazing.pPrice.setText(pPrice);
    ProductAmazing.price.setText(price);
    Picasso.with(G.context).load(picUrl).into(ProductAmazing.pic);
    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    linearProductAmazing.addView(productAmazing);

  }

  public void createProductNew(String title, String pPrice, String price, String picUrl){

    productAmazing = new ProductAmazing(G.context);
    ProductAmazing.title.setText(title);
    ProductAmazing.pPrice.setText(pPrice);
    ProductAmazing.price.setText(price);
    Picasso.with(G.context).load(picUrl).into(ProductAmazing.pic);
    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    linearProductNew.addView(productAmazing);

  }

  public void createProductMostSell(String title, String pPrice, String price, String picUrl){

    productAmazing = new ProductAmazing(G.context);
    ProductAmazing.title.setText(title);
    ProductAmazing.pPrice.setText(pPrice);
    ProductAmazing.price.setText(price);
    Picasso.with(G.context).load(picUrl).into(ProductAmazing.pic);
    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    linearProductMostSell.addView(productAmazing);

  }

  public void createProductUniqe(String title, String price, String picUrl){

    productUniqe = new ProductUniqe(G.context);
    ProductUniqe.title.setText(title);
    ProductUniqe.price.setText(price);
    Picasso.with(G.context).load(picUrl).into(ProductUniqe.pic);
    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    linearProductUniqe.addView(productUniqe);

  }

  public void ShowUniqeProduct(){
    try {
      JSONArray jsonArray = new JSONArray(uniqeProduct);
      for (int i = 0;i< jsonArray.length();i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        int id = object.getInt("id");
        String title = object.getString("title");
        String price = String.valueOf(object.getInt("price")+ "تومان") ;
        String pic = object.getString("pic");
        String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

        createProductUniqe(title,price,picUrl);

      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }




  public void ShowAmazingProduct(){
    try {
      JSONArray jsonArray = new JSONArray(amaizingProduct);
      for (int i = 0;i< jsonArray.length();i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        int id = object.getInt("id");
        String title = object.getString("title");
        String  pPrice = String.valueOf(object.getInt("pprice")+ "تومان") ;
        String price = String.valueOf(object.getInt("price")+ "تومان") ;
        String pic = object.getString("pic");
        String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

        createProductAmazing(id,title,pPrice,price,picUrl);

      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void ShowNewProduct(){
    try {
      JSONArray jsonArray = new JSONArray(newProduct);
      for (int i = 0;i< jsonArray.length();i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        int id = object.getInt("id");
        String title = object.getString("title");
        String  pPrice = String.valueOf(object.getInt("pprice")+ "تومان") ;
        String price = String.valueOf(object.getInt("price")+ "تومان") ;
        String pic = object.getString("pic");
        String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

        createProductNew(title,pPrice,price,picUrl);

      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void ShowMostSellProduct(){
    try {
      JSONArray jsonArray = new JSONArray(mostSellProduct);
      for (int i = 0;i< jsonArray.length();i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        int id = object.getInt("id");
        String title = object.getString("title");
        String  pPrice = String.valueOf(object.getInt("pprice")+ "تومان") ;
        String price = String.valueOf(object.getInt("price")+ "تومان") ;
        String pic = object.getString("pic");
        String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

        createProductMostSell(title,pPrice,price,picUrl);


      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }


  public void ShowBanners(){
    try {
      JSONArray jsonArray = new JSONArray(banners);
      for (int i = 0;i< jsonArray.length();i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        int id = object.getInt("id");
        String pic = object.getString("pic");
        String picUrl = "http://192.168.1.5/AndroidProject/MasterShop/img/"+pic;

        bannerArray.add(i,picUrl);
      }
      Picasso.with(G.context).load(bannerArray.get(0)).into(banner_one);
      Picasso.with(G.context).load(bannerArray.get(1)).into(banner_two);
      Picasso.with(G.context).load(bannerArray.get(2)).into(banner_three);
      Picasso.with(G.context).load(bannerArray.get(3)).into(banner_four);
      Picasso.with(G.context).load(bannerArray.get(4)).into(banner_five);
      Picasso.with(G.context).load(bannerArray.get(5)).into(banner_six);


    } catch (JSONException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void onSliderClick(BaseSliderView slider) {
    Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }
}
