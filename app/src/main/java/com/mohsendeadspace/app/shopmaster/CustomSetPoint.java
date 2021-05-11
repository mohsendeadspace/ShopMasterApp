package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hedgehog.ratingbar.RatingBar;

public class CustomSetPoint extends LinearLayout {

    public static TextView txtParamTitle;
    public static RatingBar ratingbarSetPoint;

    public CustomSetPoint(Context context) {
        super(context);
        init(context);
    }

    public CustomSetPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSetPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.custom_setpoint,this,true);


        txtParamTitle = (TextView) view.findViewById(R.id.txtParamTitle);
        ratingbarSetPoint = (RatingBar) view.findViewById(R.id.ratingbarSetPoint);

    }

}
