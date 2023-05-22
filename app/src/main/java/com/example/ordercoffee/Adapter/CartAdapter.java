package com.example.ordercoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ordercoffee.Model.Cart;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> list;

    public CartAdapter(Context context, ArrayList<Cart> list) {
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
        View row=inflater.inflate(R.layout.cart_item,null);

        TextView id=(TextView) row.findViewById(R.id.txtId);
        TextView delivery=(TextView) row.findViewById(R.id.txtVanChuyen);
        TextView tax=(TextView) row.findViewById(R.id.txtTaxx);
        TextView total=(TextView) row.findViewById(R.id.txtTongTien);


        final Cart cart=list.get(position);

        id.setText("ID Cart: "+cart.getId_cart());
        delivery.setText("Vận chuyển: "+String.valueOf(cart.getDelivery()));
        tax.setText("Thuế: "+String.valueOf(cart.getTax()));
        total.setText("Tổng tiền: "+String.valueOf(cart.getTotal()));

        return  row;
    }

}
