package com.example.ordercoffee.Adapter;

import android.annotation.SuppressLint;
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

import com.example.ordercoffee.Activity.MainActivity;
import com.example.ordercoffee.Activity.ShowDetailActivity;
import com.example.ordercoffee.Model.Drink;
import com.example.ordercoffee.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.DrinkViewHolder> {

    private List<Drink> lstDrink;

    public SearchAdapter(MainActivity mainActivity, List<Drink> lstDrink) {
        this.lstDrink = lstDrink;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Drink drink = lstDrink.get(position);
        if (drink == null)
            return;
        Bitmap bitmap = BitmapFactory.decodeByteArray(drink.getAnh(), 0, drink.getAnh().length);
        holder.imgDrink.setImageBitmap(bitmap);
        holder.txtDrink.setText(drink.getTieuDe());



    }

    @Override
    public int getItemCount() {
        if (lstDrink != null)
            return lstDrink.size();
        return 0;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDrink;
        private TextView txtDrink;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = itemView.findViewById(R.id.search_image);
            txtDrink = itemView.findViewById(R.id.search_ten);

        }
    }


}