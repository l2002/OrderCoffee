package com.example.ordercoffee.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ordercoffee.Activity.ShowDetailActivity;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class PhoBienAdapter extends RecyclerView.Adapter<PhoBienAdapter.ViewHolder> {

    ArrayList<Drink>popularDrink;

    public PhoBienAdapter(ArrayList<Drink> popularDrink) {
        this.popularDrink = popularDrink;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.phobien_item,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoBienAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.title.setText(popularDrink.get(position).getTieuDe());
        holder.fee.setText(String.valueOf(popularDrink.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(String.valueOf(popularDrink.get(position).getAnh()),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount(){
        return popularDrink.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addBtn;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);
        }

    }

}
