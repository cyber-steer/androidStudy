package com.example.midletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Intent intent = getIntent();
        int[] count = intent.getIntArrayExtra("count");
        int[] imgId = intent.getIntArrayExtra("imgId");
        String[] name = intent.getStringArrayExtra("name");

        ListView listView = (ListView) findViewById(R.id.listView);
        Adapter adapter = new Adapter();
        listView.setAdapter(adapter);

        for(int i=0;i<9;i++){
            adapter.addItem(imgId[i], count[i], name[i]);
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