package edu.charlotte.assignment10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.charlotte.assignment10.models.Mood;

public class HorizontalImageAdapter extends RecyclerView.Adapter<HorizontalImageAdapter.HorizontalImageViewHolder> {

    ArrayList<Mood> mMoods;
    MoodListener mListener;

    public HorizontalImageAdapter(ArrayList<Mood> moods, MoodListener listener) {
        this.mMoods = moods;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public HorizontalImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image_filter, parent, false);
        return new HorizontalImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalImageViewHolder holder, int position) {

        Mood mood = mMoods.get(position);

        holder.imageView.setImageResource(mood.getImageResourceId());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMoodSelect(mood);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMoods.size();
    }

    public static class HorizontalImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        View view;

        public HorizontalImageViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
            this.imageView = view.findViewById(R.id.imageView);
        }
    }

    public interface MoodListener {
        void onMoodSelect(Mood mood);
    }

}
