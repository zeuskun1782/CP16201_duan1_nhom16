package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1.Model.HangXe;
import com.example.duan1.R;

import java.util.ArrayList;

public class HangXeSpinnerAdapter extends ArrayAdapter<HangXe> {
    private Context context;
    private ArrayList<HangXe> list;
    TextView tv_mahang, tv_tenHang;
    public HangXeSpinnerAdapter(@NonNull Context context, ArrayList<HangXe> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_hangxe, null);
        }
        final HangXe item = list.get(position);
        if (item != null){
            tv_mahang = view.findViewById(R.id.tv_maHangADP);
            tv_mahang.setText(item.maHang+".");
            tv_tenHang = view.findViewById(R.id.tv_tenHangADP);
            tv_tenHang.setText(item.tenHang);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_hangxe, null);
        }
        final HangXe item = list.get(position);
        if (item != null){
            tv_mahang = view.findViewById(R.id.tv_maHangADP);
            tv_mahang.setText(item.maHang+".");
            tv_tenHang = view.findViewById(R.id.tv_tenHangADP);
            tv_tenHang.setText(item.tenHang);
        }
        return view;
    }
}
