package edu.charlotte.assignment10.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import edu.charlotte.assignment10.UserRecyclerAdapter;
import edu.charlotte.assignment10.databinding.FragmentUsersBinding;
import edu.charlotte.assignment10.models.Data;
import edu.charlotte.assignment10.models.Mood;
import edu.charlotte.assignment10.models.User;

public class UsersFragment extends Fragment {
    public UsersFragment() {
        // Required empty public constructor
    }

    FragmentUsersBinding binding;
    ArrayList<User> mUsers = new ArrayList<>();
    UserRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAll();
                adapter.notifyDataSetChanged();
            }
        });

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddNew();
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSortSelection();

            }
        });

        binding.imageViewFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoFilterSelection();
            }
        });

        mUsers = mListener.getAllUsers();

        RecyclerView recyclerView = binding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new UserRecyclerAdapter(mUsers, new UserRecyclerAdapter.UserRecyclerAdapterListener() {
            @Override
            public void deleteUser(User user) {
                mUsers.remove(user);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void sendSelected(User user) {
                mListener.gotoUserProfile(user);
            }
        });

        recyclerView.setAdapter(adapter);

    }

    UsersListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (UsersListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement UsersListener");
        }
    }

    public interface UsersListener{
        void clearAll();
        void gotoAddNew();
        ArrayList<User> getAllUsers();
        void gotoUserProfile(User user);
        void gotoSortSelection();
        void gotoFilterSelection();
    }


    public void sendSorts(Boolean isNameAsc, Boolean isAgeGroupAsc, Boolean isFeelingAsc){

        if(isNameAsc != null){

            if(isNameAsc.booleanValue()){
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

                binding.textViewSort.setText("Name (ASC)");

            } else {
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                });

                binding.textViewSort.setText("Name (DESC)");
            }

        }

        if(isAgeGroupAsc != null){
            if(isAgeGroupAsc.booleanValue()){
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {

                        ArrayList<String> ageGroups = new ArrayList<>(Arrays.asList(Data.getAgeGroups()));

                        int o1Position = ageGroups.indexOf(o1.getAgeGroup());
                        int o2Position = ageGroups.indexOf(o2.getAgeGroup());

                        if(o1Position > o2Position){
                            return 1;
                        } else if(o1Position == o2Position){
                            return 0;
                        } else {
                            return -1;
                        }

                    }
                });

                binding.textViewSort.setText("Age Group (ASC)");

            } else {
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {

                        ArrayList<String> ageGroups = new ArrayList<>(Arrays.asList(Data.getAgeGroups()));

                        int o1Position = ageGroups.indexOf(o1.getAgeGroup());
                        int o2Position = ageGroups.indexOf(o2.getAgeGroup());

                        if(o1Position > o2Position){
                            return -1;
                        } else if(o1Position == o2Position){
                            return 0;
                        } else {
                            return 1;
                        }

                    }
                });

                binding.textViewSort.setText("Age Group (DESC)");
            }

        }

        if(isFeelingAsc != null){

            if(isFeelingAsc.booleanValue()){
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {

                        ArrayList<Mood> moods = new ArrayList<>(Arrays.asList(Data.getMoods()));

                        int o1Position = moods.indexOf(o1.getMood());
                        int o2Position = moods.indexOf(o2.getMood());

                        if(o1Position > o2Position){
                            return 1;
                        } else if(o1Position == o2Position){
                            return 0;
                        } else {
                            return -1;
                        }

                    }
                });

                binding.textViewSort.setText("Moods (ASC)");

            } else {
                Collections.sort(mUsers, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {

                        ArrayList<Mood> moods = new ArrayList<>(Arrays.asList(Data.getMoods()));

                        int o1Position = moods.indexOf(o1.getMood());
                        int o2Position = moods.indexOf(o2.getMood());

                        if(o1Position > o2Position){
                            return -1;
                        } else if(o1Position == o2Position){
                            return 0;
                        } else {
                            return 1;
                        }

                    }
                });

                binding.textViewSort.setText("Moods (DESC)");

            }

        }

        if(isNameAsc == null && isAgeGroupAsc == null && isFeelingAsc == null){
            mUsers = mListener.getAllUsers();
        }

        adapter.notifyDataSetChanged();

    }

    public void sendFilters(Character firstInitial, String ageGroup, Mood mood){

        if(firstInitial != null){

            ArrayList<User> temp = new ArrayList<>();

            for(User user : mUsers){
                if(user.getName().charAt(0) == firstInitial){
                    temp.add(user);
                }
            }

            mUsers = temp;

        }

        if(ageGroup != null){

            ArrayList<User> temp = new ArrayList<>();

            for(User user : mUsers){
                if(user.getAgeGroup().equals(ageGroup)){
                    temp.add(user);
                }
            }

            mUsers = temp;

        }

        if(mood != null){

            ArrayList<User> temp = new ArrayList<>();

            for(User user : mUsers){
                if(user.getMood().equals(mood)){
                    temp.add(user);
                }
            }

            mUsers = temp;

        }

        if(firstInitial == null && ageGroup == null && mood == null){
            mUsers = mListener.getAllUsers();
        }

        adapter.notifyDataSetChanged();

    }

}