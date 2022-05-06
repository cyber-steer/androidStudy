package com.example.midletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cal extends AppCompatActivity {
    Button btnPl, btnMi, btnMu, btnMo,btnMovie;
    EditText edA,edB;
    TextView txtR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        edA = (EditText) findViewById(R.id.edA);
        edB = (EditText) findViewById(R.id.edB);
        txtR = (TextView) findViewById(R.id.txtR);

        btnPl = (Button) findViewById(R.id.btnPl);
        btnPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edA.getText().toString();
                String str2 = edB.getText().toString();
                int r = Integer.parseInt(str1)+Integer.parseInt(str2);
                txtR.setText("계산결과 : "+r);
            }
        });
        btnMi = (Button) findViewById(R.id.btnMi);
        btnMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edA.getText().toString();
                String str2 = edB.getText().toString();
                int r = Integer.parseInt(str1)-Integer.parseInt(str2);
                txtR.setText("계산결과 : "+r);
            }
        });
        btnMu = (Button) findViewById(R.id.btnMu);
        btnMu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edA.getText().toString();
                String str2 = edB.getText().toString();
                int r = Integer.parseInt(str1)*Integer.parseInt(str2);
                txtR.setText("계산결과 : "+r);
            }
        });
        btnMo = (Button) findViewById(R.id.btnMo);
        btnMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edA.getText().toString();
                String str2 = edB.getText().toString();
                double r = Double.parseDouble(str1)/Double.parseDouble(str2);
                txtR.setText("계산결과 : "+r);
            }
        });
        btnMovie = (Button) findViewById(R.id.btnMovie);
        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}