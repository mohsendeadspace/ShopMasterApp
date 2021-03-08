package com.mohsendeadspace.app.shopmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class ActivityUserSign extends AppCompatActivity {


  public static String data = "";
  AppCompatCheckBox showPass;
  EditText edt_Email,edt_pass;
  TextView txt_register;
  LinearLayout btnGo;
  SharedPreferences preferences;

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK){
      Bundle bundle = data.getExtras();
      String insertDone = bundle.getString("do");

      preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
      SharedPreferences.Editor editor = preferences.edit();
      editor.putString("do",insertDone);
      editor.commit();

      Intent intent = new Intent(G.context,MainActivity.class);
      startActivity(intent);
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    setContentView(R.layout.user_sign);
    super.onCreate(savedInstanceState);


    showPass = (AppCompatCheckBox) findViewById(R.id.showPass);
    edt_Email =(EditText) findViewById(R.id.edt_Email);
    edt_pass =(EditText) findViewById(R.id.edt_pass);
    txt_register =(TextView) findViewById(R.id.txt_register);
    btnGo =(LinearLayout) findViewById(R.id.btnGO);

    btnGo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String email = edt_Email.getText().toString();
        String pass = edt_pass.getText().toString();

        new AsyncTaskConnect("http://192.168.1.4/AndroidProject/MasterShop/index/",email,pass).execute();


        final ProgressDialog dialog = new ProgressDialog(ActivityUserSign.this);
        dialog.setMessage("لطفا منتظر بمانید ...");
        dialog.show();

        final Timer timer =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                if(!data.equals("")){

                  dialog.cancel();
                  //Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();

                  if (data.equals("Not exsist")){

                    Toast.makeText(G.context,"پست الکترونیکی یا کلمه عبور اشتباه است.",Toast.LENGTH_SHORT).show();
                    timer.cancel();
                  }else {
                    Intent intent = new Intent(G.context,MainActivity.class);
                    intent.putExtra("email",data);
                    setResult(RESULT_OK,intent);
                    timer.cancel();
                    finish();
                  }

                }
              }
            });
          }
        },1,1000);

      }
    });

    showPass.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (showPass.isChecked()) {
          edt_pass.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else  {
          edt_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        }
      }
    });

    txt_register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(G.context,ActivityUserSingUp.class);
        startActivityForResult(intent,0);
      }
    });

  }
}
