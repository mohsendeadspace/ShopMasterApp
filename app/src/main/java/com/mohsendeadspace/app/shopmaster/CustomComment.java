package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomComment  extends LinearLayout {


    public static TextView txtUser;
    public static TextView txtlike;
    public static TextView txtdislike;
    public static TextView txtCommentTitle;
    public static TextView txtCommentContent;

    public static LinearLayout linearPositive;
    public static LinearLayout linearPoints;

    CustomPoint customPoint;
    CustomPositive customPositive;
    CustomNegative customNegative;

    public CustomComment(Context context) {
        super(context);
        init(context);
    }

    public CustomComment(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomComment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.custom_comment,this,true);


        txtUser = (TextView) view.findViewById(R.id.txtUser);
        txtlike = (TextView) view.findViewById(R.id.txtlike);
        txtdislike = (TextView) view.findViewById(R.id.txtdislike);
        txtCommentTitle = (TextView) view.findViewById(R.id.txtCommentTitle);
        txtCommentContent = (TextView) view.findViewById(R.id.txtCommentContent);

        linearPositive = (LinearLayout) view.findViewById(R.id.linearPositive);
        linearPoints = (LinearLayout) view.findViewById(R.id.linearPoints);

        customPoint = new CustomPoint(G.context);
        customPositive = new CustomPositive(G.context);
        customNegative = new CustomNegative(G.context);

    }
}
