package com.example.assignment12;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LogDao {
    @Query("SELECT * FROM logs")
    List<Log> getAll();

    @Query("SELECT * FROM logs WHERE id = :id limit 1")
    Log findById(long id);

    @Update
    void update(Log log);

    @Insert
    void insertAll(Log ... logs);

    @Delete
    void deleteAll(Log log);


}
