package edu.uncc.assignment06;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity
        extends
            AppCompatActivity
        implements
            TasksFragment.TasksListener,
            CreateTaskFragment.CreateTaskListener
{

    private ArrayList<Task> tasks = new ArrayList<Task>();

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

        tasks.add(new Task("Clean Room", "9/23/2025", "Low"));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TasksFragment.newInstance(this.tasks), "tasks-menu")
                .commit();
    }

    @Override
    public void gotoCreateTask() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateTaskFragment(), "create-task")
                .commit();
    }

    @Override
    public void gotoSelectDate() {
        // implement
    }

    @Override
    public void gotoTasks(Task task) {

        this.tasks.add(task);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TasksFragment.newInstance(this.tasks), "tasks-menu")
                .commit();
    }
}