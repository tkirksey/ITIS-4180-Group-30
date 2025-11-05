package edu.charlotte.assignment11.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import edu.charlotte.assignment11.R;
import edu.charlotte.assignment11.TaskRecyclerViewAdapter;
import edu.charlotte.assignment11.databinding.FragmentTasksBinding;
import edu.charlotte.assignment11.models.Task;

public class TasksFragment extends Fragment {
    public TasksFragment() {
        // Required empty public constructor
    }

    ArrayList<Task> mTasks = new ArrayList<>();

    FragmentTasksBinding binding;

    RecyclerView recyclerView;
    TaskRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTasks = mListener.getTasks();

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddTask();
                mTasks.clear();
                mTasks.addAll(mListener.getTasks());
                adapter.notifyDataSetChanged();
            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAllTasks();
                mTasks.clear();
                mTasks.addAll(mListener.getTasks());
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasks.clear();
                mTasks.addAll(mListener.getSortedTasks(true));
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasks.clear();
                mTasks.addAll(mListener.getSortedTasks(false));
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new TaskRecyclerViewAdapter(mTasks, new TaskRecyclerViewAdapter.TaskRecyclerViewListener() {
            @Override
            public void onClick(Task task) {
                mListener.gotoTaskDetails(task);
                mTasks.clear();
                mTasks.addAll(mListener.getTasks());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDelete(Task task) {
                mListener.deleteTask(task);
                mTasks.clear();
                mTasks.addAll(mListener.getTasks());
                adapter.notifyDataSetChanged();
            }
        });


        recyclerView.setAdapter(adapter);

    }

    TasksListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TasksListener) {
            mListener = (TasksListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement TasksListener");
        }
    }

    public interface TasksListener{
        void gotoAddTask();
        void gotoTaskDetails(Task task);
        ArrayList<Task> getTasks();
        void clearAllTasks();
        void deleteTask(Task task);
        ArrayList<Task> getSortedTasks(boolean isAsc);
    }
}