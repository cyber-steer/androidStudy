package com.example.week05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listView = (ListView) findViewById(R.id.lvMoviePoster);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie1),"movie1","content1");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie2),"movie2","content2");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie3),"movie3","content3");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie4),"movie4","content4");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie5),"movie5","content5");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie6),"movie6","content6");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.movie7),"movie7","content7");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(i);

                System.out.println("============================"+item);
                String titleStr = item.getTitle();
                //String descStr = item.getDescription();
                //Drawable iconDrawble = item.getIcon();

                Toast.makeText(MainActivity.this, titleStr, Toast.LENGTH_SHORT).show();
            }
        });
    }
}