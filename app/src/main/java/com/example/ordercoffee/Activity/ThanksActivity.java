package com.example.ordercoffee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
                startActivity(new Intent(ThanksActivity.this, MainActivity.class));
            }
        });
    }
}
