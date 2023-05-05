package com.example.ordercoffee.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordercoffee.Model.DanhMuc;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.ViewHolder> {

    UpdateDanhMucRec updateDanhMucRec;
    Activity activity;
    ArrayList<DanhMuc> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public DanhMucAdapter(UpdateDanhMucRec updateDanhMucRec, Activity activity, ArrayList<DanhMuc> list) {
        this.updateDanhMucRec = updateDanhMucRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public DanhMucAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.danhmuc_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getHinhAnh());
        holder.name.setText(list.get(position).getTieuDe());

        if(check) {
            ArrayList<Drink> foodDomains = new ArrayList<>();


            updateDanhMucRec.callBack(position, foodDomains);
            check = false;
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0)
                {
                    ArrayList<Drink> drinks = new ArrayList<>();
                    updateDanhMucRec.callBack(position, drinks);
                }
                else if(position == 1)
                {
                    ArrayList<Drink> drinks = new ArrayList<>();
                    updateDanhMucRec.callBack(position, drinks);
                }
                else if(position == 2)
                {

                }

            }
        });

        if (select){
            if(position == 0){
                holder.constraintLayout.setBackgroundResource(R.drawable.change_bg);
                holder.name.setTextColor(Color.WHITE);
                select = false;
            }
        }else{
            if(row_index == position){
                holder.constraintLayout.setBackgroundResource(R.drawable.change_bg);
                holder.name.setTextColor(Color.WHITE);
            }else{
                holder.constraintLayout.setBackgroundResource(R.drawable.default_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.picDanhMuc);
            name = itemView.findViewById(R.id.nameDanhMuc);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
