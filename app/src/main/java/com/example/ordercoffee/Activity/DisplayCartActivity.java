package com.example.ordercoffee.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.Adapter.CartAdapter;
import com.example.ordercoffee.Adapter.DrinkAdapter;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.Cart;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DisplayCartActivity  extends AppCompatActivity {
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<Cart> list;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.lv_cart_activity);

        addControls();
        readData();
    }

    private void addControls() {

        listView=(ListView) findViewById(R.id.lv_cart);
        list=new ArrayList<>();
        cartAdapter=new CartAdapter(this,list);
        listView.setAdapter(cartAdapter);
    }

    private  void readData(){
        database= DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from cart",null);
        list.clear();
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id_cart=cursor.getInt(0);
            Double delivery=cursor.getDouble(2);
            Double tax=cursor.getDouble(3);
            Double total=cursor.getDouble(4);


            list.add(new Cart(id_cart,delivery,tax,total));
        }
        cartAdapter.notifyDataSetChanged();
    }
}
