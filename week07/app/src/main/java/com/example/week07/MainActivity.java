package com.example.week07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText name, telNo, eMail;
    String sname, scall,smail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.txtName);
        telNo = (EditText) findViewById(R.id.txtTelNo);
        eMail = (EditText) findViewById(R.id.txtMail);
        sname = name.getText().toString();
        scall = telNo.getText().toString();
        smail = eMail.getText().toString();

        Button input = (Button) findViewById(R.id.btnInput);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataInsert();
            }
        });
    }
    private void dataInsert() {
        new Thread() {
            public void run() {
                try {
                    URL setURL = new URL("Http://127.0.0.1/insert.php/");
                    HttpURLConnection http;
                    http = (HttpURLConnection) setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(sname).append("/").append(scall).append("/").append(smail).append("/");
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    outStream.write(buffer.toString());
                    outStream.flush();
                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    final BufferedReader redear = new BufferedReader(tmp);
                    while(redear.readLine() != null)
                    {
                        System.out.println(redear.readLine());
                    }
                } catch (Exception e) {
                    Log.e("", "", e);
                }
            }
        }.start();
    }
}