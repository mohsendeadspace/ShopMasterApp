package com.mohsendeadspace.app.shopmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityWait extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle bundle=getIntent().getExtras();
        int id = bundle.getInt("id");
        String idFinal=String.valueOf(id);

        new AsyncTaskGetDetail("http://192.168.1.5/AndroidProject/MasterShop/getdetail.php",idFinal).execute();
        new AsyncTaskShowTimer("http://192.168.1.5/AndroidProject/MasterShop/androidtimer.php").execute();
        new AsyncTaskGetPruductInfo("http://192.168.1.5/AndroidProject/MasterShop/getproductinfo.php",idFinal).execute();


        final Timer timer =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!ActivityShow.data.equals("")
                                & !ActivityShow.timer.equals("")
                                & !ActivityShow.info.equals("")){
                            Intent intent = new Intent(G.context,ActivityShow.class);
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
