package com.example.week09jsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText edName, edTel, edEmail;
    Button btnSave, btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = (EditText) findViewById(R.id.edName);
        edTel = (EditText) findViewById(R.id.edTel);
        edEmail = (EditText) findViewById(R.id.edEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String tel = edTel.getText().toString();
                String email = edEmail.getText().toString();
                String result="";
                CustomTask task = new CustomTask();
                try {
                    result = task.execute("insert",name,tel,email).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(result.equals("true")){
                    edName.setText("");
                    edTel.setText("");
                    edEmail.setText("");
                    Toast.makeText(MainActivity.this, "저장 성공", Toast.LENGTH_SHORT).show();
                }
                else{
                    edName.setText("");
                    Toast.makeText(MainActivity.this, "증복된 이름입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String result="";
                CustomTask task = new CustomTask();
                try {
                    result = task.execute("select",name).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(result.equals("false")){
                    Toast.makeText(MainActivity.this, "검색 실패", Toast.LENGTH_SHORT).show();
                    edTel.setText("");
                    edEmail.setText("");
                }
                else{
                    String[] results = result.split(" ");
                    edTel.setText(results[0]);
                    edEmail.setText(results[1]);
                    Toast.makeText(MainActivity.this, "검색 완료", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}