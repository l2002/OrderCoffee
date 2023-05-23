package com.example.ordercoffee.Model;

public class Feedback {
    private int id;
    private String noidung;

    public int getId() {
        return id;
    }

    public String getNoidung() {
        return noidung;
    }

    public Feedback(int id, String noidung) {
        this.id = id;
        this.noidung = noidung;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
