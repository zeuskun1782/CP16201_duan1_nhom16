package com.example.duan1.Head;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.R;

public class Registration extends AppCompatActivity {
    TaiKhoanDAO dao;
    EditText ed_tenDN, ed_hovaten, ed_dienthoai, ed_mk, ed_nhaplaiMK;
    Button btn_dangky, btn_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ed_tenDN = findViewById(R.id.ed_taiKhoan);
        ed_hovaten = findViewById(R.id.ed_hovaten);
        ed_dienthoai = findViewById(R.id.ed_dienthoaiDK);
        ed_mk = findViewById(R.id.ed_matkhauDK);
        ed_nhaplaiMK = findViewById(R.id.ed_nhaplaiMK);
        btn_dangky = findViewById(R.id.btn_dangkyTK);
        btn_huy = findViewById(R.id.btn_huyDK);

        dao = new TaiKhoanDAO(this);

        btn_huy.setOnClickListener(v -> {
            ed_tenDN.setText("");
            ed_hovaten.setText("");
            ed_dienthoai.setText("");
            ed_mk.setText("");
            ed_nhaplaiMK.setText("");
            Intent intent = new Intent(Registration.this,Login.class);
            startActivity(intent);
        });
        btn_dangky.setOnClickListener(v -> {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.maTK = ed_tenDN.getText().toString();
            taiKhoan.hoTenTK = ed_hovaten.getText().toString();
            taiKhoan.dienThoai = ed_dienthoai.getText().toString();
            taiKhoan.matKhau = ed_mk.getText().toString();
            if (validate()>0){
                if (dao.Insert(taiKhoan)>0){
                    Toast.makeText(Registration.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    ed_tenDN.setText("");
                    ed_hovaten.setText("");
                    ed_dienthoai.setText("");
                    ed_mk.setText("");
                    ed_nhaplaiMK.setText("");
                    Intent intent = new Intent(Registration.this,Login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Registration.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public int validate(){
        int check = 1;
        if (ed_tenDN.getText().length()==0||ed_hovaten.getText().length()==0||ed_dienthoai.getText().length()==0||ed_mk.getText().length()==0||ed_nhaplaiMK.getText().length()==0){
            Toast.makeText(Registration.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String Pass = ed_mk.getText().toString();
            String RePass = ed_nhaplaiMK.getText().toString();
            if (!Pass.equals(RePass)){
                Toast.makeText(Registration.this,"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}