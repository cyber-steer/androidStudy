package com.example.week02_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text, txtResult;
    Integer result;
    Button btnClear, btnBackspace, btnMod;
    Button btnDivide, btnMultiply, btnMinus, btnPlus;
    Button btnResult,btnPM,btnDot;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIds = {R.id.btn0,R.id.btn1,R.id.btn2,
            R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,
            R.id.btn7,R.id.btn8,R.id.btn9};
    String number, num;
    int i, num1, num2;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResult);
        for(i=0;i<numBtnIds.length;i++){
            numButtons[i] = (Button) findViewById(numBtnIds[i]);
            final int index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num = numButtons[index].getText().toString();
                    number = txtResult.getText().toString()+num;
                    txtResult.setText(number);

                }
            });
        }

        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num2 = Integer.parseInt(number);
                txtResult.setText((num1+num2)+"");
            }
        });

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Integer.parseInt(number);
                txtResult.setText("");
            }
        });
    }
}