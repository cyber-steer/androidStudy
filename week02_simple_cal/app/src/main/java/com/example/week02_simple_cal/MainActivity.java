package com.example.week02_simple_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNum1, etNum2;
    Button btnPlus, btnMinus, btnMultiply, btnDevide;
    TextView txtResult;

    String num1, num2;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        txtResult = (TextView) findViewById(R.id.txtResult);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Integer.parseInt(num1)+Integer.parseInt(num2);
                txtResult.setText("결과:"+result);
            }
        });

        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Integer.parseInt(num1)-Integer.parseInt(num2);
                txtResult.setText("결과:"+result);
            }
        });

        btnMultiply = (Button) findViewById(R.id.btnMultply);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Integer.parseInt(num1)*Integer.parseInt(num2);
                txtResult.setText("결과:"+result);
            }
        });

        btnDevide = (Button) findViewById(R.id.btnDevide);
        btnDevide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                double result = Double.parseDouble(num1) / Double.parseDouble(num2);
                txtResult.setText("결과:"+result);
            }
        });
    }
}
