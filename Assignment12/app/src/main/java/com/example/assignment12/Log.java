package com.example.assignment12;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "logs")
public class Log {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public Long date;
    @ColumnInfo
    public float sleepHours;
    @ColumnInfo
    public String sleepRating;
    @ColumnInfo
    public float exerciseHours;
    @ColumnInfo
    public float weight;

    public Log(long id, Date date, float sleepHours, String sleepRating, float exerciseHours, float weight) {
        this.id = id;
        this.date = date.getTime();
        this.sleepHours = sleepHours;
        this.sleepRating = sleepRating;
        this.exerciseHours = exerciseHours;
        this.weight = weight;
    }

    public Log(Long date, float sleepHours, String sleepRating, float exerciseHours, float weight) {
        this.date = date;
        this.sleepHours = sleepHours;
        this.sleepRating = sleepRating;
        this.exerciseHours = exerciseHours;
        this.weight = weight;
    }

    public Log() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public float getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(float sleepHours) {
        this.sleepHours = sleepHours;
    }

    public String getSleepRating() {
        return sleepRating;
    }

    public void setSleepRating(String sleepRating) {
        this.sleepRating = sleepRating;
    }

    public float getExerciseHours() {
        return exerciseHours;
    }

    public void setExerciseHours(float exerciseHours) {
        this.exerciseHours = exerciseHours;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date=" + date +
                ", sleepHours=" + sleepHours +
                ", sleepRating='" + sleepRating + '\'' +
                ", exerciseHours=" + exerciseHours +
                ", weight=" + weight +
                '}';
    }
}
