package com.example.assignment12;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements AddLogFragment.AddLogFragmentListener,
        MainFragment.MainFragmentListener,
        SelectSleepRatingFragment.SelectSleepRatingFragmentListener,
        SelectSleepAmountFragment.SelectSleepAmountFragmentListener,
        SelectDateFragment.SelectDateFragmentListener,
        SelectExerciseTimeFragment.ExerciseTimeListener
{

    LogDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(this, LogDatabase.class, "log.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new MainFragment())
                .commit();
    }

    @Override
    public void cancelLog() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendToMain(Log log) {
        db.logDao().insertAll(log);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new MainFragment())
                .commit();
    }


    @Override
    public void goToSelectDate() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectDateFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectSleepAmount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectSleepAmountFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void goToSelectRating() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectSleepRatingFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectExerciseTime() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectExerciseTimeFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void goToAddLog() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new AddLogFragment(), "add-log-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public LogDatabase getDb() {
        return db;
    }

    @Override
    public void gotoVisualize() {

    }

    @Override
    public void sendSelectedRating(String rating) {
        AddLogFragment fragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("add-log-fragment");
        if (fragment != null) {
            fragment.goToSelectRating(rating);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelRating() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelSleepAmount() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSleepAmount(float amount) {
        AddLogFragment fragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("add-log-fragment");
        fragment.setSleepAmount(amount);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDateSelected(Date date) {
        AddLogFragment fragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("add-log-fragment");
        if (fragment != null) {
            fragment.goToSelectDate(date);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelSelectDate() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onCancel() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onExerciseTimeSelect(float amount) {
        AddLogFragment fragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("add-log-fragment");
        fragment.setExerciseAmount(amount);
        getSupportFragmentManager().popBackStack();
    }
}