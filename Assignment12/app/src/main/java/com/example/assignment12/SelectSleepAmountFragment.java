package com.example.assignment12;

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
import android.widget.ListView;

import com.example.assignment12.databinding.FragmentSelectSleepAmountBinding;

import java.util.ArrayList;

public class SelectSleepAmountFragment extends Fragment {

    ArrayList<String> sleepOptions = new ArrayList<>();
    ListView sleepAmounts;


    public SelectSleepAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sleepOptions.add("0.5 Hours");
        for(int i = 1; i < 15; i++){
            sleepOptions.add(i + " Hours");
            sleepOptions.add(i + ".5 Hours");
        }
        sleepOptions.add("15 Hours");
    }

    FragmentSelectSleepAmountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectSleepAmountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelSleepAmount();
            }
        });

        sleepAmounts = binding.listViewSleepAmount;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, sleepOptions);
        sleepAmounts.setAdapter(adapter);

        sleepAmounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                float sleepAmount = position / 2.0f + 0.5f;
                mListener.sendSleepAmount(sleepAmount);
            }
        });

    }

    SelectSleepAmountFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectSleepAmountFragmentListener) context;
    }

    interface SelectSleepAmountFragmentListener{
        void cancelSleepAmount();
        void sendSleepAmount(float amount);
    }
}