package com.example.ordercoffee.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class UpdateDrinkActivity extends AppCompatActivity {
    final String DATABASE_NAME="android.db";
    Button btnChonAnh,btnChupAnh,btnLuu,btnHuy;
    EditText edtId,edtTieude,edtMota,edtGia;
    ImageView imageView;

    final int REQUEST_TAKE_PHOTO=123;
    final int REQUEST_CHOOSE_PHOTO=321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatedrink_activity);

        addControls();
        initUI();
        addEvents();
    }

    private void initUI() {
        Intent intent=getIntent();
        String id= String.valueOf(intent.getIntExtra("id_drink",-1));
        SQLiteDatabase database= DBHelper.initDatabase(this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("select * from drink where id_drink=?",new String[]{id+"",});
        cursor.moveToFirst();

        String tieude=cursor.getString(1);
        byte[]anh=cursor.getBlob(2);
        String mota=cursor.getString(3);
        String gia= String.valueOf(cursor.getDouble(4));

        Bitmap bitmap= BitmapFactory.decodeByteArray(anh,0,anh.length);
        imageView.setImageBitmap(bitmap);

        edtId.setText(id);
        edtTieude.setText(tieude);
        edtMota.setText(mota);
        edtGia.setText(gia);
    }

    public void update(){
        String id=edtId.getText().toString();
        String tieude=edtTieude.getText().toString();
        byte[]anh=getByteArrayImageView(imageView);
        String mota=edtMota.getText().toString();
        String gia=edtGia.getText().toString();

        ContentValues contentValues=new ContentValues();
        contentValues.put("id_drink",id);
        contentValues.put("tieude",tieude);
        contentValues.put("anh",anh);
        contentValues.put("mota",mota);
        contentValues.put("gia",gia);

        SQLiteDatabase database= DBHelper.initDatabase(this,"android.db");
        database.update("drink",contentValues,"id_drink=?",new String[]{id+""});
        Intent intent=new Intent(this,DisplayDrinkActivity.class);
        startActivity(intent);
    }

    private  void addEvents(){
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
            }
        });
        btnChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
    }
    public void cancle(){
        Intent intent=new Intent(this,DisplayDrinkActivity.class);
        startActivity(intent);
    }
    private void addControls() {
        btnChupAnh=(Button) findViewById(R.id.btnChupAnh);
        btnChonAnh=(Button) findViewById(R.id.btnChonAnh);
        btnLuu=(Button) findViewById(R.id.btnLuu);
        btnHuy=(Button) findViewById(R.id.btnHuy);

        edtId=(EditText) findViewById(R.id.id);
        edtTieude=(EditText) findViewById(R.id.title);
        imageView=(ImageView) findViewById(R.id.imageAnh);
        edtMota=(EditText) findViewById(R.id.mota);
        edtGia=(EditText) findViewById(R.id.gia);
    }

    private void takePic(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    private void choosePic(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CHOOSE_PHOTO) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(requestCode==REQUEST_TAKE_PHOTO){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    private byte[] getByteArrayImageView(ImageView imageView) {
        BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]bytes=stream.toByteArray();
        return bytes;
    }
}