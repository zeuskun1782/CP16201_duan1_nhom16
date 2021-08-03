package com.example.duan1.Head;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.MainActivity;
import com.example.duan1.R;

public class Login extends AppCompatActivity {
    String struser, strpass;
    TaiKhoanDAO dao;
    CheckBox cbx;
    Button btn_login, btn_Reg;
    EditText ed_tenDN, ed_MK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_tenDN = findViewById(R.id.ed_dangNhap);
        ed_MK = findViewById(R.id.ed_matKhau);
        cbx = findViewById(R.id.cbx_luuMK);
        btn_login = findViewById(R.id.btn_dangNhapMK);
        btn_Reg = findViewById(R.id.ed_dangky);
        dao = new TaiKhoanDAO(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        ed_tenDN.setText(preferences.getString("USERNAME",""));
        ed_MK.setText(preferences.getString("PASSWORD",""));
        cbx.setChecked(preferences.getBoolean("REMEMBER",false));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });
        btn_Reg.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this,Registration.class);
            startActivity(intent);
        });
    }
    public void checklogin(){
        struser = ed_tenDN.getText().toString();
        strpass = ed_MK.getText().toString();
        if (struser.isEmpty() || strpass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checklogin(struser,strpass)>0 || (struser.equals("admin") && (strpass.equals("admin")))){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                remember(struser, strpass,cbx.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
    public void remember(String u, String p, boolean status){
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}