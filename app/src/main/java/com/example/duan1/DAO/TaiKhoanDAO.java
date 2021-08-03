package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.SQLiteOpenHepler.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private SQLiteDatabase db;

    public TaiKhoanDAO(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public long Insert(TaiKhoan taiKhoan){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTK",taiKhoan.maTK);
        contentValues.put("hoTenTK",taiKhoan.hoTenTK);
        contentValues.put("dienThoai",taiKhoan.dienThoai);
        contentValues.put("matKhau",taiKhoan.matKhau);
        return db.insert("TaiKhoan",null,contentValues);
    }
    public int UpdateMK(TaiKhoan taiKhoan){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTK",taiKhoan.maTK);
        contentValues.put("hoTenTK",taiKhoan.hoTenTK);
        contentValues.put("dienThoai",taiKhoan.dienThoai);
        contentValues.put("matKhau",taiKhoan.matKhau);
        return  db.update("TaiKhoan",contentValues,"maTK=?", new String[]{taiKhoan.maTK});
    }
    public List<TaiKhoan> GetData(String sql, String...selection){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selection);
        while (cursor.moveToNext()){
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.maTK = cursor.getString(cursor.getColumnIndex("maTK"));
            taiKhoan.hoTenTK = cursor.getString(cursor.getColumnIndex("hoTenTK"));
            taiKhoan.dienThoai = cursor.getString(cursor.getColumnIndex("dienThoai"));
            taiKhoan.matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(taiKhoan);
        }
        return list;
    }
    public TaiKhoan GetID(String id){
        String sql = "SELECT * FROM TaiKhoan WHERE maTK=? ";
        List<TaiKhoan> list = GetData(sql,id);
        return list.get(0);
    }
    public List<TaiKhoan> GetAll(){
        String sql = "SELECT * FROM TaiKhoan";
        return  GetData(sql);
    }
    public int checklogin(String id, String password) {
        String sql = "SELECT * FROM  TaiKhoan WHERE maTK=? AND matKhau=?";
        List<TaiKhoan> list = GetData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        } else {
            return 1;
        }
    }
    public int Delete(String Id){
        return db.delete("TaiKhoan","maTK=?", new String[]{Id});
    }
}
