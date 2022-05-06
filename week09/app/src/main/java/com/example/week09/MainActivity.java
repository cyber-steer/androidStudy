package com.example.week09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText name, tel, email;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        name = findViewById(R.id.editName);
        tel = findViewById(R.id.editTel);
        email = findViewById(R.id.editEmail);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "card").allowMainThreadQueries().build();
        textView.setText(db.cardDao().getAll().toString());

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.cardDao().insert(new Card(
                        name.getText().toString(),
                        tel.getText().toString(),
                        email.getText().toString()
                ));
                textView.setText(db.cardDao().getAll().toString());
            }
        });

    }
}