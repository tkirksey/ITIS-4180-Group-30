package edu.charlotte.assignment10.fragments;

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
import android.widget.ListAdapter;

import edu.charlotte.assignment10.R;
import edu.charlotte.assignment10.databinding.FragmentSelectAgeGroupBinding;
import edu.charlotte.assignment10.databinding.FragmentSelectMoodBinding;
import edu.charlotte.assignment10.models.Data;

public class SelectAgeGroupFragment extends Fragment {
    String[] ageGroups = Data.getAgeGroups();

    public SelectAgeGroupFragment() {
        // Required empty public constructor
    }

    FragmentSelectAgeGroupBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectAgeGroupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Age Group");
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAgeGroupSelectionCancelled();
            }
        });

        binding.listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ageGroups));

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onAgeGroupSelected(ageGroups[position]);
            }
        });

    }

    SelectAgeGroupListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectAgeGroupListener) {
            mListener = (SelectAgeGroupListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectAgeGroupListener");
        }
    }

    public interface SelectAgeGroupListener {
        void onAgeGroupSelected(String ageGroup);
        void onAgeGroupSelectionCancelled();
    }


}