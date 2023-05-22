package com.example.ordercoffee.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ordercoffee.Adapter.DanhMucAdapter;
import com.example.ordercoffee.Adapter.DrinkAdapter;
import com.example.ordercoffee.Adapter.DrinkHomeAdapter;
import com.example.ordercoffee.Adapter.PhoBienAdapter;
import com.example.ordercoffee.Adapter.UpdateDanhMucRec;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.DanhMuc;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateDanhMucRec {

    private RecyclerView.Adapter adapter2,adapter;
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;
    private RecyclerView recyclerViewDanhMucList,recyclerViewPhoBienList, rycRecyclerViewFoodList;

    DrinkHomeAdapter drinkHomeAdapter;
    PhoBienAdapter phoBienAdapter;
    ////Vertical
    ArrayList<Drink> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewDanhMuc();

        addControlsPhoBien();
        readDataPhoBien();

        addControls();
        readData();
    }

    private void addControls() {
        list = new ArrayList<>();
        rycRecyclerViewFoodList=findViewById(R.id.food_View);
        drinkHomeAdapter = new DrinkHomeAdapter(this, list);
        rycRecyclerViewFoodList.setAdapter(drinkHomeAdapter);
        rycRecyclerViewFoodList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FloatingActionButton cartBtn=findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });


    }
    private  void readData(){
        database= DBHelper.initDatabase(this,DATABASE_NAME);
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
        drinkHomeAdapter.notifyDataSetChanged();
    }

    private void addControlsPhoBien() {
        list = new ArrayList<>();

        recyclerViewPhoBienList=findViewById(R.id.phoBien_View);
        phoBienAdapter = new PhoBienAdapter(this, list);
        recyclerViewPhoBienList.setAdapter(phoBienAdapter);
        recyclerViewPhoBienList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FloatingActionButton cartBtn=findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private  void readDataPhoBien(){
        database= DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from drink where id_drink=1",null);
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
        phoBienAdapter.notifyDataSetChanged();
    }

    private void recyclerViewDanhMuc() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewDanhMucList=findViewById(R.id.recyclerView);
        recyclerViewDanhMucList.setLayoutManager(linearLayoutManager);

        ArrayList<DanhMuc> danhMucDomains=new ArrayList<>();
        danhMucDomains.add(new DanhMuc("Coffee", R.drawable.icon_app));
        danhMucDomains.add(new DanhMuc("Tea", R.drawable.logo1));

        adapter2 = new DanhMucAdapter(this, this, danhMucDomains);
        recyclerViewDanhMucList.setAdapter(adapter2);
    }


    @Override
    public void callBack(int position, ArrayList<Drink> list) {
//        drinkHomeAdapter = new DrinkHomeAdapter(this, list);
//        drinkHomeAdapter.notifyDataSetChanged();
//        rycRecyclerViewFoodList.setAdapter(drinkHomeAdapter);
    }
}