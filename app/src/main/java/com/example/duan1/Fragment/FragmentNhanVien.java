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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.Adapter.KhachHangAdapter;
import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.Model.NhanVien;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentNhanVien extends Fragment {
    ListView lv;
    ArrayList<NhanVien> list;
    ArrayAdapter adapter;
    static NhanVienDAO dao;
    Dialog dialog;
    Button btnSave, btnCannel;
    EditText ed_maNV, ed_tenNV, ed_namSinh, ed_dienThoai, ed_diaChi, ed_gioiTinh;
    FloatingActionButton flb;
    NhanVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        lv = view.findViewById(R.id.lv_nhanvien);
        flb = view.findViewById(R.id.flb_nhanvien);
        dao = new NhanVienDAO(getActivity());
        Capnhaplv();
        flb.setOnClickListener(v -> {
            opendialog(getActivity(),0);
            return;
        });
        lv.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            opendialog(getActivity(),1);
        });
        return  view;

    }
    protected void opendialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_nhan_vien);
        ed_maNV = dialog.findViewById(R.id.ed_maNV);
        ed_tenNV = dialog.findViewById(R.id.ed_tenNV);
        ed_namSinh = dialog.findViewById(R.id.ed_namSinhNV);
        ed_dienThoai = dialog.findViewById(R.id.ed_dienThoaiNV);
        ed_diaChi = dialog.findViewById(R.id.ed_diachiNV);
        ed_gioiTinh = dialog.findViewById(R.id.sp_nhanvien);
        btnCannel = dialog.findViewById(R.id.btn_huyNV);
        btnSave = dialog.findViewById(R.id.btn_themNV);

        ed_maNV.setEnabled(false);
        if (type != 0) {
            ed_maNV.setText(String.valueOf(item.maNV));
            ed_tenNV.setText(item.tenNV);
        }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new NhanVien();
            item.tenNV = ed_tenNV.getText().toString();
            item.namSinh = Integer.parseInt(ed_namSinh.getText().toString());
            item.dienThoai = ed_dienThoai.getText().toString();
            item.gioiTinh = ed_gioiTinh.getText().toString();
            item.diaChi = ed_diaChi.getText().toString();
                if (type == 0) {
                    if (dao.Insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.maNV = Integer.parseInt(ed_maNV.getText().toString());
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
        list = (ArrayList<NhanVien>) dao.GetAll();
        adapter = new NhanVienAdapter(getActivity(),this,list);
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
        if (ed_tenNV.getText().length()==0||ed_namSinh.getText().length()==0||ed_gioiTinh.getText().length()==0||ed_dienThoai.getText().length()==0||ed_diaChi.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}