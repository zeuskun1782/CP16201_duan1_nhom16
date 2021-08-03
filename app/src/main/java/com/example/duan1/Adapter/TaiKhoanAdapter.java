package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1.Fragment.FragmentTaoTaiKhoan;
import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.R;

import java.util.ArrayList;

public class TaiKhoanAdapter  extends ArrayAdapter<TaiKhoan> {
    private Context context;
     ArrayList<TaiKhoan> list;
     FragmentTaoTaiKhoan fragment;
     TextView tv_tenDN, tv_hoTen, tv_dienThoai, tv_matKhau;
     ImageView img_Xoa;

    public TaiKhoanAdapter(@NonNull Context context, FragmentTaoTaiKhoan fragment,  ArrayList<TaiKhoan> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tai_khoan,null);
        }
        final TaiKhoan item = list.get(position);
        if (item != null){
            tv_tenDN = view.findViewById(R.id.item_tenDN);
            tv_hoTen = view.findViewById(R.id.item_hoTen);
            tv_dienThoai = view.findViewById(R.id.item_dienthoai);
            tv_matKhau = view.findViewById(R.id.item_MK);
            img_Xoa = view.findViewById(R.id.item_imgxoa);
            tv_tenDN.setText("Tên đăng nhập :"+ item.maTK);
            tv_hoTen.setText("Họ và tên :"+ item.hoTenTK);
            tv_dienThoai.setText("Điện thoại :"+ item.dienThoai);
            tv_matKhau.setText(item.matKhau);
            img_Xoa.setOnClickListener(v -> {
                fragment.Xoa(item.maTK);
            });
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tai_khoan,null);
        }
        final TaiKhoan item = list.get(position);
        if (item != null){
            tv_tenDN = view.findViewById(R.id.item_tenDN);
            tv_hoTen = view.findViewById(R.id.item_hoTen);
            tv_dienThoai = view.findViewById(R.id.item_dienthoai);
            tv_matKhau = view.findViewById(R.id.item_MK);
            img_Xoa = view.findViewById(R.id.item_imgxoa);
            tv_tenDN.setText("Tên đăng nhập :"+ item.maTK);
            tv_hoTen.setText("Họ và tên :"+ item.hoTenTK);
            tv_dienThoai.setText("Điện thoại :"+ item.dienThoai);
            tv_matKhau.setText(item.matKhau);
            img_Xoa.setOnClickListener(v -> {
                fragment.Xoa(item.maTK);
            });
        }
        return view;
    }
}
