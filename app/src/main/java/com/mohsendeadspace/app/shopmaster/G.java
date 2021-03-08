package com.mohsendeadspace.app.shopmaster;

import android.app.Application;
import android.content.Context;

public class G extends Application {

  public static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
  }
}
