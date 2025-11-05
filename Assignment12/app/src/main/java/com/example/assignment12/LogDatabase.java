package com.example.assignment12;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities ={Log.class}, version = 1)
public abstract class LogDatabase extends RoomDatabase {
    public abstract LogDao logDao();

}
