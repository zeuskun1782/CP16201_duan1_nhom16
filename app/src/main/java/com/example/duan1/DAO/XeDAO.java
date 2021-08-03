package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.ChiTietHoaDon;
import com.example.duan1.Model.Xe;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class XeDAO {
    private SQLiteDatabase db;

    public XeDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public long Insert(Xe xe){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenXe",xe.tenXe);
        contentValues.put("giaXe",xe.giaXe);
        contentValues.put("mauXe",xe.mauXe);
        contentValues.put("trangThai",xe.trangThai);
        contentValues.put("maHang",xe.maHang);
        return db.insert("Xe",null,contentValues);
    }
    public int Update(Xe xe){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maXe",xe.maXe);
        contentValues.put("tenXe",xe.tenXe);
        contentValues.put("giaXe",xe.giaXe);
        contentValues.put("mauXe",xe.mauXe);
        contentValues.put("trangThai",xe.trangThai);
        contentValues.put("maHang",xe.maHang);
        return db.update("Xe",contentValues,"maXe=?", new String[]{String.valueOf(xe.maXe)});
    }
    private ArrayList<Xe> GetData(String sql, String...selection){
        ArrayList<Xe> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selection);
        while (cursor.moveToNext()){
            Xe xe = new Xe();
            xe.maXe = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maXe")));
            xe.tenXe = cursor.getString(cursor.getColumnIndex("tenXe"));
            xe.giaXe = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaXe")));
            xe.mauXe = cursor.getString(cursor.getColumnIndex("mauXe"));
            xe.trangThai = cursor.getString(cursor.getColumnIndex("trangThai"));
            xe.maHang = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHang")));
            list.add(xe);
        }
        return list;
    }
    public List<Xe> GetAll(){
        String sql = "SELECT * FROM Xe";
        return GetData(sql);
    }
    public Xe GetID(String ID){
        String sql = "SELECT * FROM XE WHERE maXe=?";
        List<Xe> list = GetData(sql, ID);
        return list.get(0);
    }
    public int Delete(String id){
        return db.delete("Xe","maXe=?", new String[]{id});
    }
}
