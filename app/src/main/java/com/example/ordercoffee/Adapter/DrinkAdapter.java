package com.example.ordercoffee.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordercoffee.Activity.AddDrinkActivity;
import com.example.ordercoffee.Activity.UpdateDrinkActivity;
import com.example.ordercoffee.Helper.DBHelper;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {

    Context context;
    ArrayList<Drink> list;

    public DrinkAdapter(Context context, ArrayList<Drink> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.drink_crud_item,null);

        ImageView image=(ImageView) row.findViewById(R.id.anh);
        TextView id=(TextView) row.findViewById(R.id.txtId);
        TextView tieude=(TextView) row.findViewById(R.id.txtTieuDe);
        TextView mota=(TextView) row.findViewById(R.id.txtMota);
        TextView gia=(TextView) row.findViewById(R.id.txtFee);

        Button sua=(Button) row.findViewById(R.id.btnSua);
        Button xoa=(Button) row.findViewById(R.id.btnXoa);

        final Drink drink=list.get(position);

        id.setText("ID: "+drink.getId());
        tieude.setText("Tên SP: "+drink.getTieuDe());
        mota.setText("Mô tả: "+drink.getMoTa());
        gia.setText("Giá: "+String.valueOf(drink.getFee()));
        Bitmap bitmap= BitmapFactory.decodeByteArray(drink.getAnh(),0,drink.getAnh().length);
        image.setImageBitmap(bitmap);

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateDrinkActivity.class);
                intent.putExtra("id",drink.getId());
                context.startActivity(intent);
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắn chắn muốn xóa thức uống này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(drink.getId());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        return  row;
    }

    private void delete(int idDrink){
        SQLiteDatabase database= DBHelper.initDatabase((Activity) context,"android.db");
        database.delete("drink","id=?",new String[]{idDrink+""});
        list.clear();
        Cursor cursor=database.rawQuery("select * from drink",null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String tieude=cursor.getString(1);
            byte[]anh=cursor.getBlob(2);
            String mota=cursor.getString(3);
            Double gia=cursor.getDouble(4);

            list.add(new Drink(id,tieude,anh,mota,gia));
        }
        notifyDataSetChanged();
    }
}

