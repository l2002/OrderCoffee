package com.example.ordercoffee.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordercoffee.Adapter.CartListAdapter;
import com.example.ordercoffee.Helper.ChangeNumberItemListenner;
import com.example.ordercoffee.Helper.ManagementCart;
import com.example.ordercoffee.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    private TextView txtTotalFee;
    private TextView txtTax;
    private TextView txtDelivery;
    private TextView txtTotal;
    private TextView txtEmpty;
    private double tax;
    private ScrollView scrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_acticity);

        managementCart=new ManagementCart(this);
        initView();
        initList();
        calculateCart();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListenner() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            txtEmpty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            txtEmpty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double thue=0.02;
        double vanChuyen=10000.0;


        tax=Math.round((managementCart.getTotalFee()*thue)*100.0)/100.0;
        double total=Math.round((managementCart.getTotalFee()+tax+vanChuyen)*100.0)/100.0;
        double itemTotal=Math.round(managementCart.getTotalFee()*100.0)/100.0;

        txtTotalFee.setText(itemTotal+" "+"VND");
        txtTax.setText(tax+" "+"VND");
        txtDelivery.setText(vanChuyen+" "+"VND");
        txtTotal.setText(total+" "+"VND");
    }

    private void initView() {
        txtTotalFee=findViewById(R.id.txtTotalFee);
        txtDelivery=findViewById(R.id.txtDelivery);
        txtTax=findViewById(R.id.txtTax);
        txtTotal=findViewById(R.id.txtTotal);
        recyclerView=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        txtEmpty=findViewById(R.id.txtEmpty);
    }
}
