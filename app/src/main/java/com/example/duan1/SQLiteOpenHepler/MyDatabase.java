package com.example.duan1.SQLiteOpenHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Quanlyphanphoixe.db";
    public static final int VERSION = 1;
    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String HangXe = "CREATE TABLE HangXe (maHang INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " tenHang TEXT NOT NULL)";
        db.execSQL(HangXe);
        String Xe = "CREATE TABLE Xe (maXe INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " tenXe TEXT NOT NULL, " +
                " giaXe INTEGER NOT NULL, " +
                " mauXe TEXT NOT NULL, " +
                " trangThai TEXT NOT NULL, " +
                " maHang INTEGER REFERENCES hang(maHang))";
        db.execSQL(Xe);
        String Khachhang ="CREATE TABLE KhachHang (maKH INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " tenKH TEXT NOT NULL, " +
                " namSinh INTEGER NOT NULL, " +
                " dienThoai TEXT NOT NULL, " +
                " diaChi TEXT NOT NULL)";
        db.execSQL(Khachhang);
        String Nhanvien ="CREATE TABLE NhanVien (maNV INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " tenNV TEXT NOT NULL, " +
                " namSinh INTEGER NOT NULL, " +
                " gioiTinh TEXT NOT NULL, " +
                " dienThoai TEXT NOT NULL, " +
                " diaChi TEXT NOT NULL)";
        db.execSQL(Nhanvien);
        String TaiKhoan ="CREATE TABLE TaiKhoan (maTK TEXT PRIMARY KEY, " +
                " hoTenTK TEXT NOT NULL, " +
                " dienThoai TEXT NOT NULL, " +
                " matKhau TEXT NOT NULL" +
                ")";
        db.execSQL(TaiKhoan);
        String ChiTietHoaDon ="CREATE TABLE ChitietHoaDon (maHDCT INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " maXe INTEGER REFERENCES Xe(maXe), " +
                " soLuong INTEGER NOT NULL, " +
                " donviTinh TEXT NOT NULL, " +
                " donGia INTEGER NOT NULL)";
        db.execSQL(ChiTietHoaDon);
        String HoaDon ="CREATE TABLE HoaDon (maHD INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " maHDCT INTEGER REFERENCES ChitietHoaDon(maHDCT), " +
                " maNV INTEGER REFERENCES NhanVien(maNV), " +
                " maKH INTEGER REFERENCES KhachhHang(maKH), " +
                " Ngay TEXT NOT NULL, " +
                " hinhthucTT TEXT NOT NULL, " +
                " phanLoai TEXT NOT NULL, " +
                " tongTien INTEGER NOT NULL)";
        db.execSQL(HoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
