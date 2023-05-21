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

    private EditText edtusername,edtpassword;
    List<User> mListUser;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        edtusername = findViewById(R.id.username);
        edtpassword = findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        mListUser = new ArrayList<>();
        getListUsers();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickLogin();
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtusername.getText().toString().equals("") || edtpassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Không được để trống tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtusername.getText().toString().equals("admin") && edtpassword.getText().toString().equals("admin")) {
                    //correct
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    Toast.makeText(LoginActivity.this, "CHÀO MỪNG ADMIN", Toast.LENGTH_SHORT).show();

                } else {
                    //incorrect
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void clickLogin() {
        String username=edtusername.getText().toString().trim();
        String password=edtpassword.getText().toString().trim();

        if(mListUser==null||mListUser.isEmpty()){
            Toast.makeText(LoginActivity.this, "Username and password Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isHasUser=false;
        for(User user: mListUser){
            if(username.equals(user.getUsername())&password.equals(user.getPassword())){
                isHasUser=true;
                mUser=user;
                break;
            }
        }
        if(isHasUser){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("Object_user",mUser);
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Toast.makeText(LoginActivity.this, "Username or password invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void getListUsers() {

            ApiService.API_SERVICE.callApi("json-server","password")
                    .enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            mListUser=response.body();
                            Log.e("List user",mListUser.size()+"");
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "err", Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}