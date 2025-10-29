package edu.charlotte.trivia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.charlotte.trivia.databinding.FragmentStatsBinding;

public class StatsFragment extends Fragment {

    public static final String KEY_NUM_QUEST = "KEY_NUM_QUEST";
    public static final String KEY_FIRST_TRY = "KEY_FIRST_TRY";

    int mNumQuestions;
    int mFirstTry;

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance(int numQuestions, int firstTry) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_NUM_QUEST, numQuestions);
        args.putInt(KEY_FIRST_TRY, firstTry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNumQuestions = getArguments().getInt(KEY_NUM_QUEST);
            mFirstTry = getArguments().getInt(KEY_FIRST_TRY);
        } else {
            mNumQuestions = -1;
            mFirstTry = -1;
        }
        getActivity().setTitle("Stats");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentStatsBinding binding = FragmentStatsBinding.bind(view);

        binding.textViewResults.setText(mFirstTry + " out of " + mNumQuestions + " questions were answered correctly from the first attempt!");

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBack();
            }
        });

    }

    StatsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (StatsListener) context;
    }

    public interface StatsListener {
        void goBack();
    }

}