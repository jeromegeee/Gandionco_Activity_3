package com.example.gandionco_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_username, txt_password, txt_confirmpassword;
    Button btn_register, btn_return;
    database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_confirmpassword = (EditText) findViewById(R.id.txt_confirmpassword);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_return = (Button) findViewById(R.id.btn_return);
        DB = new database(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txt_username.getText().toString();
                String pw = txt_password.getText().toString();
                String cpw = txt_confirmpassword.getText().toString();

                if (user.equals("") | pw.equals("") | cpw.equals(""))
                    Toast.makeText(MainActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                else {

                    if (pw.equals(cpw)) {
                        Boolean checkuser = DB.checkusername(user);

                        if (checkuser==false) {
                            Boolean insert = DB.insertData(user, pw);

                            if (insert==true)
                                Toast.makeText(MainActivity.this, "Registration Successful :)", Toast.LENGTH_SHORT).show();
                             else
                                Toast.makeText(MainActivity.this, "Registration Failed :(", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(MainActivity.this, "Username is taken :(", Toast.LENGTH_SHORT).show();

                        }
                    } else{
                        Toast.makeText(MainActivity.this, "Password does not match :(", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_Page.class);
                startActivity(intent);
            }
        });
    }
}