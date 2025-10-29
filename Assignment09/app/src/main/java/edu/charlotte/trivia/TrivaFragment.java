package edu.charlotte.trivia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.charlotte.trivia.databinding.FragmentTrivaBinding;
import edu.charlotte.trivia.models.Trivia;

public class TrivaFragment extends Fragment {

    public static final String KEY_TRIVIA_LIST = "KEY_TRIVIA_LIST";

    ArrayList<Trivia> mTrivia;

    public TrivaFragment() {
        // Required empty public constructor
    }

    public static TrivaFragment newInstance(ArrayList<Trivia> trivia) {
        TrivaFragment fragment = new TrivaFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_TRIVIA_LIST, trivia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(KEY_TRIVIA_LIST) != null) {
            mTrivia = (ArrayList<Trivia>) getArguments().getSerializable(KEY_TRIVIA_LIST);
        } else {
            mTrivia = new ArrayList<>();
        }
        getActivity().setTitle("Select Trivia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_triva, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentTrivaBinding binding = FragmentTrivaBinding.bind(view);

        ListView listView = binding.listView;
        TriviaAdaptor adaptor = new TriviaAdaptor(getActivity(), mTrivia);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoQuiz(mTrivia.get(position));
            }
        });



    }

    TriviaListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (TriviaListener) context;
    }

    public interface TriviaListener {
        void gotoQuiz(Trivia trivia);
    }

}