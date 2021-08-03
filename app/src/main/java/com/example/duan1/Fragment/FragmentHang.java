package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Adapter.HangXeAdapter;
import com.example.duan1.DAO.HangXeDAO;
import com.example.duan1.Model.HangXe;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentHang extends Fragment {
     ListView lv;
     ArrayList<HangXe> list;
     FloatingActionButton flb_them, flb_sua;
     ExtendedFloatingActionButton flb_tong;
     Dialog dialog;
     EditText ed_Mahang, ed_Tenhang;
     Button btnSave, btnCannel;
     static HangXeDAO dao;
     HangXeAdapter adapter;
     HangXe item;
     Boolean isAllFlb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hang, container, false);
        lv = view.findViewById(R.id.lv_hangxe);
        flb_them = view.findViewById(R.id.flb_Them);
        flb_sua = view.findViewById(R.id.flb_Sua);
        flb_tong = view.findViewById(R.id.flb_tong);

        flb_them.setVisibility(View.GONE);
        flb_sua.setVisibility(View.GONE);

        isAllFlb = false;
        flb_tong.shrink();

        flb_tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllFlb){
                    flb_them.show();
                    flb_sua.show();
                    flb_them.setVisibility(View.VISIBLE);
                    flb_sua.setVisibility(View.VISIBLE);

                    flb_tong.extend();
                    isAllFlb = true;
                }else {
                    flb_them.hide();
                    flb_sua.hide();

                    flb_tong.shrink();

                    isAllFlb = false;
                }
                flb_sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });


            }
        });
        dao = new HangXeDAO(getActivity());
        Capnhaplv();

        flb_them.setOnClickListener(v -> {
            opendialog(getActivity(),0);
            return;
        });

        lv.setOnItemClickListener((parent, view1, position, id) -> {
            item = new HangXe();
            item =  list.get(position);
            opendialog(getActivity(),1);
            return;
        });
        return view;
    }
    public void opendialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hang_xe);
        ed_Mahang = dialog.findViewById(R.id.ed_mahang);
        ed_Tenhang = dialog.findViewById(R.id.ed_tenhang);
        btnCannel = dialog.findViewById(R.id.btn_huyhang);
        btnSave = dialog.findViewById(R.id.btn_themhang);

        ed_Mahang.setEnabled(false);
         if (type != 0){
            ed_Mahang.setText(String.valueOf(item.maHang));
            ed_Tenhang.setText(item.tenHang);
         }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new HangXe();
            item.tenHang = ed_Tenhang.getText().toString();
                if (type == 0){
                    if (dao.Insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.maHang = Integer.parseInt(ed_Mahang.getText().toString());
                    if (dao.Update(item)>0){
                        Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            dialog.dismiss();
            Capnhaplv();
        });
        dialog.show();
    }
    public void Xoa(final String ID){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setIcon(R.drawable.ic_deltet);
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", (dialog1, which) -> {
            dao.Delete(ID);
            Capnhaplv();
            dialog1.cancel();
        });
        builder.setNegativeButton("Không", (dialog1, which) -> {
            dialog1.cancel();
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void Capnhaplv(){
        list = (ArrayList<HangXe>) dao.GetAll();
        adapter = new HangXeAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (ed_Tenhang.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}