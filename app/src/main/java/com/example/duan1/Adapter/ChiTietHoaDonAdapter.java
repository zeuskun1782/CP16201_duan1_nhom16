package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1.DAO.HangXeDAO;
import com.example.duan1.DAO.XeDAO;
import com.example.duan1.Fragment.FragmentChiTietHoaDon;
import com.example.duan1.Model.ChiTietHoaDon;
import com.example.duan1.Model.HangXe;
import com.example.duan1.Model.Xe;
import com.example.duan1.R;

import java.util.ArrayList;

public class ChiTietHoaDonAdapter extends ArrayAdapter<ChiTietHoaDon> {
    private Context context;
    private ArrayList<ChiTietHoaDon> list;
    FragmentChiTietHoaDon fragment;
    TextView tv_maCTHD, tv_xeCTHD, tv_soLuongCTHD, tv_donviTinh, tv_donGiaCTHD;
    public ChiTietHoaDonAdapter(@NonNull Context context, FragmentChiTietHoaDon fragment, ArrayList<ChiTietHoaDon> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment =fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_chitiethoadon, null);
        }
        final ChiTietHoaDon item = list.get(position);
        if (item != null){
            XeDAO xeDAO = new XeDAO(context);
            Xe xe = xeDAO.GetID(String.valueOf(item.maXe));
            tv_maCTHD = view.findViewById(R.id.tv_maCTHD);
            tv_maCTHD.setText("Mã Hóa đơn :"+item.maHD+".");
            tv_xeCTHD = view.findViewById(R.id.tv_maXeHD);
            tv_xeCTHD.setText("Tên xe :"+item.maXe);
            tv_soLuongCTHD = view.findViewById(R.id.tv_soLuong);
            tv_soLuongCTHD.setText("Số lượng :"+item.soLuong);
            tv_donviTinh = view.findViewById(R.id.tv_donviTinh);
            tv_donviTinh.setText("Đơn vị tính :"+item.donviTinh);
            tv_donGiaCTHD = view.findViewById(R.id._tv_donGia);
            tv_donGiaCTHD.setText("Đơn Giá : "+item.donGia);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_chitiethoadon, null);
        }
        final ChiTietHoaDon item = list.get(position);
        if (item != null){
            tv_maCTHD = view.findViewById(R.id.tv_maCTHD);
            tv_maCTHD.setText("Mã Hóa đơn :"+item.maHD+".");
            tv_xeCTHD = view.findViewById(R.id.tv_maXeHD);
            tv_xeCTHD.setText("Tên xe :"+item.maXe);
            tv_soLuongCTHD = view.findViewById(R.id.tv_soLuong);
            tv_soLuongCTHD.setText("Số lượng :"+item.soLuong);
            tv_donviTinh = view.findViewById(R.id.tv_donviTinh);
            tv_donviTinh.setText("Đơn vị tính :"+item.donviTinh);
            tv_donGiaCTHD = view.findViewById(R.id._tv_donGia);
            tv_donGiaCTHD.setText("Đơn Giá : "+item.donGia);
        }
        return view;
    }
}
