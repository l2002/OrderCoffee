package com.example.ordercoffee.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.Adapter.DrinkAdapter;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DisplayDrinkActivity extends AppCompatActivity {
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<Drink> list;
    DrinkAdapter foodAdapter;
    FloatingActionButton btnAdd;
    Button btnHome;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.listview_drink_crud);

        addControls();
        readData();
    }

    private void addControls() {
        btnAdd= (FloatingActionButton) findViewById(R.id.btnAddDrink);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayDrinkActivity.this,AddDrinkActivity.class);
                startActivity(intent);
            }
        });
        listView=(ListView) findViewById(R.id.lv_drink);
        list=new ArrayList<>();
        foodAdapter=new DrinkAdapter(this,list);
        listView.setAdapter(foodAdapter);
    }

    private  void readData(){
        database=DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from drink",null);
        list.clear();
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String tieude=cursor.getString(1);
            byte[]anh=cursor.getBlob(2);
            String mota=cursor.getString(3);
            Double gia=cursor.getDouble(4);

            list.add(new Drink(id,tieude,anh,mota,gia));
        }
        foodAdapter.notifyDataSetChanged();
    }
}