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

import edu.charlotte.assignment10.MoodArrayAdapter;
import edu.charlotte.assignment10.R;
import edu.charlotte.assignment10.databinding.FragmentSelectMoodBinding;
import edu.charlotte.assignment10.models.Data;
import edu.charlotte.assignment10.models.Mood;

public class SelectMoodFragment extends Fragment {
    public SelectMoodFragment() {
        // Required empty public constructor
    }

    FragmentSelectMoodBinding binding;
    private Mood[] mMoods = Data.getMoods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectMoodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Mood");
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMoodSelectionCancelled();
            }
        });

        binding.listView.setAdapter(new MoodArrayAdapter(getActivity(), mMoods));

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onMoodSelected(mMoods[position]);
            }
        });

    }

    SelectMoodListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectMoodListener) {
            mListener = (SelectMoodListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectMoodListener");
        }
    }

    public interface SelectMoodListener {
        void onMoodSelected(Mood mood);
        void onMoodSelectionCancelled();
    }
}