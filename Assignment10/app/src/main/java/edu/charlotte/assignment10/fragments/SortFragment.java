package edu.charlotte.assignment10.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.charlotte.assignment10.databinding.FragmentSortBinding;

public class SortFragment extends Fragment {
    FragmentSortBinding binding;


    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSortBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sort");

        binding.imageViewNameAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(true, null, null);
            }
        });

        binding.imageViewNameDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(false, null, null);
            }
        });

        binding.imageViewAgeAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(null, true, null);
            }
        });

        binding.imageViewAgeDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(null, false, null);
            }
        });

        binding.imageViewFeelingAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(null, null, true);
            }
        });

        binding.imageViewFeelingDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(null, null, false);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSorts(null, null, null);

            }
        });


    }


    SortListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SortListener) context;
    }

    public interface SortListener {
        /***
         *
         * Only set one filter at a time, leave the others as null
         *
         * If you set more than one as true, the last of the ones set in the
         * arguments will be applied.
         *
         * If you set all to be null, the sort will be removed.
         *
         * ex: 'sendSorts(true, null, null)' for sorting names ascending
         *
         * */
        void sendSorts(Boolean isNameAsc, Boolean isAgeGroupAsc, Boolean isFeelingAsc);
    }

}