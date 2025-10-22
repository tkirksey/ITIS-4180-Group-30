package edu.charlotte.assignment10.fragments;

import android.os.Bundle;

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