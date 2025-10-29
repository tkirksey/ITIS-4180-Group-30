package edu.charlotte.assignment10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class HorizontalTextAdapter extends RecyclerView.Adapter<HorizontalTextAdapter.ViewHolder> {

    ArrayList<String> mStrings;
    HorizontalTextListener mListener;

    public HorizontalTextAdapter(ArrayList<String> strings, HorizontalTextListener listener){
        this.mStrings = strings;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String string = mStrings.get(position);
        holder.textView.setText(string);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTextSelected(string);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textView = view.findViewById(R.id.textView);
        }
    }

    public interface HorizontalTextListener {

        void onTextSelected(String text);

    }

}
