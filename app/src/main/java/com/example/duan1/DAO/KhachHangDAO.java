package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.KhachHang;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public long Insert(KhachHang khachHang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKH",khachHang.tenKH);
        contentValues.put("namSinh",khachHang.namSinh);
        contentValues.put("dienThoai",khachHang.dienThoai);
        contentValues.put("diaChi",khachHang.diaChi);
        return db.insert("KhachHang",null,contentValues);
    }
    public int Update(KhachHang khachHang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKH",khachHang.tenKH);
        contentValues.put("namSinh",khachHang.namSinh);
        contentValues.put("dienThoai",khachHang.dienThoai);
        contentValues.put("diaChi",khachHang.diaChi);
        return db.update("KhachHang",contentValues,"maKH=?", new String[]{String.valueOf(khachHang.maKH)});
    }
    private ArrayList<KhachHang> GetData(String sql, String...selection){
        ArrayList<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selection);
        while (cursor.moveToNext()){
            KhachHang khachHang = new KhachHang();
            khachHang.maKH = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH")));
            khachHang.tenKH = cursor.getString(cursor.getColumnIndex("tenKH"));
            khachHang.namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
            khachHang.dienThoai = cursor.getString(cursor.getColumnIndex("dienThoai"));
            khachHang.diaChi = cursor.getString(cursor.getColumnIndex("diaChi"));
            list.add(khachHang);
        }
        return list;
    }
    public List<KhachHang> GetAll(){
        String sql = "SELECT * FROM KhachHang";
        return GetData(sql);
    }
    public KhachHang GetID(String Id){
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = GetData(sql, Id);
        return list.get(0);
    }
    public int Delete(String Id){
        return db.delete("KhachHang","maKH=?", new String[]{Id});
    }
}
