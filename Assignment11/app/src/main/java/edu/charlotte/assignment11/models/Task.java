package edu.charlotte.assignment11.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tasks")
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "priority_value")
    public int priority_value;

    @ColumnInfo(name = "priority_name")
    public String priority_name;

    public Task(){

    }

    public Task(String name, String category, Priority priority){

        this.name = name;
        this.category = category;
        this.priority_value = priority.getLevel();
        this.priority_name = priority.getName();

    }

}
