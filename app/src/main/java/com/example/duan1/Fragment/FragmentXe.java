package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.Adapter.XeAdapter;
import com.example.duan1.Adapter.HangXeSpinnerAdapter;
import com.example.duan1.DAO.HangXeDAO;
import com.example.duan1.DAO.XeDAO;
import com.example.duan1.Model.HangXe;
import com.example.duan1.Model.Xe;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentXe extends Fragment {
    ListView lv;
    ArrayList<Xe> list;
    FloatingActionButton flb;
    Dialog dialog;
    EditText ed_maXe, ed_tenXe, ed_giaXe, ed_mauXe, ed_trangThai;
    Spinner spinner;
    Button btnSave, btnCannel;
    static XeDAO dao;
    XeAdapter adapter;
    Xe item;
    HangXeSpinnerAdapter spinnerAdapter;
    ArrayList<HangXe> listHangXe;
    HangXeDAO hangXeDAO;
    int mahang, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xe, container, false);
        lv = view.findViewById(R.id.lv_xe);
        flb = view.findViewById(R.id.flb_xe);
        dao = new XeDAO(getActivity());
        Capnhatlv();

        flb.setOnClickListener(v -> {
            Dialog(getActivity(), 0);
            return;
        });
        lv.setOnItemClickListener((parent, view1, position1, id) -> {
            item = list.get(position1);
            Dialog(getActivity(), 0);
        });
        return view;
    }

    protected void Dialog(final Context context, final int type) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_xe);
        ed_maXe = dialog.findViewById(R.id.ed_maXe);
        ed_tenXe = dialog.findViewById(R.id.ed_tenXe);
        ed_trangThai = dialog.findViewById(R.id.ed_trangThai);
        ed_giaXe = dialog.findViewById(R.id.ed_giaXe);
        ed_mauXe = dialog.findViewById(R.id.ed_mauXe);
        btnSave = dialog.findViewById(R.id.btn_themXe);
        btnCannel = dialog.findViewById(R.id.btn_huyXe);
        spinner = dialog.findViewById(R.id.Sp_mahang);
        hangXeDAO = new HangXeDAO(context);
        listHangXe = new ArrayList<HangXe>();
        listHangXe = (ArrayList<HangXe>) hangXeDAO.GetAll();
        spinnerAdapter = new HangXeSpinnerAdapter(context, listHangXe);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mahang = listHangXe.get(position).maHang;
                Toast.makeText(context, "Chọn : " + listHangXe.get(position).tenHang, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ed_maXe.setEnabled(false);
        if (type != 0) {
            ed_maXe.setText(String.valueOf(item.maXe));
            ed_tenXe.setText(item.tenXe);
            ed_giaXe.setText(String.valueOf(item.giaXe));
            for (int i = 0; i < listHangXe.size(); i++)
                if (item.maHang == (listHangXe.get(i).maHang)) {
                    position = i;
                }
            spinner.setSelection(position);
        }
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            item = new Xe();
            item.tenXe = ed_tenXe.getText().toString();
            item.giaXe = Integer.parseInt(ed_giaXe.getText().toString());
            item.mauXe = ed_mauXe.getText().toString();
            item.trangThai = ed_trangThai.getText().toString();
            item.maHang = mahang;
            if (type == 0) {
                if (dao.Insert(item) > 0) {
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                item.maHang = Integer.parseInt(ed_maXe.getText().toString());
                if (dao.Update(item)>0){
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            dialog.dismiss();
            Capnhatlv();
        });
        dialog.show();
    }

    public void Xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setIcon(R.drawable.ic_deltet);
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.Delete(Id);
                Capnhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    void Capnhatlv(){
        list = (ArrayList<Xe>) dao.GetAll();
        adapter = new XeAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (ed_tenXe.getText().length()==0||ed_giaXe.getText().length()==0||ed_giaXe.getText().length()==0||ed_mauXe.getText().length()==0||ed_trangThai.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}