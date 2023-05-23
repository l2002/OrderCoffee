package com.example.ordercoffee.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordercoffee.API.KeyValuePair;
import com.example.ordercoffee.R;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    MaterialButton btnLogin;

//    @Override//   protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//
//        edtUsername = (EditText) findViewById(R.id.username);
//        edtPassword = (EditText) findViewById(R.id.password);
//        btnLogin = (MaterialButton) findViewById(R.id.loginbtn);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
//                    Toast.makeText(LoginActivity.this, "Không được để trống tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
//                    return;
//                } else if (edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin")) {
//                    //correct
//                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
//                    Toast.makeText(LoginActivity.this, "CHÀO MỪNG ADMIN", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    //incorrect
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    Toast.makeText(LoginActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
    MaterialButton bt_login;
    EditText et_username, et_password;
    Gson gson;
    SharedPreferences sharedPreferences;
    String URL = "https://jsonblob.com/api/1110452290778841088";

    public static final String Sp_Status = "Status";
    public static final String MyPref = "MyPref";
    static int mStatusCode = 0;
    public String username, password;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        bt_login = (MaterialButton) findViewById(R.id.loginbtn);
        OnClick();
        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
//        if (sharedPreferences.getString(LoginActivity.Sp_Status,"").matches("LoggedIn")){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        }
    }

    private void OnClick() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();

                if(username.equals("admin")&&password.equals("admin")){
                    Toast.makeText(LoginActivity.this, "Chào mừng Admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                }
                if (username.length() >= 1) {
                    if (password.length() >= 1) {
                        loginapi();
                    } else {
                        et_password.setError("Hãy nhập password");
                    }
                } else {
                    et_username.setError("Hãy nhập username");
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Nhấn Quay lại lần nữa để Thoát.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    private void loginapi() {
        ArrayList<KeyValuePair> params = new ArrayList<KeyValuePair>();
        params.add(new KeyValuePair("username", username));
        params.add(new KeyValuePair("password", password));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String username1= et_username.getText().toString().trim();
                        String password1 = et_password.getText().toString().trim();
                        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        gson = new Gson();
                        switch (mStatusCode) {
                            case 200:
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    gson.fromJson(jsonObject.toString(), KeyValuePair.class);
                                    if(username1==jsonObject.getJSONObject("username").toString().trim()&&password1==jsonObject.getJSONObject("password").toString().trim()){
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        editor.putString(Sp_Status, "LoggedIn");
                                        editor.commit();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }else {
                                        Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                }
                , new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(LoginActivity.this, "Server Down", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        requestQueue.add(stringRequest);
    }
}