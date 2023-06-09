package com.example.ordercoffee.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    Context context;
    ArrayList<Drink> list;
    private ImageView image;

    public PhoBienAdapter(Context context,ArrayList<Drink> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public PhoBienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoBienAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.phobien_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoBienAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Drink drink = list.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(drink.getAnh(), 0, drink.getAnh().length);

        holder.tieude.setText(list.get(position).getTieuDe());
        holder.pic.setImageBitmap(bitmap);
        holder.gia.setText(String.valueOf(drink.getFee()));

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", list.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tieude,gia;
        public ImageView pic;
        public TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=(ImageView) itemView.findViewById(R.id.pic);
            tieude=(TextView) itemView.findViewById(R.id.title);
            gia=(TextView) itemView.findViewById(R.id.fee);
            addBtn=(TextView) itemView.findViewById(R.id.addBtn);
        }
    }
}
