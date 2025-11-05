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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.assignment12.databinding.FragmentSelectExerciseTimeBinding;

import java.util.ArrayList;

public class SelectExerciseTimeFragment extends Fragment {

    ArrayList<String> exerciseOptions = new ArrayList<>();

    public SelectExerciseTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exerciseOptions.add("0.5 Hours");
        for(int i = 1; i < 15; i++){
            exerciseOptions.add(i + " Hours");
            exerciseOptions.add(i + ".5 Hours");
        }
        exerciseOptions.add("15 Hours");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_exercise_time, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentSelectExerciseTimeBinding binding = FragmentSelectExerciseTimeBinding.bind(view);

        binding.buttonExerciseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancel();
            }
        });

        ListView listView = binding.listViewExerciseAmount;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, exerciseOptions);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                float exerciseAmount = position / 2.0f + 0.5f;
                mListener.onExerciseTimeSelect(exerciseAmount);
            }
        });

    }



    ExerciseTimeListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ExerciseTimeListener) context;
    }

    public interface ExerciseTimeListener {
        void onCancel();
        void onExerciseTimeSelect(float amount);
    }

}