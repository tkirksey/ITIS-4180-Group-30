package edu.charlotte.assignment10.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import edu.charlotte.assignment10.HorizontalImageAdapter;
import edu.charlotte.assignment10.HorizontalTextAdapter;
import edu.charlotte.assignment10.R;
import edu.charlotte.assignment10.databinding.FragmentFilterBinding;
import edu.charlotte.assignment10.models.Data;
import edu.charlotte.assignment10.models.Mood;
import edu.charlotte.assignment10.models.User;

public class FilterFragment extends Fragment {
    public FilterFragment() {
        // Required empty public constructor
    }

    ArrayList<User> mUsers = new ArrayList<>();

    FragmentFilterBinding binding;

    FilterListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUsers = mListener.getAllUsers();

        binding.buttonClearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendFilters(null, null, null);
            }
        });

        binding.recyclerViewName.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewAge.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewFeeling.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        HorizontalTextAdapter nameAdapter = new HorizontalTextAdapter(getAllInitials(), new HorizontalTextAdapter.HorizontalTextListener() {
            @Override
            public void onTextSelected(String text) {
                mListener.sendFilters(text, null, null);
            }
        });

        HorizontalTextAdapter ageAdapter = new HorizontalTextAdapter(getAllAgeGroups(), new HorizontalTextAdapter.HorizontalTextListener() {
            @Override
            public void onTextSelected(String text) {
                mListener.sendFilters(null, text, null);
            }
        });

        HorizontalImageAdapter moodAdapter = new HorizontalImageAdapter(getAllMoods(), new HorizontalImageAdapter.MoodListener() {
            @Override
            public void onMoodSelect(Mood mood) {
                mListener.sendFilters(null, null, mood);
            }
        });

        binding.recyclerViewName.setAdapter(nameAdapter);
        binding.recyclerViewAge.setAdapter(ageAdapter);
        binding.recyclerViewFeeling.setAdapter(moodAdapter);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (FilterListener) context;
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
        void sendFilters(String firstInitial, String ageGroup, Mood mood);
        ArrayList<User> getAllUsers();
    }

    private ArrayList<String> getAllInitials(){

        Set<String> firstInitials = new HashSet<>();

        for(User user : mUsers){
            firstInitials.add(String.valueOf(user.getName().charAt(0)));
        }

        ArrayList<String> listOfFirstInitials = new ArrayList<String>(firstInitials);

        Collections.sort(listOfFirstInitials, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return listOfFirstInitials;

    }

    private ArrayList<String> getAllAgeGroups(){

        Set<String> ageGroups = new HashSet<>();

        ArrayList<String> dataAgeGroups = new ArrayList<>(Arrays.asList(Data.getAgeGroups()));

        for(User user : mUsers){

            String ageGroup = user.getAgeGroup();

            ageGroups.add(Data.getAgeFilters()[dataAgeGroups.indexOf(ageGroup)]);

        }

        ArrayList<String> returnList = new ArrayList<>(ageGroups);

        Collections.sort(returnList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // puts under 12 first
        if(returnList.contains("Under 12")){

            int indx = returnList.indexOf("Under 12");

            returnList.remove(indx);

            returnList.add(0, "Under 12");

        }

        // puts over 75 last
        if(returnList.contains("Over 75")){

            int indx = returnList.indexOf("Over 75");

            returnList.remove(indx);

            returnList.add("Over 75");

        }

        return returnList;

    }

    private ArrayList<Mood> getAllMoods(){

        Set<Mood> moods = new HashSet<>();

        for(User user : mUsers){

            moods.add(user.getMood());

        }

        ArrayList<Mood> listMoods = new ArrayList<>(moods);

        return listMoods;

    }

}