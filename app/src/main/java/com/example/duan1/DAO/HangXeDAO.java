package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.HangXe;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class HangXeDAO {
    private SQLiteDatabase db;

    public HangXeDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public long Insert(HangXe hangXe){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenHang",hangXe.tenHang);
        return db.insert("HangXe",null,contentValues);
    }
    public int Update(HangXe hangXe){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenHang",hangXe.tenHang);
        return db.update("HangXe",contentValues,"maHang",null);
    }
    public int Delete(String Id){
        return db.delete("HangXe","maHang=?",new String[]{Id});
    }
    private List<HangXe> GetData(String sql, String...selection){
        ArrayList<HangXe> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selection);
        while (cursor.moveToNext()){
            HangXe hangXe = new HangXe();
            hangXe.maHang = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHang")));
            hangXe.tenHang = cursor.getString(cursor.getColumnIndex("tenHang"));
            list.add(hangXe);
        }
        return list;
    }
    public List<HangXe> GetAll(){
        String sql = "SELECT * FROM HangXe";
        return GetData(sql);
    }
    public HangXe GetID(String Id){
        String sql = "SELECT * FROM HangXe WHERE maHang=?";
        List<HangXe> list = GetData(sql, Id);
        return list.get(0);
    }
}
