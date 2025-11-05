package edu.charlotte.assignment11;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;

import edu.charlotte.assignment11.fragments.AddTaskFragment;
import edu.charlotte.assignment11.fragments.SelectCategoryFragment;
import edu.charlotte.assignment11.fragments.SelectPriorityFragment;
import edu.charlotte.assignment11.fragments.TaskDetailsFragment;
import edu.charlotte.assignment11.fragments.TasksFragment;
import edu.charlotte.assignment11.models.Data;
import edu.charlotte.assignment11.models.Priority;
import edu.charlotte.assignment11.models.Task;

public class MainActivity extends AppCompatActivity implements SelectPriorityFragment.SelectPriorityListener, SelectCategoryFragment.SelectCategoryListener,
        AddTaskFragment.AddTaskListener, TasksFragment.TasksListener, TaskDetailsFragment.TaskDetailsListener {

    AppDatabase db;

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

        db = Room.databaseBuilder(this, AppDatabase.class, "Tasks.db")
                .allowMainThreadQueries()
                .build();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new TasksFragment())
                .commit();
    }

    @Override
    public void onCategorySelected(String category) {
        AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("addTaskFragment");
        if (addTaskFragment != null) {
            addTaskFragment.setSelectedCategory(category);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onPrioritySelected(Priority priority) {
        AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("addTaskFragment");
        if (addTaskFragment != null) {
            addTaskFragment.setSelectedPriority(priority);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoSelectPriority() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectPriorityFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectCategory() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new SelectCategoryFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTaskAdded(Task task) {
        db.taskDao().insertAll(task);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onTaskDeleted(Task task) {
        db.taskDao().delete(task);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onCancelSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoAddTask() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new AddTaskFragment(), "addTaskFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoTaskDetails(Task task) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TaskDetailsFragment.newInstance(db.taskDao().getTask(task.id)))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) db.taskDao().getAll();
    }

    @Override
    public void clearAllTasks() {
        db.taskDao().deleteAll();
    }

    @Override
    public void deleteTask(Task task) {
        db.taskDao().delete(task);
    }

    @Override
    public ArrayList<Task> getSortedTasks(boolean isAsc) {
        if(isAsc){
            return (ArrayList<Task>) db.taskDao().getTasksSortedAsc();
        } else {
            return (ArrayList<Task>) db.taskDao().getTasksSortedDesc();
        }
    }


}