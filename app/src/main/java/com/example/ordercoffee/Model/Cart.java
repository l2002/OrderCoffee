package com.example.ordercoffee.Model;

public class Cart {
    private int id;
    private double totalFee;
    private double delivery;
    private double tax;
    private double total;

    public Cart(int id, double totalFee, double delivery, double tax, double total) {
        this.id = id;
        this.totalFee = totalFee;
        this.delivery = delivery;
        this.tax = tax;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getDelivery() {
        return delivery;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
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
}
