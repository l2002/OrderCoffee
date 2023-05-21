package com.example.ordercoffee.Model;

public class Cart {
    private int id_cart;
    private double delivery;
    private double tax;
    private double total;

    public int getId_cart() {
        return id_cart;
    }

    public double getDelivery() {
        return delivery;
    }

    public double getTax() {
        return tax;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public Cart(int id_cart, double delivery, double tax, double total) {
        this.id_cart = id_cart;
        this.delivery = delivery;
        this.tax = tax;
        this.total = total;
    }
}
