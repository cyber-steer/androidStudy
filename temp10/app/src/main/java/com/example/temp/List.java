package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class List extends AppCompatActivity {
    EditText etSearch;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        handler = new Handler();
        etSearch = findViewById(R.id.etSearch);

        dataSelect("");

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = etSearch.getText().toString();
                dataSelect(key);
            }
        });
        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Insert.class));
            }
        });
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void dataSelect(String key) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("Http://10.0.2.2/movieSelect.php/");
                    HttpURLConnection http = (HttpURLConnection)url.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(key);

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
                    final String[] sResult = resultData.split("/");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Adapter adapter = new Adapter();
                            ListView listView = findViewById(R.id.listView);
                            listView.setAdapter(adapter);
                            for(int i=0;i<sResult.length;) {
                                Item item = new Item();
                                item.setTitle(sResult[i++]);
                                item.setContent(sResult[i++]);
                                item.setGenre(sResult[i++]);
                                adapter.addItem(item);
                            }
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("error : "+e);
                    Log.e("","Error",e);
                }
            }
        }.start();
    }
}

