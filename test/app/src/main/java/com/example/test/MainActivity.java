package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {"그림1","그림2","그림3","그림4","그림5","그림6","그림7","그림8","그림9"};
        ImageView[] imageViews = new ImageView[9];
        int[] imgId={R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};
        int[] count = new int[9];

        for(int i=0;i<9;i++){
            int index = i;
            count[i] = 0;
            imageViews[i] = (ImageView) findViewById(imgId[i]);
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count[index]++;
                }
            });
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Result.class);
                intent.putExtra("count",count);
                intent.putExtra("name",names);
                startActivity(intent);
            }
        });
    }
}