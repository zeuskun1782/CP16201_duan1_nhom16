package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Adapter.HangXeAdapter;
import com.example.duan1.Adapter.KhachHangAdapter;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.Model.HangXe;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentKhachHang extends Fragment {
    ListView lv;
    ArrayList<KhachHang> list;
    ArrayAdapter adapter;
    static KhachHangDAO dao;
    Dialog dialog;
    Button btnSave, btnCannel;
    EditText ed_maKH, ed_tenKH, ed_namSinh, ed_dienThoai, ed_diaChi;
    FloatingActionButton flb;
    KhachHang item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        lv = view.findViewById(R.id.lv_khachhang);
        flb = view.findViewById(R.id.flb_khachhang);
        dao = new KhachHangDAO(getActivity());
        Capnhaplv();
        flb.setOnClickListener(v -> {
            opendialog(getActivity(),0);
            return;
        });
        lv.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            opendialog(getActivity(),1);
        });
        return view;
    }
    protected void opendialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_khach_hang);
        ed_maKH = dialog.findViewById(R.id.ed_maKH);
        ed_tenKH = dialog.findViewById(R.id.ed_tenKH);
        ed_namSinh = dialog.findViewById(R.id.ed_namSinh);
        ed_dienThoai = dialog.findViewById(R.id.ed_dienThoai);
        ed_diaChi = dialog.findViewById(R.id.ed_diaChi);
        btnCannel = dialog.findViewById(R.id.btn_huyKH);
        btnSave = dialog.findViewById(R.id.btn_themKH);

        ed_maKH.setEnabled(false);
        if (type != 0) {
            ed_maKH.setText(String.valueOf(item.maKH));
            ed_tenKH.setText(item.tenKH);
        }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new KhachHang();
            item.tenKH = ed_tenKH.getText().toString();
            item.namSinh = ed_namSinh.getText().toString();
            item.dienThoai = ed_dienThoai.getText().toString();
            item.diaChi = ed_diaChi.getText().toString();
                if (type == 0) {
                    if (dao.Insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.maKH = Integer.parseInt(ed_maKH.getText().toString());
                    if (dao.Update(item)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            dialog.dismiss();
            Capnhaplv();
        });
        dialog.show();
    }
    void Capnhaplv(){
        list = (ArrayList<KhachHang>) dao.GetAll();
        adapter = new KhachHangAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
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
    public int validate(){
        int check = 1;
        if (ed_tenKH.getText().length()==0||ed_namSinh.getText().length()==0||ed_dienThoai.getText().length()==0||ed_diaChi.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}