package edu.charlotte.trivia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import edu.charlotte.trivia.models.Data;
import edu.charlotte.trivia.models.Trivia;

public class MainActivity
        extends
            AppCompatActivity
        implements
            TrivaFragment.TriviaListener,
            QuizFragment.QuizListener,
            StatsFragment.StatsListener
{

    ArrayList<Trivia> mTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mTrivia = Data.getAllTrivias();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TrivaFragment.newInstance(mTrivia))
                .commit();
    }

    @Override
    public void gotoQuiz(Trivia trivia) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, QuizFragment.newInstance(trivia))
                .commit();
    }

    @Override
    public void gotoStats(int numQuestions, int firstTry) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, StatsFragment.newInstance(numQuestions, firstTry))
                .commit();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, TrivaFragment.newInstance(mTrivia))
                .commit();
    }
}