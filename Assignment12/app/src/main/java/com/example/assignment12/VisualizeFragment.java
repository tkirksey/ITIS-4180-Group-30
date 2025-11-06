package com.example.assignment12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment12.databinding.FragmentVisualizeBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class VisualizeFragment extends Fragment {

    LogDatabase db;

    VisualizeListener mListener;


    public VisualizeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = mListener.getDb();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualize, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentVisualizeBinding binding = FragmentVisualizeBinding.bind(view);

        ArrayList<Log> mLogs = new ArrayList<>();
        mLogs.addAll(db.logDao().getAll());

        GraphView sleepHrs = binding.graphSleepHrs;
        GraphView sleepRating = binding.graphSleepRating;
        GraphView exerciseHrs = binding.graphExerciseHrs;
        GraphView weight = binding.graphWeight;

        ArrayList<DataPoint> sleepHrsData = new ArrayList<>();
        ArrayList<DataPoint> sleepRatingData = new ArrayList<>();
        ArrayList<DataPoint> exerciseHrsData = new ArrayList<>();
        ArrayList<DataPoint> weightData = new ArrayList<>();

        for(Log log : mLogs){

            sleepHrsData.add(new DataPoint(log.date, log.sleepHours));
            exerciseHrsData.add(new DataPoint(log.date, log.exerciseHours));
            weightData.add(new DataPoint(log.date, log.weight));
            sleepRatingData.add(new DataPoint(log.date, DataService.ratings.indexOf(log.sleepRating) + 1));

        }

        LineGraphSeries<DataPoint> sleepHrsLGS = new LineGraphSeries<>(sleepHrsData.toArray(new DataPoint[1]));
        LineGraphSeries<DataPoint> sleepRatLGS = new LineGraphSeries<>(sleepRatingData.toArray(new DataPoint[1]));
        LineGraphSeries<DataPoint> exerciseHrsLGS = new LineGraphSeries<>(exerciseHrsData.toArray(new DataPoint[1]));
        LineGraphSeries<DataPoint> weightLGS = new LineGraphSeries<>(weightData.toArray(new DataPoint[1]));

        sleepHrs.addSeries(sleepHrsLGS);
        sleepRating.addSeries(sleepRatLGS);
        exerciseHrs.addSeries(exerciseHrsLGS);
        weight.addSeries(weightLGS);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (VisualizeListener) context;
    }

    public interface VisualizeListener {
        LogDatabase getDb();
    }

}