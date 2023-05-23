package com.example.ordercoffee.Activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThanksActivity extends AppCompatActivity {


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.thanks_activity);

        FloatingActionButton btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThanksActivity.this, CartActivity.class));
            }
        });

        Button btnDongGop=findViewById(R.id.btnYKien);
        btnDongGop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER);
            }
        });
    }
    private void openDialog(int gravity){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_feedback);

        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes=window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER==gravity){
            dialog.setCancelable(false);
        }else {
            dialog.setCancelable(true);
        }

        EditText edtFeedBack=dialog.findViewById(R.id.edt_feedback);
        Button btnNo=dialog.findViewById(R.id.btnNo);
        Button btnSend=dialog.findViewById(R.id.btnSend);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung=edtFeedBack.getText().toString();

                ContentValues contentValues = new ContentValues();

                contentValues.put("noidung", noidung);

                SQLiteDatabase database = DBHelper.initDatabase(ThanksActivity.this, "android.db");
                database.insert("feedback", null, contentValues);
                Toast.makeText(ThanksActivity.this, "Đã gửi nhận xét", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
