package com.example.assignment4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment4.databinding.FragmentDisplayUserBinding;

public class DisplayUserFragment extends Fragment {

    private static final String ARG_PARAM_PROFILE = "ARG_PARAM_PROFILE";

    private User mUser;

    public DisplayUserFragment() {
        // Required empty public constructor
    }

    public static DisplayUserFragment newInstance(User user){
        DisplayUserFragment fragment = new DisplayUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PROFILE, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_PARAM_PROFILE);
        }
    }

    FragmentDisplayUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDisplayUserBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvNamePr.setText(mUser.getName());
        binding.tvEmailPr.setText(mUser.getEmail());
        binding.tvRolePr.setText(mUser.getRole());
        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendUser(mUser);
                mListener.gotoUpdateUser();
            }
        });
    }

    DisplayUserFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DisplayUserFragmentListener) context;
    }

    interface DisplayUserFragmentListener{
        void gotoUpdateUser();
        void sendUser(User usr);
    }

    public void setUser(User usr){
        this.mUser = usr;
    }

}