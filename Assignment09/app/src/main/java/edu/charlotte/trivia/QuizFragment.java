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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import edu.charlotte.trivia.databinding.FragmentQuizBinding;
import edu.charlotte.trivia.models.Question;
import edu.charlotte.trivia.models.Trivia;

public class QuizFragment extends Fragment {

    public static final String KEY_TRIVIA = "KEY_TRIVIA";

    Trivia mTrivia;

    int firstTryCorrect = 0;
    boolean isFirstTry = true;

    int questionIndex = 0;

    Question currentQuestion;

    TextView question, outOf;
    ImageView questionImg;
    ListView answers;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(Trivia trivia) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_TRIVIA, trivia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(KEY_TRIVIA) != null) {
            mTrivia = (Trivia) getArguments().getSerializable(KEY_TRIVIA);
        } else {
            mTrivia = new Trivia();
        }
        getActivity().setTitle("Trivia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentQuizBinding binding = FragmentQuizBinding.bind(view);

        question = binding.textViewQuestion;
        outOf = binding.textViewOutOf;
        questionImg = binding.imageViewQuestion;
        answers = binding.listViewAnswers;

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBack();
            }
        });

        refresh();

    }

    QuizListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (QuizListener) context;
    }

    public interface QuizListener {
        void gotoStats(int numQuestions, int firstTry);
        void goBack();
    }

    private void refresh(){

        isFirstTry = true;

        currentQuestion = mTrivia.getQuestions().get(questionIndex);

        outOf.setText("Question " + (questionIndex + 1) + " of " + mTrivia.getQuestions().size());

        question.setText(currentQuestion.getQuestion());

        Picasso.get()
                .load(currentQuestion.getImgUrl())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(questionImg);

        answers.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, currentQuestion.getAnswers()));

        answers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(currentQuestion.getCorrectAnswerIndex() == position){

                    if(isFirstTry){
                        firstTryCorrect += 1;
                    }

                    questionIndex += 1;

                    if(questionIndex == mTrivia.getQuestions().size()){
                        mListener.gotoStats(mTrivia.getQuestions().size(), firstTryCorrect);
                    } else {
                        refresh();
                    }

                } else {

                    Toast.makeText(getActivity(), "Answer was incorrect. Try again!", Toast.LENGTH_SHORT).show();
                    isFirstTry = false;

                }

            }
        });

    }

}