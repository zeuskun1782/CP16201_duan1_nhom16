package com.example.duan1.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.R;

import static android.content.Context.MODE_PRIVATE;


public class FragmentDoiMatKhau extends Fragment {
    EditText edPassOld, edPass, edRepass;
    TaiKhoanDAO dao;
    Button btnSave, btnCannel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        edPassOld = view.findViewById(R.id.ed_doiMKCU);
        edPass = view.findViewById(R.id.ed_NhapMKmoi);
        edRepass = view.findViewById(R.id.ed_nhaplaiMkmoi);
        btnSave = view.findViewById(R.id.btn_doiMK);
        btnCannel = view.findViewById(R.id.btn_huydoiMK);

        dao = new TaiKhoanDAO(getActivity());

        btnCannel.setOnClickListener(v -> {
            edPassOld.setText("");
            edPass.setText("");
            edRepass.setText("");
        });
        btnSave.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String user = sharedPreferences.getString("USERNAME","");
            if (validate()>0){
                TaiKhoan taiKhoan = dao.GetID(user);
                taiKhoan.matKhau = edPass.getText().toString();
                dao.UpdateMK(taiKhoan);
                if (dao.UpdateMK(taiKhoan) > 0){
                    Toast.makeText(getContext(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    edPassOld.setText("");
                    edPass.setText("");
                    edRepass.setText("");
                }else {
                    Toast.makeText(getContext(),"Thay đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public int validate(){
        int check = 1;
        if (edPassOld.getText().length()==0 || edPass.getText().length()==0 || edRepass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String PassOld = pref.getString("PASSWORD","");
            String Pass = edPass.getText().toString();
            String RePass = edRepass.getText().toString();
            if (!PassOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check = -1;
            }if (!Pass.equals(RePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}