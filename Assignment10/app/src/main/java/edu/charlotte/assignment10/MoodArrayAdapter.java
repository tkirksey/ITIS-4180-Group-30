package edu.charlotte.assignment10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import edu.charlotte.assignment10.models.Mood;

public class MoodArrayAdapter extends ArrayAdapter<Mood> {

    Context context;
    Mood[] moods;

    public MoodArrayAdapter(@NonNull Context context, @NonNull Mood[] objects) {
        super(context, 0, objects);

        this.context = context;
        this.moods = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item_mood, parent, false);
        }

        holder = new ViewHolder(convertView);

        Mood mood = moods[position];

        holder.imageView.setImageResource(mood.getImageResourceId());
        holder.textView.setText(mood.getName());

        return holder.view;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.textViewMood);
            this.imageView = itemView.findViewById(R.id.imageViewMood);
            this.view = itemView;

        }
    }

}


