package com.example.ordercoffee.Model;

import java.io.Serializable;

public class Drink implements Serializable {
    private int id;
    private String tieuDe;
    private byte[] anh;
    private String moTa;
    private  double fee;
    private int numberInCart;

    public Drink(int id, String tieuDe, byte[] anh, String moTa, double fee) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.anh = anh;
        this.moTa = moTa;
        this.fee = fee;
    }


    public int getId() {
        return id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getMoTa() {
        return moTa;
    }

    public double getFee() {
        return fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
