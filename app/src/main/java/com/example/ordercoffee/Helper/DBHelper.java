package com.example.ordercoffee.Helper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DBHelper{
    public static SQLiteDatabase initDatabase(Activity activity,String databaseName){
        try{
            String outFileName=activity.getApplicationInfo().dataDir+"/databases/"+databaseName;
            File f=new File(outFileName);
            if(!f.exists()){
                InputStream e=activity.getAssets().open(databaseName);
                File folder=new File(activity.getApplicationInfo().dataDir+"/databases/");
                if(!folder.exists()){
                    folder.mkdir();
                }
                FileOutputStream myOut=new FileOutputStream(outFileName);
                byte[] buffer=new byte[1024];

                int length;
                while((length = e.read(buffer)) >0){
                    myOut.write(buffer,0,length);
                }
                myOut.flush();
                myOut.close();
                e.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  activity.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
    }
}
