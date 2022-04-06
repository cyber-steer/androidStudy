package com.example.week06gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int img[] = {
                R.drawable.canada,R.drawable.france,
                R.drawable.korea,R.drawable.mexico,
                R.drawable.poland,R.drawable.saudi_arabia
        };

        MyAdapter adapter = new MyAdapter(
                getApplicationContext(),
                R.layout.row,
                img
        );

        Gallery g= (Gallery) findViewById(R.id.gallery);
        g.setAdapter(adapter);

        final ImageView iv = (ImageView) findViewById(R.id.imageView2);
        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iv.setImageResource(img[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}