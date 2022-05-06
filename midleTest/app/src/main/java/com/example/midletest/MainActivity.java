package com.example.midletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] count = new int[9];
        int[] imgId = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
        int[] ivId={R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};
        String[] name = {"pic 1","pic 2","pic 3","pic 4","pic 5","pic 6","pic 7","pic 8","pic 9"};

        for(int i=0;i<9;i++){
            int index = i;

            ImageView imageView = (ImageView) findViewById(ivId[i]);
            imageView.setImageResource(imgId[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count[index]++;
                    Toast.makeText(MainActivity.this, (index+1)+"번째 그림의 투표 수 : "+count[index], Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button buttonEnd = (Button) findViewById(R.id.btnEnd);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Vote.class);
                intent.putExtra("count",count);
                intent.putExtra("imgId",imgId);
                intent.putExtra("name",name);

                startActivity(intent);
            }
        });

        Button buttonCal = (Button) findViewById(R.id.btnCal);
        buttonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cal.class);
                startActivity(intent);
            }
        });
    }
}