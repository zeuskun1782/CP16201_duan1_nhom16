package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.KhachHang;
import com.example.duan1.Model.NhanVien;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }

    public long Insert(NhanVien nhanVien){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenNV",nhanVien.tenNV);
        contentValues.put("namSinh",nhanVien.namSinh);
        contentValues.put("gioiTinh",nhanVien.gioiTinh);
        contentValues.put("dienThoai",nhanVien.dienThoai);
        contentValues.put("diaChi",nhanVien.diaChi);
        return db.insert("NhanVien",null,contentValues);
    }
    public int Update(NhanVien nhanVien){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNV",nhanVien.maNV);
        contentValues.put("tenNV",nhanVien.tenNV);
        contentValues.put("namSinh",nhanVien.namSinh);
        contentValues.put("gioiTinh",nhanVien.gioiTinh);
        contentValues.put("dienThoai",nhanVien.dienThoai);
        contentValues.put("diaChi",nhanVien.diaChi);
        return db.update("NhanVien",contentValues,"maNV=?", new String[]{String.valueOf(nhanVien.maNV)});
    }
    public ArrayList<NhanVien> GetData(String sql, String...select){
        ArrayList<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,select);
        while (cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien();
            nhanVien.maNV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNV")));
            nhanVien.tenNV = cursor.getString(cursor.getColumnIndex("tenNV"));
            nhanVien.namSinh = Integer.parseInt(cursor.getString(cursor.getColumnIndex("namSinh")));
            nhanVien.gioiTinh = cursor.getString(cursor.getColumnIndex("gioiTinh"));
            nhanVien.dienThoai = cursor.getString(cursor.getColumnIndex("dienThoai"));
            nhanVien.diaChi = cursor.getString(cursor.getColumnIndex("diaChi"));
            list.add(nhanVien);
        }
        return list;
    }
    public ArrayList<NhanVien> GetAll(){
        String sql = "SELECT * FROM NhanVien";
        return GetData(sql);
    }
    public NhanVien GetID(String Id){
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<NhanVien> list = GetData(sql, Id);
        return list.get(0);
    }
    public int Delete(String id){
         return db.delete("NhanVien","maNV=?", new String[]{id});
    }
}
