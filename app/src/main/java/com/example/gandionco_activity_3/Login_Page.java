package com.example.gandionco_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {

    EditText txt_username_2, txt_password_2;
    Button btn_login, btn_register_2;
    database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        txt_username_2 = (EditText)findViewById(R.id.txt_username_2);
        txt_password_2 = (EditText)findViewById(R.id.txt_password_2);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register_2 = (Button)findViewById(R.id.btn_register_2);
        DB = new database(this);

        btn_register_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txt_username_2.getText().toString();
                String pw = txt_password_2.getText().toString();
                if(user.equals("")||pw.equals(""))
                    Toast.makeText(Login_Page.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpw = DB.checkusernamepassword(user,pw);
                    if(checkuserpw==true){
                        Toast.makeText(Login_Page.this,"Login Successful :)",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_Page.this,"Login Failed :(",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}