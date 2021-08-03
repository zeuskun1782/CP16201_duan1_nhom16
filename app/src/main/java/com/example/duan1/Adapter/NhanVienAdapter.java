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

import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Fragment.FragmentNhanVien;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.Model.NhanVien;
import com.example.duan1.R;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    private Context context;
    FragmentNhanVien fragment;
    NhanVienDAO dao;
    ArrayList<NhanVien> list;
    ImageView imageView_Xoa;
    TextView tv_maNV, tv_tenNV, tv_namSinh, tv_gioiTinh, tv_dienThoai, tv_diaChi;
    public NhanVienAdapter(@NonNull Context context, FragmentNhanVien fragment,ArrayList<NhanVien> list) {
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
            view = inflater.inflate(R.layout.item_nhan_vien,null);
        }
        final NhanVien item = list.get(position);
        if (item != null){
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            NhanVien nhanVien = nhanVienDAO.GetID(String.valueOf(item.maNV));
            tv_maNV = view.findViewById(R.id.tv_maNV);
            tv_maNV.setText("Mã Nhân Viên :" +item.maNV);
            tv_tenNV = view.findViewById(R.id.tv_tenNV);
            tv_tenNV.setText("Tên Nhân viên :" +item.tenNV);
            tv_namSinh = view.findViewById(R.id.tv_namsinhNV);
            tv_namSinh.setText("Năm sinh :" +item.namSinh);
            tv_gioiTinh = view.findViewById(R.id.tv_gioiTinh);
            tv_gioiTinh.setText("Giới Tính :"+item.gioiTinh);
            tv_dienThoai = view.findViewById(R.id.tv_dienThoaiNV);
            tv_dienThoai.setText("Điện thoai :" +item.dienThoai);
            tv_diaChi= view.findViewById(R.id._tv_diaChiNV);
            tv_diaChi.setText("Địa chỉ: "+ item.diaChi);
            imageView_Xoa = view.findViewById(R.id.image_xoaNV);
            dao = new NhanVienDAO(getContext());
            imageView_Xoa.setOnClickListener(v -> {
                fragment.Xoa(String.valueOf(item.maNV));
            });
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_nhan_vien,null);
        }
        final NhanVien item = list.get(position);
        if (item != null){
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            NhanVien nhanVien = nhanVienDAO.GetID(String.valueOf(item.maNV));
            tv_maNV = view.findViewById(R.id.tv_maNV);
            tv_maNV.setText("Mã Nhân Viên :" +item.maNV);
            tv_tenNV = view.findViewById(R.id.tv_tenNV);
            tv_tenNV.setText("Tên Nhân viên :" +item.tenNV);
            tv_namSinh = view.findViewById(R.id.tv_namsinhNV);
            tv_namSinh.setText("Năm sinh :" +item.namSinh);
            tv_gioiTinh = view.findViewById(R.id.tv_gioiTinh);
            tv_gioiTinh.setText("Giới Tính :"+item.gioiTinh);
            tv_dienThoai = view.findViewById(R.id.tv_dienThoaiNV);
            tv_dienThoai.setText("Điện thoai :" +item.dienThoai);
            tv_diaChi= view.findViewById(R.id._tv_diaChiNV);
            tv_diaChi.setText("Địa chỉ: "+ item.diaChi);
            imageView_Xoa = view.findViewById(R.id.image_xoaNV);
            dao = new NhanVienDAO(getContext());
//            btn_Xoa.setOnClickListener(v -> {
//                fragment.Xoa(String.valueOf(item.maKH));
//            });
        }
        return view;
    }
}
