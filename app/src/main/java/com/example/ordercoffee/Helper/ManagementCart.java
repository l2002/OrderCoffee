package com.example.ordercoffee.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.ordercoffee.Model.Drink;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public ArrayList<Drink> getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public void insertDrink(Drink item){
        ArrayList<Drink> listDrink=getListCart();
        boolean existAlready=false;
        int n=0;
        for(int i=0;i<listDrink.size();i++){
            if(listDrink.get(i).getTieuDe().equals(item.getTieuDe())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if (existAlready){
            listDrink.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listDrink.add(item);
        }
        tinyDB.putListObject("CartList",listDrink);

        Toast.makeText(context, "Đã thêm vào giỏ hàng của bạn", Toast.LENGTH_SHORT).show();
    }

    public void minusNumberrink(ArrayList<Drink> listDrink,int position,ChangeNumberItemListenner changeNumberItemListenner){
        if (listDrink.get(position).getNumberInCart()==1){
            listDrink.remove(position);
        }else {
            listDrink.get(position).setNumberInCart(listDrink.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listDrink);
        changeNumberItemListenner.changed();
    }

    public void plusNumberDrink(ArrayList<Drink> listDrink,int position,ChangeNumberItemListenner changeNumberItemListenner){
        listDrink.get(position).setNumberInCart(listDrink.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listDrink);
        changeNumberItemListenner.changed();
    }

    public Double getTotalFee(){
        ArrayList<Drink>list=getListCart();
        double fee=0;
        for(int i=0;i<list.size();i++){
            fee=fee+(list.get(i).getFee()+list.get(i).getNumberInCart());
        }
        return fee;
    }
}
