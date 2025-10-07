package com.example.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment07.databinding.FragmentBookSelectBinding;

import java.util.ArrayList;

public class BookSelectFragment extends Fragment {

    private static final String KEY_BOOKS = "KEY_BOOKS";

    ArrayList<Book> mBooks;

    public BookSelectFragment() {
        // Required empty public constructor
    }

    public static BookSelectFragment newInstance(ArrayList<Book> books) {
        BookSelectFragment fragment = new BookSelectFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_BOOKS, books);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(KEY_BOOKS) != null) {
            mBooks = (ArrayList<Book>) getArguments().getSerializable(KEY_BOOKS);
        } else {
            mBooks = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentBookSelectBinding binding = FragmentBookSelectBinding.bind(view);

        binding.buttonBookSelectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBack();
            }
        });

        // set up list view with the books provided
        // in 'mBooks' and handle the click interaction

    }

    BookSelectListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (BookSelectListener) context;
    }

    public interface BookSelectListener {
        void gotoBook(Book book);
        void goBack();
    }

}