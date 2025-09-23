package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.uncc.assignment06.databinding.FragmentTasksBinding;

public class TasksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TASKS = "ARRAYLIST<TASKS>_TASKS";

    private ArrayList<Task> tasks;
    private int arrayIndex = 0;

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance(ArrayList<Task> tasks) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASKS, tasks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tasks = (ArrayList<Task>) getArguments().getSerializable(TASKS);
        } else {
            this.tasks = new ArrayList<Task>();
        }
    }

    FragmentTasksBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Tasks");

        binding.buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoCreateTask();
                refresh();
            }
        });

        refresh();

    }

    public interface TasksListener {
        void gotoCreateTask();
    }

    TasksListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (TasksListener) context;
    }

    private void refresh(){

        binding.textViewTasksCount.setText("You have " + this.tasks.size() + " tasks");

        if(this.tasks.isEmpty()){
            binding.cardViewTask.setAlpha(0.0f);
        } else {
            binding.imageViewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrayIndex += 1;

                    if(arrayIndex == tasks.size()){
                        arrayIndex = 0;
                    }

                    refresh();
                }
            });

            binding.imageViewPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrayIndex -= 1;

                    if(arrayIndex == -1){
                        arrayIndex = tasks.size() - 1;
                    }

                    refresh();
                }
            });

            binding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tasks.remove(arrayIndex);
                    refresh();
                }
            });

            binding.textViewTaskOutOf.setText(("Task " + (arrayIndex + 1) + " of " + tasks.size()));
            binding.textViewTaskName.setText(tasks.get(arrayIndex).getName());
            binding.textViewTaskPriority.setText(tasks.get(arrayIndex).getPriority());
            binding.textViewTaskDate.setText(tasks.get(arrayIndex).getDate());

            binding.cardViewTask.setAlpha(1.0f);

        }

    }

}