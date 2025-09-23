package edu.uncc.assignment06;

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



    }

    public interface TasksListener {
        void gotoCreateTask();
    }

}