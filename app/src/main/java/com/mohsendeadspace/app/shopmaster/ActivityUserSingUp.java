package com.mohsendeadspace.app.shopmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityUserSingUp extends AppCompatActivity {


  public static String data = "";
  EditText edt_Email,edt_pass,edt_repass;
  LinearLayout linearSingUp;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_signup);

    edt_Email = (EditText) findViewById(R.id.edt_Email);
    edt_pass = (EditText) findViewById(R.id.edt_pass);
    edt_repass = (EditText) findViewById(R.id.edt_repass);
    linearSingUp = (LinearLayout) findViewById(R.id.linearSingUp);

    linearSingUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String email = edt_Email.getText().toString();
        String pass = edt_pass.getText().toString();
        String repass = edt_repass.getText().toString();

        if (pass.equals(repass)){

          new AsyncTaskInsertUser("http://192.168.1.5/AndroidProject/MasterShop/insertuser/",email,pass).execute();

          final ProgressDialog dialog = new ProgressDialog(ActivityUserSingUp.this);
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

                    Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();


                      if (data.equals("not ok") )
                      {
                        Toast.makeText(G.context,"متاسفانه ارتباط با سرور قطع میباشد.",Toast.LENGTH_SHORT).show();
                        timer.cancel();
                      }else if (data.equals("exist")){
                        Toast.makeText(G.context,"این ایمیل قبلا ثبت نام کرده است.",Toast.LENGTH_SHORT).show();
                        timer.cancel();
                      }
                      else
                      {
                        Intent intent = new Intent(G.context,ActivityUserSign.class);
                        intent.putExtra("do",data);
                        setResult(RESULT_OK,intent);
                        timer.cancel();
                        finish();
                      }

                  }
                }
              });
            }
          },1,1000);





        }else {

          Toast.makeText(G.context,"کلمه عبور با تکرار آن مطابقت ندارد.",Toast.LENGTH_SHORT).show();
        }

      }
    });




  }
}
