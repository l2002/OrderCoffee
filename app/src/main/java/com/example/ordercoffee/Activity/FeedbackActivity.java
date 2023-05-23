package com.example.ordercoffee.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.Adapter.CartAdapter;
import com.example.ordercoffee.Adapter.FeedbackAdapter;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.Cart;
import com.example.ordercoffee.Model.Feedback;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {
    final String DATABASE_NAME = "android.db";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<Feedback> list;
    FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.feedback_view);

        addControls();
        readData();
    }

    private void addControls() {

        listView=(ListView) findViewById(R.id.lv_cart);
        list=new ArrayList<>();
        feedbackAdapter=new FeedbackAdapter(this,list);
        listView.setAdapter(feedbackAdapter);
    }

    private  void readData(){
        database= DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from feedback",null);
        list.clear();
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String noidung=cursor.getString(1);


            list.add(new Feedback(id,noidung));
        }
        feedbackAdapter.notifyDataSetChanged();
    }
}
