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
import com.example.assignment07.databinding.FragmentBookViewBinding;

public class BookViewFragment extends Fragment {

    private static final String KEY_BOOK = "KEY_BOOK";

    private Book mBook;

    public BookViewFragment() {
        // Required empty public constructor
    }

    public static BookViewFragment newInstance(Book book) {
        BookViewFragment fragment = new BookViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(KEY_BOOK) != null) {
            mBook = (Book) getArguments().getSerializable(KEY_BOOK);
        } else {
            mBook = new Book("", "", -1, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentBookViewBinding binding = FragmentBookViewBinding.bind(view);

        binding.buttonBookViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBack();
            }
        });

        binding.textViewTitle.setText(mBook.getTitle());
        binding.textViewAuthor.setText(mBook.getAuthor());
        binding.textViewGenre.setText(mBook.getGenre());
        binding.textViewYear.setText(String.valueOf(mBook.getYear()));

    }

    BookViewListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (BookViewListener) context;
    }

    public interface BookViewListener {
        void goBack();
    }

}