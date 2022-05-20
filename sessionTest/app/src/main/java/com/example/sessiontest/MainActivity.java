package com.example.sessiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUserName, etPassword;
    Button btLogin;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.et_userName);
        etPassword = findViewById(R.id.et_password);
        btLogin = findViewById(R.id.bt_login);

        sessionManager = new SessionManager(getApplicationContext());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUserName = etUserName.getText().toString().trim();
                String sPassword = etPassword.getText().toString().trim();

                if(sPassword.equals("")){
                    etPassword.setError("Please enter password");
                }
                if(sPassword.equals("root")){
                    sessionManager.setLogin(true);
                    sessionManager.setUserName(sUserName);
                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(sessionManager.getLogin()){
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
        }
    }
}