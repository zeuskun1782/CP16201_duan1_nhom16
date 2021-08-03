package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.ChiTietHoaDon;
import com.example.duan1.Model.HangXe;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    private SQLiteDatabase db;

    public ChiTietHoaDonDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public long Insert(ChiTietHoaDon chiTietHoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maXe",chiTietHoaDon.maXe);
        contentValues.put("soLuong",chiTietHoaDon.soLuong);
        contentValues.put("donGia",chiTietHoaDon.donGia);
        contentValues.put("donviTinh",chiTietHoaDon.donviTinh);
        return db.insert("ChitietHoaDon",null,contentValues);
    }
    public int Update(ChiTietHoaDon chiTietHoaDon){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maXe",chiTietHoaDon.maXe);
        contentValues.put("soLuong",chiTietHoaDon.soLuong);
        contentValues.put("donGia",chiTietHoaDon.donGia);
        contentValues.put("donviTinh",chiTietHoaDon.donviTinh);
        return db.update("ChitietHoaDon",contentValues,"maHDCT=?",new String[]{String.valueOf(chiTietHoaDon.maHD)});
    }
    public ArrayList<ChiTietHoaDon> GetData(String sql, String...select){
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, select);
        while (cursor.moveToNext()){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.maHD = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHDCT")));
            chiTietHoaDon.maXe = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maXe")));
            chiTietHoaDon.soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));
            chiTietHoaDon.donviTinh = cursor.getString(cursor.getColumnIndex("donviTinh"));
            chiTietHoaDon.donGia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("donGia")));
            list.add(chiTietHoaDon);
        }
        return list;
    }
    public List<ChiTietHoaDon> GetAll(){
        String sql = "SELECT * FROM ChitietHoaDon";
        return GetData(sql);
    }
    public ChiTietHoaDon GetID(String Id){
        String sql = "SELECT * FROM ChitietHoaDon WHERE maHDCT=?";
        List<ChiTietHoaDon> list = GetData(sql, Id);
        return list.get(0);
    }
}
