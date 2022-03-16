package com.example.number;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnP, btnM, btnS, btnD;
    EditText edt1, edt2;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult=(TextView) findViewById(R.id.txtresult);

        edt1=(EditText) findViewById(R.id.edt1);
        edt2=(EditText) findViewById(R.id.edt2);

        btnP=(Button) findViewById(R.id.btn1);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String num1 = edt1.getText().toString();
               String num2 = edt2.getText().toString();
               int result= Integer.parseInt(num1) + Integer.parseInt(num2);
               textResult.setText("결과:" + result);

            }
        });
        btnM=(Button) findViewById(R.id.btn2);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = edt1.getText().toString();
                String num2 = edt2.getText().toString();
                int result= Integer.parseInt(num1) - Integer.parseInt(num2);
                textResult.setText("결과:" + result);
            }
        });
        btnS=(Button) findViewById(R.id.btn3);

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = edt1.getText().toString();
                String num2 = edt2.getText().toString();
                int result= Integer.parseInt(num1) * Integer.parseInt(num2);
                textResult.setText("결과:" + result);
            }
        });
        btnD=(Button) findViewById(R.id.btn4);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = edt1.getText().toString();
                String num2 = edt2.getText().toString();
                double result= Double.parseDouble(num1) / Double.parseDouble(num2);
                textResult.setText("결과:" + result);
            }
        });


    }
}