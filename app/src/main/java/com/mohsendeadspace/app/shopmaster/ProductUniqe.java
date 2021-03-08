package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ProductUniqe extends LinearLayout {

    public static ImageView pic;
    public static TextView title;
    public static TextView price;
    public ProductUniqe(Context context) {
        super(context);
        init(context);
    }

    public ProductUniqe(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductUniqe(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_uniqe,this,true);

        pic = (ImageView) view.findViewById(R.id.imgPic);
        title = (TextView) view.findViewById(R.id.txttitle);
        price = (TextView) view.findViewById(R.id.txtprice);
    }
}
