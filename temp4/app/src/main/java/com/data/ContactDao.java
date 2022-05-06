package com.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.model.Contact;

@Dao

public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Delete
    void delete(Contact... contacts);

    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    LiveData<Contact> getAll();
}
