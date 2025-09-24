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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.charlotte.taskstabapp.databinding.FragmentTasksBinding;
import edu.charlotte.taskstabapp.models.Task;
import kotlinx.coroutines.scheduling.TaskContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {

    public static final String PARAM_TASK_LIST = "PARAM_TASK_LIST";

    private ArrayList<Task> mTasks;
    private int currentIndex = 0;

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance(ArrayList<Task> tasks) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_TASK_LIST, tasks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTasks = (ArrayList<Task>) getArguments().getSerializable(PARAM_TASK_LIST);
            Collections.sort(mTasks);
        }
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

        

    }

}