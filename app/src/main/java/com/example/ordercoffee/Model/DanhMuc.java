package com.example.ordercoffee.Model;

public class DanhMuc {
    private String tieuDe;
    private int hinhAnh;

    public DanhMuc(String tieuDe, int hinhAnh) {
        this.tieuDe = tieuDe;
        this.hinhAnh = hinhAnh;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
