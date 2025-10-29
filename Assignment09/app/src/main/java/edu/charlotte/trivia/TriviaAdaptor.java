package edu.charlotte.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import edu.charlotte.trivia.models.Trivia;

public class TriviaAdaptor extends ArrayAdapter<Trivia> {

    Context mContext;
    ArrayList<Trivia> mTrivia;

    public TriviaAdaptor(@NonNull Context context, @NonNull ArrayList<Trivia> trivia) {
        super(context, 0, trivia);
        mContext = context;
        mTrivia = trivia;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the view if it hasn't been already
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.trivia_list_item, parent, false);
        }

        Trivia trivia = mTrivia.get(position);

        TextView title = convertView.findViewById(R.id.textViewTriviaTitle);
        TextView desc = convertView.findViewById(R.id.textViewTriviaDesc);

        title.setText(trivia.getTitle());
        desc.setText(trivia.getDescription());

        return convertView;
    }

}
