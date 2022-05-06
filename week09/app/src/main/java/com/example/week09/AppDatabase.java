package com.example.week09;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
}
