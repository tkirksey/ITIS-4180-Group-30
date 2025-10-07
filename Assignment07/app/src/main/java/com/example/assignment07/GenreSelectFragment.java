package com.example.assignment07;

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

import com.example.assignment07.databinding.FragmentGenreSelectBinding;

import java.util.ArrayList;

public class GenreSelectFragment extends Fragment {

    private static final String KEY_GENRELIST = "KEY_GENRELIST";

    private ArrayList<String> mGenres;

    public GenreSelectFragment() {
        // Required empty public constructor
    }

    public static GenreSelectFragment newInstance(ArrayList<String> genres) {
        GenreSelectFragment fragment = new GenreSelectFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_GENRELIST, genres);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(KEY_GENRELIST) != null) {
            mGenres = (ArrayList<String>) getArguments().getSerializable(KEY_GENRELIST);
        } else {
            mGenres = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentGenreSelectBinding binding = FragmentGenreSelectBinding.bind(view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mGenres);
        binding.listViewGenre.setAdapter(adapter);

        binding.listViewGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selection = mGenres.get(position);

                mListener.gotoBookSelect(selection);

            }
        });
    }

    GenreSelectListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (GenreSelectListener) context;
    }

    public interface GenreSelectListener {
        void gotoBookSelect(String genre);
    }

}