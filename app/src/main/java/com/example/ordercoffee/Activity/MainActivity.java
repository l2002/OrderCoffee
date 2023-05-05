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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateDanhMucRec {

    private RecyclerView.Adapter adapter2,adapter;
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;
    private RecyclerView recyclerViewDanhMucList,recyclerViewPhoBienList, rycRecyclerViewFoodList;

    DrinkHomeAdapter drinkHomeAdapter;
    ////Vertical
    ArrayList<Drink> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewDanhMuc();

        recyclerViewPhoBien();

        addControls();
        readData();
    }

    private void addControls() {
        list = new ArrayList<>();
        rycRecyclerViewFoodList=findViewById(R.id.food_View);
        drinkHomeAdapter = new DrinkHomeAdapter(this, list);
        rycRecyclerViewFoodList.setAdapter(drinkHomeAdapter);
        rycRecyclerViewFoodList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

    private void recyclerViewDanhMuc() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewDanhMucList=findViewById(R.id.recyclerView);
        recyclerViewDanhMucList.setLayoutManager(linearLayoutManager);

        ArrayList<DanhMuc> danhMucDomains=new ArrayList<>();
        danhMucDomains.add(new DanhMuc("Coffee", R.drawable.coffee));
        danhMucDomains.add(new DanhMuc("Tea", R.drawable.logo1));

        adapter2 = new DanhMucAdapter(this, this, danhMucDomains);
        recyclerViewDanhMucList.setAdapter(adapter2);
    }


    private void recyclerViewPhoBien(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPhoBienList=findViewById(R.id.recyclerView2);
        recyclerViewPhoBienList.setLayoutManager(linearLayoutManager);

        ArrayList<Drink> pb=new ArrayList<>();
        pb.add(new Drink(1,"Coffee",null,"Ngon",10));
        pb.add(new Drink(2,"Capuchino",null,"Đắng",10));
        pb.add(new Drink(3,"Trà đào",null,"Chua",10));

        adapter2=new PhoBienAdapter(pb);
        recyclerViewPhoBienList.setAdapter(adapter2);
    }

    @Override
    public void callBack(int position, ArrayList<Drink> list) {
//        drinkHomeAdapter = new DrinkHomeAdapter(this, list);
//        drinkHomeAdapter.notifyDataSetChanged();
//        rycRecyclerViewFoodList.setAdapter(drinkHomeAdapter);
    }
}