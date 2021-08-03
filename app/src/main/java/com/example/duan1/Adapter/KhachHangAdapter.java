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

import com.example.duan1.DAO.HangXeDAO;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.Fragment.FragmentKhachHang;
import com.example.duan1.Model.HangXe;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.Model.Xe;
import com.example.duan1.R;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> list;
    FragmentKhachHang fragment;
    static  KhachHangDAO dao;
    TextView tv_maKH, tv_tenKH, tv_namSinh, tv_dienThoai, tv_diaChi;
    ImageView imageView_Xoa, btn_Sua;

    public KhachHangAdapter(@NonNull Context context, FragmentKhachHang fragment,ArrayList<KhachHang> list) {
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
            view = inflater.inflate(R.layout.item_khachhang,null);
        }
        final KhachHang item = list.get(position);
        if (item != null){
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            KhachHang khachHang = khachHangDAO.GetID(String.valueOf(item.maKH));
            tv_maKH = view.findViewById(R.id.tv_maKH);
            tv_maKH.setText("Mã Khách hàng :" +item.maKH);
            tv_tenKH = view.findViewById(R.id.tv_tenKH);
            tv_tenKH.setText("Tên Khách hàng :" +item.tenKH);
            tv_namSinh = view.findViewById(R.id.tv_namSinh);
            tv_namSinh.setText("Năm sinh :" +item.namSinh);
            tv_dienThoai = view.findViewById(R.id.tv_dienThoai);
            tv_dienThoai.setText("Điện thoai :" +item.dienThoai);
            tv_diaChi= view.findViewById(R.id._tv_diaChi);
            tv_diaChi.setText("Địa chỉ: "+ item.diaChi);
            imageView_Xoa = view.findViewById(R.id.img_xoaKH);
            dao = new KhachHangDAO(getContext());
            imageView_Xoa.setOnClickListener(v -> {
                fragment.Xoa(String.valueOf(item.maKH));
            });
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_khachhang,null);
        }
        final KhachHang item = list.get(position);
        if (item != null){
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            KhachHang Khachhang = khachHangDAO.GetID(String.valueOf(item.maKH));
            tv_maKH = view.findViewById(R.id.tv_maKH);
            tv_maKH.setText("Mã Khách hàng:"+item.maKH);
            tv_tenKH = view.findViewById(R.id.tv_tenKH);
            tv_tenKH.setText("Tên Khách hàng:"+item.tenKH);
            tv_namSinh = view.findViewById(R.id.tv_namSinh);
            tv_namSinh.setText("Năm sinh:"+item.namSinh);
            tv_dienThoai = view.findViewById(R.id.tv_dienThoai);
            tv_dienThoai.setText("Điện thoai:"+item.dienThoai);
            tv_diaChi= view.findViewById(R.id._tv_diaChi);
            tv_diaChi.setText("Địa chỉ:"+item.diaChi);
            imageView_Xoa = view.findViewById(R.id.img_xoaKH);
            imageView_Xoa.setOnClickListener(v -> {
            });
        }
        return view;
    }
}
