package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout linearLayout = findViewById(R.id.linear);

        EditText textView = new EditText(this);
        textView.setText("test");
        textView.setWidth(100);

        TextView textView1 = new TextView(this);
        textView1.setText("abc");

        linearLayout.addView(textView);
        linearLayout.addView(textView1);
    }
}