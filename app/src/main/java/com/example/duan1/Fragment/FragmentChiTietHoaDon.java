package com.example.duan1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.Adapter.ChiTietHoaDonAdapter;
import com.example.duan1.Adapter.XeAdapter;
import com.example.duan1.Adapter.XeSpinnerAdapter;
import com.example.duan1.DAO.ChiTietHoaDonDAO;
import com.example.duan1.DAO.XeDAO;
import com.example.duan1.Model.ChiTietHoaDon;
import com.example.duan1.Model.Xe;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentChiTietHoaDon extends Fragment {
    ListView lv;
    FloatingActionButton flb;
    static ChiTietHoaDonDAO dao;
    Dialog dialog;
    Spinner spinner;
    XeDAO xeDAO;
    ArrayList<Xe> xeArrayList;
    XeSpinnerAdapter adapter;
    ArrayAdapter<ChiTietHoaDon> arrayAdapter;
    ArrayList<ChiTietHoaDon> list;
    ChiTietHoaDon item;
    int maXe, position;
    EditText ed_maHDTC, ed_soluong, ed_donviTinh, ed_donGia;
    Button btnSave, btnCannel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_hoa_don, container, false);
        lv = view.findViewById(R.id.lv_cthd);
        flb = view.findViewById(R.id.flb_cthd);
        Capnhatlv();

        dao = new ChiTietHoaDonDAO(getActivity());

        flb.setOnClickListener(v -> {
            opendialog(getActivity(),0);
            return;
        });
        lv.setOnItemClickListener((parent, view1, position1, id) -> {
            item = new ChiTietHoaDon();
            item = list.get(position1);
            opendialog(getActivity(),1);
            return;
        });
        return view;
    }
    protected void opendialog(final Context context, final int type){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialong_chitiethoadon);
        ed_maHDTC = dialog.findViewById(R.id.ed_maCTHD);
        ed_soluong = dialog.findViewById(R.id.ed_soLuong);
        ed_donviTinh = dialog.findViewById(R.id.ed_donviTinh);
        ed_donGia = dialog.findViewById(R.id.ed_donGia);
        btnSave = dialog.findViewById(R.id.btn_themCTHD);
        btnCannel = dialog.findViewById(R.id.btn_huyCT);
        spinner = dialog.findViewById(R.id.sp_tenXeHD);
        xeDAO = new XeDAO(context);
        xeArrayList = new ArrayList<>();
        xeArrayList = (ArrayList<Xe>) xeDAO.GetAll();
        adapter = new XeSpinnerAdapter(context,xeArrayList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maXe = xeArrayList.get(position).maXe;
                Toast.makeText(context, "Chọn : " + xeArrayList.get(position).tenXe, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ed_maHDTC.setEnabled(false);
        if (type != 0) {
            ed_maHDTC.setText(String.valueOf(item.maHD));
            ed_soluong.setText(String.valueOf(item.soLuong));
            ed_donviTinh.setText(String.valueOf(item.donviTinh));
            ed_donGia.setText(String.valueOf(item.donGia));
            for (int i = 0; i < xeArrayList.size(); i++)
                if (item.maXe == (xeArrayList.get(i).maXe)) {
                    position = i;
                }
            spinner.setSelection(position);
        }
        btnCannel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(v -> {
            item = new ChiTietHoaDon();
            item.maXe = maXe;
            item.soLuong = Integer.parseInt(ed_soluong.getText().toString());
            item.donviTinh = ed_donviTinh.getText().toString();
            item.donGia = Integer.parseInt(ed_donGia.getText().toString());
            if (type == 0){
                if (dao.Insert(item)>0){
                    Toast.makeText(context,"Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                item.maHD = Integer.parseInt(ed_maHDTC.getText().toString());
                if (dao.Update(item)>0){
                    Toast.makeText(context,"Sửa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            dialog.dismiss();
            Capnhatlv();
        });
        dialog.show();
    }
    void Capnhatlv(){
        dao = new ChiTietHoaDonDAO(getActivity());
        list = (ArrayList<ChiTietHoaDon>) dao.GetAll();
        arrayAdapter = new ChiTietHoaDonAdapter(getContext(),this,list);
        lv.setAdapter(arrayAdapter);
    }
}