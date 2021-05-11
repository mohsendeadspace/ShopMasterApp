package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomPositive extends LinearLayout {
    public static TextView txtCustomPositive;


    public CustomPositive(Context context) {
        super(context);
        init(context);
    }

    public CustomPositive(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomPositive(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.positive,this,true);


        txtCustomPositive = (TextView) view.findViewById(R.id.txtCustomPositive);


    }

}
