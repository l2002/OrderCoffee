package com.example.ordercoffee.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Helper.ManagementCart;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.io.ByteArrayOutputStream;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView btnAddToCart;
    private TextView txtTieuDe,txtGia,txtMoTa,txtNumOrder,txtTotalPrice;
    private ImageView picDrink;
    private TextView btnPlus,btnMinus;
    private Drink object;
    int soLuong=1;
    private ManagementCart managementCart;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart=new ManagementCart(this);
        initView();
        getBundel();
    }

    private byte[] getByteArrayImageView(ImageView imageView) {
        BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]bytes=stream.toByteArray();
        return bytes;
    }

    private void getBundel() {
        object=(Drink) getIntent().getSerializableExtra("object");

        getByteArrayImageView(picDrink);
        Bitmap bitmap= BitmapFactory.decodeByteArray(object.getAnh(),0,object.getAnh().length);
        picDrink.setImageBitmap(bitmap);

        txtTieuDe.setText(object.getTieuDe());
        txtGia.setText(String.valueOf(object.getFee())+" "+"VND");
        txtMoTa.setText(object.getMoTa());

        txtNumOrder.setText(String.valueOf(soLuong));
        txtTotalPrice.setText(String.valueOf(soLuong*object.getFee())+" "+"VND");
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               soLuong=soLuong+1;
               txtNumOrder.setText(String.valueOf(soLuong));
               txtTotalPrice.setText(String.valueOf(soLuong*object.getFee()+" "+"VND"));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soLuong>1){
                    soLuong=soLuong-1;
                }
                txtNumOrder.setText(String.valueOf(soLuong));
                txtTotalPrice.setText(soLuong*object.getFee()+" "+"VND");
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(soLuong);
                managementCart.insertDrink(object);
            }
        });
    }

    private void initView() {
        btnAddToCart=findViewById(R.id.btnAddToCard);
        txtTieuDe=findViewById(R.id.txtTieuDe);
        picDrink=findViewById(R.id.picDrink);
        txtGia=findViewById(R.id.txtGia);
        txtMoTa=findViewById(R.id.txtMota);
        txtNumOrder=findViewById(R.id.txtNumOrder);
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        txtTotalPrice=findViewById(R.id.txtTongTIen);
    }
}
