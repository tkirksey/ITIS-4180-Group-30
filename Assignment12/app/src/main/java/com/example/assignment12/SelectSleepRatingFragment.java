package com.example.assignment12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.assignment12.databinding.FragmentSelectSleepRatingBinding;

public class SelectSleepRatingFragment extends Fragment {

    String rating = "";
    public SelectSleepRatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectSleepRatingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectSleepRatingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
//                String rating = "";
                if (checkedId == R.id.rbExcellent) {
                    rating = "Excellent(5)";
                } else if (checkedId == R.id.rbVeryGood) {
                    rating = "Very Good(4)";
                } else if (checkedId == R.id.rbGood) {
                    rating = "Good(3)";
                } else if (checkedId == R.id.rbFair) {
                    rating = "Fair(2)";
                } else if (checkedId == R.id.rbPoor) {
                    rating = "Poor(1)";
                }

            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSelectedRating(rating);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelRating();
            }
        });


    }

    SelectSleepRatingFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectSleepRatingFragmentListener) context;
    }

    interface SelectSleepRatingFragmentListener{
        void sendSelectedRating(String rating);
        void cancelRating();


    }
}