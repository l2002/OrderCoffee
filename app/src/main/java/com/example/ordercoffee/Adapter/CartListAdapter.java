package com.example.ordercoffee.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordercoffee.Activity.ShowDetailActivity;
import com.example.ordercoffee.Helper.ChangeNumberItemListenner;
import com.example.ordercoffee.Helper.ManagementCart;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{
    ArrayList<Drink> listDrinkSelected;
    private ManagementCart managementCart;
    ChangeNumberItemListenner changeNumberItemListenner;

    public CartListAdapter(ArrayList<Drink> listDrinkSelected,Context context,ChangeNumberItemListenner changeNumberItemListenner) {
        this.listDrinkSelected = listDrinkSelected;
        managementCart=new ManagementCart(context);
        this.changeNumberItemListenner=changeNumberItemListenner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inView=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Drink drink=listDrinkSelected.get(position);
        Bitmap bitmap= BitmapFactory.decodeByteArray(drink.getAnh(),0,drink.getAnh().length);

        holder.tieude.setText(listDrinkSelected.get(position).getTieuDe());
        holder.pic.setImageBitmap(bitmap);
        holder.feeEachItem.setText(String.valueOf(drink.getFee())+" "+"VND");
        holder.totalEachItem.setText(Math.round(listDrinkSelected.get(position).getNumberInCart()*listDrinkSelected.get(position).getFee())+" "+"VND");
        holder.num.setText(String.valueOf(listDrinkSelected.get(position).getNumberInCart()));

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberDrink(listDrinkSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListenner.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberrink(listDrinkSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListenner.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return listDrinkSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tieude,feeEachItem;
        public ImageView pic;
        public TextView totalEachItem,num,plusItem,minusItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.pic2);
            tieude=itemView.findViewById(R.id.txtTieude2);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem= itemView.findViewById(R.id.totalEachItem);
            plusItem=itemView.findViewById(R.id.btnPlus);
            minusItem=itemView.findViewById(R.id.btnMinus);
            num=itemView.findViewById(R.id.txtNumOrder);
        }
    }
}
