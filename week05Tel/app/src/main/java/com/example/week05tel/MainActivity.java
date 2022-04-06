package com.example.week05tel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView;
        TelListAdapter adapter;
        adapter = new TelListAdapter();

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "아버지","01048972347");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "어머니","01044897234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "할아버지","01048897234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "할머니","01048697234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "증조할아버지","01047897234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "증조할머니","01048972234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "외할아버지","01048597234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.tell), "외할머니","01089728934");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TelList telList = (TelList) adapterView.getItemAtPosition(i);

                String tel = telList.getTel();
                Toast.makeText(MainActivity.this, tel, Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("tel:"+tel);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
    }
}