package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView id, tel, name, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (TextView) findViewById(R.id.id);
        tel = (TextView) findViewById(R.id.telNo);
        name = (TextView) findViewById(R.id.eMail);
        email = (TextView) findViewById(R.id.ema)
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<void, void, void>{

        @Override
        protected Result doInBackground(Params... params) {
            return null;
        }
    }
    }
}