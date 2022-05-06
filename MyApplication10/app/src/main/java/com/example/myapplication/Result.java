package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        int[] imgSorce = intent.getIntArrayExtra("imgSorce");
        int[] count = intent.getIntArrayExtra("count");
        String[] imgName = intent.getStringArrayExtra("imgName");

        ListView listView = (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

        for (int i = 0; i < 9; i++){
            adapter.addItem(imgSorce[i], count[i], imgName[i]);
        }
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}