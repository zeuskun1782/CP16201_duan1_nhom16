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

import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.Adapter.TaiKhoanAdapter;
import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.Model.NhanVien;
import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentTaoTaiKhoan extends Fragment {
    Dialog dialog;
    ArrayAdapter adapter;
    EditText ed_tenDN, ed_hoten, ed_dienthoai, ed_matkhau;
    Button btnSave, btnCannel;
    ArrayList<TaiKhoan> list;
    TaiKhoanDAO dao;
    ListView lv;
    FloatingActionButton flb;
    TaiKhoan item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_tao_tai_khoan, container, false);
       lv = view.findViewById(R.id.lv_taikhoan);
       flb = view.findViewById(R.id.flb_taikhoan);
       dao = new TaiKhoanDAO(getActivity());
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
        dialog.setContentView(R.layout.dialog_tai_khoan);
        ed_tenDN = dialog.findViewById(R.id.dialog_tenDN);
        ed_hoten = dialog.findViewById(R.id.dialog_hoten);
        ed_dienthoai = dialog.findViewById(R.id.dialog_dienthoai);
        ed_matkhau = dialog.findViewById(R.id.dialog_MK);
        btnCannel = dialog.findViewById(R.id.btn_huyTK);
        btnSave = dialog.findViewById(R.id.btn_themTK);

        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new TaiKhoan();
            item.maTK = ed_tenDN.getText().toString();
            item.hoTenTK = ed_hoten.getText().toString();
            item.dienThoai = ed_dienthoai.getText().toString();
            item.matKhau = ed_matkhau.getText().toString();
            if (validate()>0){
                if (type == 0) {
                    if (dao.Insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.maTK = ed_tenDN.getText().toString();
                    if (dao.UpdateMK(item)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            dialog.dismiss();
            Capnhaplv();
        });
        dialog.show();
    }
    void Capnhaplv(){
        list = (ArrayList<TaiKhoan>) dao.GetAll();
        adapter = new TaiKhoanAdapter(getActivity(),this,list);
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
        if (ed_tenDN.getText().length()==0||ed_hoten.getText().length()==0||ed_dienthoai.getText().length()==0||ed_matkhau.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}