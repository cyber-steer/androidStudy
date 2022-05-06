package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "testdb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        mUserDao = database.userDao();
    }
}