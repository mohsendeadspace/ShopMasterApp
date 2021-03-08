package com.mohsendeadspace.app.shopmaster;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProductMenuAdapter extends ArrayAdapter {

  public  int resourceId;
  public Activity context;
  public ArrayList<ProductMenuListItem> object;

  public ProductMenuAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
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
    TextView txtTitle = (TextView) view.findViewById(R.id.product_title_item);

    ProductMenuListItem productMenuListItem = object.get(position);
    txtTitle.setText(productMenuListItem.title);
    return view;
  }
}
