package com.example.assignment12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment12.databinding.FragmentAddLogBinding;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddLogFragment extends Fragment {

    String rating;
    Date date;

    public AddLogFragment() {
        // Required empty public constructor
    }

    Log mLog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddLogBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddLogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (date != null) {
            String formattedDate = DateFormat.getDateInstance().format(date);
            binding.textViewDateAndTime.setText(formattedDate);
        } else {
            binding.textViewDateAndTime.setText("");
        }

        binding.textViewSleepQuality.setText(rating);



        binding.buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectDate();
            }
        });

        binding.buttonSelectSleepAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectSleepAmount();
            }
        });

        binding.buttonSelectSleepQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectRating();
            }
        });

        binding.buttonSelectExerciseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectExerciseTime();
            }
        });


        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateNtime = binding.textViewDateAndTime.toString();
                String sleepAmount = binding.textViewSleepAmount.toString();
                String exerciseTime = binding.textViewExerciseTime.toString();
                String weight = binding.editTextWeight.toString();

                if (dateNtime.isEmpty()) {
                    Toast.makeText(getContext(), "Select a date", Toast.LENGTH_SHORT).show();
                } else if (sleepAmount.isEmpty()) {
                    Toast.makeText(getContext(), "Select a time amount", Toast.LENGTH_SHORT).show();
                } else if (exerciseTime.isEmpty()) {
                    Toast.makeText(getContext(), "Select a time amount", Toast.LENGTH_SHORT).show();
                } else if (weight.isEmpty()) {
                    Toast.makeText(getContext(), "Enter a weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelLog();
            }
        });



    }

    AddLogFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddLogFragmentListener) context;
    }

    public void goToSelectRating(String rating) {
        this.rating = rating;
    }

    public void goToSelectDate(Date date) {
        this.date = date;
    }


    interface AddLogFragmentListener{
        void cancelLog();
        void sendToMain(Log log);
        void goToSelectDate();
        void goToSelectSleepAmount();
        void goToSelectRating();
        void goToSelectExerciseTime();
    }
}