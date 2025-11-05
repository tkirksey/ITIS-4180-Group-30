package com.example.assignment12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.assignment12.databinding.FragmentAddLogBinding;
import com.example.assignment12.databinding.FragmentMainBinding;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private LogDatabase db;
    boolean dbModified = true;

    LogAdapter adapter;

    ListView listView;

    ArrayList<Log> mLogs;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = mListener.getDb();
    }

    FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = binding.listView;
        mLogs = (ArrayList<Log>) db.logDao().getAll();

        adapter = new LogAdapter(getActivity(), mLogs, new LogAdapter.LogListener() {
            @Override
            public void onDelete(Log log) {
                db.logDao().deleteAll(log);
                mLogs.clear();
                mLogs.addAll(db.logDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);

        binding.buttonAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToAddLog();
            }
        });

        binding.buttonVisualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        mLogs.clear();
        mLogs.addAll(db.logDao().getAll());
        adapter.notifyDataSetChanged();

    }

    MainFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MainFragmentListener) context;
    }

    interface MainFragmentListener{
        void goToAddLog();
        LogDatabase getDb();
        void gotoVisualize();
    }
}