package com.example.ordercoffee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordercoffee.API.ApiService;
import com.example.ordercoffee.Model.User;
import com.example.ordercoffee.R;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    List<User> mListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


         username = findViewById(R.id.username);
         password = findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        mListUser=new ArrayList<>();
        getListUsers();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")||password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"Không được để trống tên đăng nhập và mật khẩu",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    Toast.makeText(LoginActivity.this,"CHÀO MỪNG ADMIN",Toast.LENGTH_SHORT).show();

                }else {
                    //incorrect
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void getListUsers() {
        ApiService.API_SERVICE.callApi()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                      mListUser=response.body();
                        Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "err", Toast.LENGTH_SHORT).show();
                    }
                });
        }
}