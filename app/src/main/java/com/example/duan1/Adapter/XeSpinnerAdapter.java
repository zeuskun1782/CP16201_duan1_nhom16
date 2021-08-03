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
import com.example.duan1.Model.Xe;
import com.example.duan1.R;

import java.util.ArrayList;

public class XeSpinnerAdapter extends ArrayAdapter<Xe> {
    private Context context;
    private ArrayList<Xe> list ;
    TextView tv_maXe, tv_tenXe;

    public XeSpinnerAdapter(@NonNull Context context, ArrayList<Xe> list) {
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
            view = layoutInflater.inflate(R.layout.item_spinner_xe, null);
        }
        final Xe item = list.get(position);
        if (item != null){
            tv_maXe = view.findViewById(R.id.tv_maXeCTHD);
            tv_maXe.setText(item.maXe+".");
            tv_tenXe = view.findViewById(R.id.tv_tenXeCTHD);
            tv_tenXe.setText(item.tenXe);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_spinner_xe, null);
        }
        final Xe item = list.get(position);
        if (item != null){
            tv_maXe = view.findViewById(R.id.tv_maXeCTHD);
            tv_maXe.setText(item.maXe+".");
            tv_tenXe = view.findViewById(R.id.tv_tenXeCTHD);
            tv_tenXe.setText(item.tenXe);
        }
        return view;
    }
}
