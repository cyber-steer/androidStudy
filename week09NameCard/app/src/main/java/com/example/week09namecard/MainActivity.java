package com.example.week09namecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText inputName, inputTelNo, inputEmail;
    Button btnSave, btnSearch;
    String name, telNo, eMail;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputName = (EditText) findViewById(R.id.inputName);
        inputTelNo = (EditText) findViewById(R.id.inputTelNo);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = inputName.getText().toString();
                telNo = inputTelNo.getText().toString();
                eMail = inputEmail.getText().toString();
                dataInsert(name, telNo, eMail);
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void dataInsert(String name, String tel_no, String e_mail) {
        new Thread() {
            public void run() {
                try {
                    URL setURL = new URL("Http://10.0.2.2/insert03.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection) setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(name).append("/").append(tel_no).append("/").append(e_mail).append("/");
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
                    outStream.write(buffer.toString());
                    outStream.flush();
                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
                    final BufferedReader reader = new BufferedReader(tmp);
                    while (reader.readLine() != null) {
                        System.out.println(reader.readLine());
                    }
                }catch(Exception e){
                    Log.e("dataInsert()", "지정 에러 발생", e);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "입력실패", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();
    }
}