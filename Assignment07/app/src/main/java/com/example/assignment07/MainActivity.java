package com.example.assignment07;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity
        extends
            AppCompatActivity
        implements
            GenreSelectFragment.GenreSelectListener,
            BookSelectFragment.BookSelectListener,
            BookViewFragment.BookViewListener
{

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, GenreSelectFragment.newInstance(Data.getAllGenres()))
                .commit();
    }

    @Override
    public void gotoBookSelect(String genre) {
        // assuming genre is not null and not empty
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, BookSelectFragment.newInstance(Data.getBooksByGenre(genre)))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoBook(Book book) {
        // assuming book is not null
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, BookViewFragment.newInstance(book))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }
}