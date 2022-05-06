package com.example.temp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void setInsert(User user);

    @Update
    void setUpdate(User user);

    @Delete
    void setDelete(User user);

    @Query("select * from user")
    List<User> getUserAll();
}
