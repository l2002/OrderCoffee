package com.example.ordercoffee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.R;

public class AdminActivity extends AppCompatActivity {
    LinearLayout btnHome,btnQuanLy,btnCart,btnFeedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        btnHome=findViewById(R.id.btnHome);
        btnQuanLy=findViewById(R.id.btnQuanLy);
        btnCart=findViewById(R.id.btnCart);
        btnCart=findViewById(R.id.btnCart);
        btnFeedBack=findViewById(R.id.btnFeedback);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });

        btnQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, DisplayDrinkActivity.class));
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, DisplayCartActivity.class));
            }
        });

        btnFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, FeedbackActivity.class));
            }
        });
    }
}
