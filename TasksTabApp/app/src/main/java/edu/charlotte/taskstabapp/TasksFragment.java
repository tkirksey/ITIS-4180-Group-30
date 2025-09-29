package edu.charlotte.taskstabapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.charlotte.taskstabapp.databinding.FragmentTasksBinding;
import edu.charlotte.taskstabapp.models.Task;
import kotlinx.coroutines.scheduling.TaskContext;

public class TasksFragment extends Fragment {

    private ArrayList<Task> mTasks;
    private int currentIndex = 0;

    TextView textViewTasksCount, textViewTaskName, textViewTaskDate, textViewTaskPriority, textViewTaskOutOf;
    ImageView imageViewPrevious, imageViewNext;
    CardView cardViewTask;

    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Log.d("debug", "TasksFragment.onViewCreated(): I have " + mTasks.size() + " tasks!");

        Collections.sort(mTasks);

        FragmentTasksBinding binding = FragmentTasksBinding.bind(view);

        textViewTaskOutOf = binding.textViewTaskOutOf;
        textViewTaskName = binding.textViewTaskName;
        textViewTaskDate = binding.textViewTaskDate;
        textViewTasksCount = binding.textViewTasksCount;
        textViewTaskPriority = binding.textViewTaskPriority;

        imageViewNext = binding.imageViewNext;
        imageViewPrevious = binding.imageViewPrevious;

        cardViewTask = binding.cardViewTask;

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex += 1;

                if(currentIndex == mTasks.size()){
                    currentIndex = 0;
                }

                updateScreen();

            }
        });

        imageViewPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex -= 1;

                if(currentIndex == -1){
                    currentIndex = mTasks.size() - 1;
                }

                updateScreen();

            }
        });

        updateScreen();

    }

    public void sendTasks(ArrayList<Task> tasks){
        this.mTasks = tasks;
    }

    private void updateScreen(){

        // not strictly necessary after the first run
        // due to not having a delete button
        textViewTasksCount.setText("This category has " + mTasks.size() + " tasks");

        if(mTasks.isEmpty()){
            cardViewTask.setAlpha(0.0f);
        } else {

            Task currentTask = mTasks.get(currentIndex);

            cardViewTask.setAlpha(1.0f);

            textViewTaskOutOf.setText("Task " + (currentIndex + 1) + " of " + mTasks.size());
            textViewTaskName.setText(currentTask.getTitle());
            textViewTaskPriority.setText(currentTask.getPriority());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentTask.getDate().getTime());
            String formattedDate = "";
            formattedDate += (calendar.get(Calendar.MONTH) + 1) + "/";
            formattedDate += calendar.get(Calendar.DAY_OF_MONTH) + "/";
            formattedDate += calendar.get(Calendar.YEAR);

            textViewTaskDate.setText(formattedDate);

        }

    }

}