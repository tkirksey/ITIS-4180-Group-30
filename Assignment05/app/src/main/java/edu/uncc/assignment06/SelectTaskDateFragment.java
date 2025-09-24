package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

import edu.uncc.assignment06.databinding.FragmentSelectTaskDateBinding;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link SelectTaskDateFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class SelectTaskDateFragment extends Fragment {

    private String formattedDate = "";

    public SelectTaskDateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectTaskDateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectTaskDateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Task Date");

        binding.calendarView.setMaxDate(System.currentTimeMillis());

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                formattedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(formattedDate.equals("")){
                    Toast.makeText(getActivity(), "Please select a date", Toast.LENGTH_SHORT).show();
                    return;
                }

                mListener.goBackToCreateTask(formattedDate);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBackToCreateTask(null);
            }
        });
    }

    SelectTaskDateFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectTaskDateFragmentListener) context;
    }

    interface SelectTaskDateFragmentListener{
        void goBackToCreateTask(String formattedDate);
    }



}