package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomPoint  extends LinearLayout {

    TextView txtPoint;
    ProgressBar progressbar;


    public CustomPoint(Context context) {
        super(context);
        init(context);
    }

    public CustomPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.custom_point,this,true);

        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        txtPoint = (TextView) view.findViewById(R.id.txtPoint);

    }


}
