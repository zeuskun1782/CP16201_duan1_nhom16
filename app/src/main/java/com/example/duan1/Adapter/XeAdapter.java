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
import com.example.duan1.Fragment.FragmentXe;
import com.example.duan1.Model.HangXe;
import com.example.duan1.Model.Xe;
import com.example.duan1.R;

import java.util.ArrayList;

public class XeAdapter extends ArrayAdapter<Xe> {
    private Context context;
    private ArrayList<Xe> list;
     FragmentXe fragment;
     TextView tv_maXe, tv_tenXe, tv_giaXe, tv_mauXe, tv_Loai, tv_trangThai;
     ImageView img_Xoa;
    public XeAdapter(@NonNull Context context, FragmentXe fragment, ArrayList<Xe> list ) {
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
            view = inflater.inflate(R.layout.item_xe,null);
        }
        final Xe item = list.get(position);
        if (item != null){
            HangXeDAO hangXeDAO = new HangXeDAO(context);
            HangXe hangXe = hangXeDAO.GetID(String.valueOf(item.maHang));
            tv_maXe = view.findViewById(R.id.tv_maXe);
            tv_maXe.setText("Mã xe:"+item.maXe);
            tv_tenXe = view.findViewById(R.id.tv_tenXe);
            tv_tenXe.setText("Tên xe:"+item.tenXe);
            tv_giaXe = view.findViewById(R.id.tv_giaXe);
            tv_giaXe.setText("Giá xe:"+item.giaXe);
            tv_mauXe = view.findViewById(R.id.tv_mauXe);
            tv_mauXe.setText("Màu xe :"+item.mauXe);
            tv_trangThai = view.findViewById(R.id.tv_TrangThai);
            tv_trangThai.setText("Trạng Thái :"+item.trangThai);
            tv_Loai= view.findViewById(R.id._tv_maHangXe);
            tv_Loai.setText("Hãng xe:"+hangXe.tenHang);
            img_Xoa = view.findViewById(R.id.img_xoaXe);
            img_Xoa.setOnClickListener(v -> {
                    fragment.Xoa(String.valueOf(item.maXe));
            });
        }
        return view;
    }
}
