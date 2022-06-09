package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Insert extends AppCompatActivity {
    EditText etTitle, etGenre, etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etContent = findViewById(R.id.etContent);

        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String content = etContent.getText().toString();
                String sql = title+"_"+genre+"_"+content;
                dataInsert(sql);

                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("title",title);
                intent.putExtra("content",content);
                intent.putExtra("genre",genre);
                startActivity(intent);
            }
        });
    }

    public void dataInsert(String sql) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("Http://10.0.2.2/movieInsert.php/");
                    HttpURLConnection http = (HttpURLConnection)url.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(sql);

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