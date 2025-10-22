package edu.charlotte.assignment10.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.charlotte.assignment10.R;
import edu.charlotte.assignment10.databinding.FragmentFilterBinding;
import edu.charlotte.assignment10.models.Mood;

public class FilterFragment extends Fragment {
    public FilterFragment() {
        // Required empty public constructor
    }

    FragmentFilterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public interface FilterListener {
        /**
         *
         * Only set one of these with the filter value you would like to filter by
         *
         * If you set more than one, all the filter will be applied.
         *
         * If you set all to null, they filters will be removed.
         *
         */
        void sendFilters(Character firstInitial, String ageGroup, Mood mood);
    }

}