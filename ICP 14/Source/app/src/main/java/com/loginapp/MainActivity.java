package com.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button Loginbtn;
    EditText UsernameCtrl;
    EditText PassowrdCtrl;
    TextView StatuesLabelCtrl;
    String username;
    String passowrd;

    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameCtrl = findViewById(R.id.UsernameText);
        PassowrdCtrl = findViewById(R.id.PasswordText);
        StatuesLabelCtrl = findViewById(R.id.StatuesLabel);
        username = UsernameCtrl.getText().toString();
        passowrd = PassowrdCtrl.getText().toString();
        Loginbtn = findViewById(R.id.Loginbtn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UsernameCtrl.getText().toString().isEmpty() && !PassowrdCtrl.getText().toString().isEmpty()) {
                    if (UsernameCtrl.getText().toString().equals("pujitha") && PassowrdCtrl.getText().toString().equals("1234567")) {
                        flag = true;
                    }
                }
                if (!flag) {
                    StatuesLabelCtrl.setText("Wrong Username or Password");
                }
                else {
                    reDirectToHomePage();
                }
            }


        });
    }

    public void reDirectToHomePage () {
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
    }
}