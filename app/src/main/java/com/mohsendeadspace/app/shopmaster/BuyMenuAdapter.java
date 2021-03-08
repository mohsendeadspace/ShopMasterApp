package com.mohsendeadspace.app.shopmaster;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BuyMenuAdapter extends ArrayAdapter {

  public  int resourceId;
  public Activity context;
  public ArrayList<BuyMenuListItem> object;

  public BuyMenuAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
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
    ImageView img = (ImageView)view.findViewById(R.id.img_menu_list);
    TextView txtTitle = (TextView) view.findViewById(R.id.txt_title_menu_list);

    BuyMenuListItem buyMenuListItem = object.get(position);
    txtTitle.setText(buyMenuListItem.title);
    img.setImageResource(buyMenuListItem.img);
    return view;
  }
}
