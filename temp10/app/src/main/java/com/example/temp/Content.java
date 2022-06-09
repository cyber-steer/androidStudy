package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Content extends AppCompatActivity {
    TextView tvTitle, tvGenre, tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String genre = intent.getStringExtra("genre");

        System.out.println(content);

        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvContent = findViewById(R.id.tvContent);

        tvTitle.setText(title);
        tvGenre.setText(genre);
        tvContent.setText(content);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataDelete(title);
                startActivity(new Intent(getApplicationContext(), List.class));
            }
        });
    }

    public void dataDelete(String title) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("Http://10.0.2.2/movieDelete.php/");
                    HttpURLConnection http = (HttpURLConnection)url.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(title);

                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(),"UTF-8");
                    outputStreamWriter.write(buffer.toString());
                    outputStreamWriter.flush();

                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();

                    String str;
                    while((str = reader.readLine())!=null) {
                        builder.append(str);
                        System.out.println(str);
                    }

                    String resultData = builder.toString();
                    System.out.println("통신 결과 : "+resultData);
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("error : "+e);
                    Log.e("","Error",e);
                }
            }
        }.start();
    }
}