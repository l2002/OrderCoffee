package com.example.ordercoffee.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.ordercoffee.Adapter.DanhMucAdapter;
import com.example.ordercoffee.Adapter.DrinkHomeAdapter;
import com.example.ordercoffee.Adapter.PhoBienAdapter;
import com.example.ordercoffee.Adapter.SearchAdapter;
import com.example.ordercoffee.Adapter.UpdateDanhMucRec;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.DanhMuc;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UpdateDanhMucRec {

    SearchAdapter searchAdapter;
    RecyclerView.LayoutManager layoutManager;
    MaterialSearchBar materialSearchBar;
    private RecyclerView.Adapter adapter2,adapter;
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;
    private RecyclerView recyclerViewDanhMucList,recyclerViewPhoBienList, rycRecyclerViewFoodList,rycSearch;
    List<String> suggestList = new ArrayList<>();
    DrinkHomeAdapter drinkHomeAdapter;
    PhoBienAdapter phoBienAdapter;
    ////Vertical
    ArrayList<Drink> list;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search();

        recyclerViewDanhMuc();

        addControlsPhoBien();
        readDataPhoBien();

        addControls();
        readData();
    }

    private void search(){
        rycSearch = (RecyclerView) findViewById(R.id.list_search);
        layoutManager = new LinearLayoutManager(this);
        rycSearch.setLayoutManager(layoutManager);
        rycSearch.setHasFixedSize(true);
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.editTextTextPersonName2);
        materialSearchBar.setCardViewElevation(10);

        loadList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(materialSearchBar.getText().isEmpty())
                {
                    searchAdapter=null;
                    rycSearch.setAdapter(searchAdapter);
                }
            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    rycSearch.setAdapter(searchAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    public List<Drink> getDrinkByName (String name)
    {
        final String DATABASE_NAME = "android.db";
        SQLiteDatabase database;
        database=DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from drink where tieude LIKE ?",new String[]{"%"+name+"%"});
        List<Drink> search_list = new ArrayList<>();
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String tieude=cursor.getString(1);
            byte[]anh=cursor.getBlob(2);
            String mota=cursor.getString(3);
            Double gia=cursor.getDouble(4);

            search_list.add(new Drink(id,tieude,anh,mota,gia));
        }
        return search_list;
    }
    public List<String> getDrinkName ()
    {
        final String DATABASE_NAME = "android.db";
        SQLiteDatabase database;
        database=DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select tieude from drink", null);
        List<String> search_list = new ArrayList<>();
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String tieude=cursor.getString(0);

            search_list.add(tieude);
        }
        return search_list;
    }
    private void startSearch(String text) {
        searchAdapter = new SearchAdapter(this, getDrinkByName(text));
        rycSearch.setAdapter(searchAdapter);
    }

    private void loadList() {
        suggestList = getDrinkName();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    private void addControls() {
        list = new ArrayList<>();
        rycRecyclerViewFoodList=findViewById(R.id.drink_View);
        btnLogOut=findViewById(R.id.btnLogOut);
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

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
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
        Cursor cursor=database.rawQuery("select * from drink where id_drink=1 and id_drink=2",null);
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