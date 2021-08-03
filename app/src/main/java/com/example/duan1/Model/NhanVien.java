package com.example.duan1.Model;

public class NhanVien {
    public int maNV;
    public String tenNV;
    public int namSinh;
    public String gioiTinh;
    public String dienThoai;
    public String diaChi;

    public NhanVien() {
    }

    public NhanVien(int maNV, String tenNV, int namSinh, String gioiTinh, String dienThoai, String diaChi) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }
}
