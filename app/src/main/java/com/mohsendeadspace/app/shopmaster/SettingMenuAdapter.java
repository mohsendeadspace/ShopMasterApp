package com.mohsendeadspace.app.shopmaster;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingMenuAdapter extends ArrayAdapter {

  public  int resourceId;
  public Activity context;
  public ArrayList<SettingMenuListItem> object;

  public SettingMenuAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
    super(context, resource, objects);

    this.resourceId = resource;
    this.context =context;
    this.object = objects;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = convertView;
    view = this.context.getLayoutInflater().inflate(this.resourceId,null);
    TextView txtTitle = (TextView) view.findViewById(R.id.setting_title_item);

    SettingMenuListItem settingMenuListItem = object.get(position);
    txtTitle.setText(settingMenuListItem.title);
    return view;
  }
}
