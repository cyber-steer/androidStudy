package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cal);

        EditText edtxtA = (EditText) findViewById(R.id.edTxtA);
        EditText edtxtB = (EditText) findViewById(R.id.edTxtB);
        TextView textView = (TextView) findViewById(R.id.txtR);

        Button button1 = (Button) findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edtxtA.getText().toString();
                String str2 = edtxtB.getText().toString();
                int r = Integer.parseInt(str1) + Integer.parseInt(str2);
                textView.setText("결과 : "+r);
                Toast.makeText(SimpleCal.this, "결과 : "+r, Toast.LENGTH_SHORT).show();
            }
        });
        Button button2 = (Button) findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edtxtA.getText().toString();
                String str2 = edtxtB.getText().toString();
                int r = Integer.parseInt(str1) - Integer.parseInt(str2);
                textView.setText("결과 : "+r);
                Toast.makeText(SimpleCal.this, "결과 : "+r, Toast.LENGTH_SHORT).show();
            }
        });
        Button button3 = (Button) findViewById(R.id.btn3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edtxtA.getText().toString();
                String str2 = edtxtB.getText().toString();
                int r = Integer.parseInt(str1) * Integer.parseInt(str2);
                textView.setText("결과 : "+r);
                Toast.makeText(SimpleCal.this, "결과 : "+r, Toast.LENGTH_SHORT).show();
            }
        });
        Button button4 = (Button) findViewById(R.id.btn4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edtxtA.getText().toString();
                String str2 = edtxtB.getText().toString();
                double r = Double.parseDouble(str1) / Double.parseDouble(str2);
                textView.setText("결과 : "+r);
                Toast.makeText(SimpleCal.this, "결과 : "+r, Toast.LENGTH_SHORT).show();
            }
        });
    }
}