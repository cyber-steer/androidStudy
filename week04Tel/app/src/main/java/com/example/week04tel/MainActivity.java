package com.example.week04tel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String, String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        HashMap<String, String> item01 = new HashMap<>();
        item01.put("key01","title01");
        item01.put("key02","content01");
        list.add(item01);

        HashMap<String, String> item02 = new HashMap<>();
        item02.put("key01","title02");
        item02.put("key02","content02");
        list.add(item02);

        listView = (ListView) findViewById(R.id.list);
        String[] from = {"key01","key02"};
        int[] to = new int[] {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, from, to);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}