package com.mohsendeadspace.app.shopmaster;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductAmazing extends LinearLayout {

    LinearLayout amazinglenar;
    public int id;
    public static ImageView pic;
    public static TextView title;
    public static TextView pPrice;
    public static TextView price;
    public ProductAmazing(Context context) {
        super(context);
        init(context);
    }

    public ProductAmazing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductAmazing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.product_amazing,this,true);

        pic = (ImageView) view.findViewById(R.id.imgPic);
        title = (TextView) view.findViewById(R.id.txttitle);
        pPrice = (TextView) view.findViewById(R.id.txtpprice);
        price = (TextView) view.findViewById(R.id.txtprice);
        amazinglenar = (LinearLayout) view.findViewById(R.id.amazinglenar);

        amazinglenar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(G.context,id+"",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context,ActivityWait.class);
                //زمانی که میخاهیم از یک غیر اکتیویتی به اکتویتی برویم از این متد پاینن استفاده میکنیم.
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",id);
                G.context.startActivity(intent);
            }
        });
    }
}
