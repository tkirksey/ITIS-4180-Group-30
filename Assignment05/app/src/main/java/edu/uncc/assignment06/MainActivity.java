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
        CreateTaskFragment.CreateTaskListener,
        SelectTaskDateFragment.SelectTaskDateFragmentListener
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
        tasks.add(new Task("Do Homework", "9/23/2025", "High"));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TasksFragment.newInstance(this.tasks), "task-menu")
                .commit();
    }

    @Override
    public void gotoCreateTask() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateTaskFragment(), "create-task")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBackToCreateTask(String formattedDate) {

        CreateTaskFragment fragement = (CreateTaskFragment) getSupportFragmentManager().findFragmentByTag("create-task");

        if(fragement != null){

            if(formattedDate != null){

                fragement.setDate(formattedDate);

            }

            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void goBackToTaskMenu(Task task) {

        TasksFragment fragement = (TasksFragment) getSupportFragmentManager().findFragmentByTag("task-menu");

        if(fragement != null){

            if(task != null){
                fragement.addTask(task);
            }

            getSupportFragmentManager().popBackStack();

        }

    }

    @Override
    public void gotoSetDate() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectTaskDateFragment())
                .addToBackStack(null)
                .commit();
    }
}