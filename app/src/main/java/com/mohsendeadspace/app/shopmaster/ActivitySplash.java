package com.mohsendeadspace.app.shopmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new AsyncTaskTimer("http://192.168.1.4/AndroidProject/MasterShop/androidtimer.php").execute();
        new AsyncTaskAmaizingProduct("http://192.168.1.4/AndroidProject/MasterShop/readamazing.php").execute();
        new AsyncTaskNewProduct("http://192.168.1.4/AndroidProject/MasterShop/newproduct.php").execute();
        new AsyncTaskMostSellProduct("http://192.168.1.4/AndroidProject/MasterShop/mostsell.php").execute();
        new AsyncTaskUniqeProduct("http://192.168.1.4/AndroidProject/MasterShop/unique.php").execute();
        new AsyncTaskBanners("http://192.168.1.4/AndroidProject/MasterShop/readbanner.php").execute();

        final Timer timer =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!MainActivity.timer.equals("")
                                & !MainActivity.amaizingProduct.equals("")
                                & !MainActivity.newProduct.equals("")
                                & !MainActivity.mostSellProduct.equals("")
                                & !MainActivity.uniqeProduct.equals("")
                                & !MainActivity.banners.equals("")){
                            Intent intent = new Intent(G.context,MainActivity.class);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    }
                });
            }
        },1,1000);
    }
}
