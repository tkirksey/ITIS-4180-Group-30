package com.example.assignment12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogAdapter extends ArrayAdapter<Log> {

    ArrayList<Log> mLogs;
    LogListener mListener;

    public LogAdapter(@NonNull Context context, @NonNull List<Log> objects, LogListener listener) {
        super(context, 0, objects);
        this.mLogs = (ArrayList<Log>) objects;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_list, parent, false);
        }

        Log log = mLogs.get(position);
        ViewHolder viewHolder = new ViewHolder(convertView);

        viewHolder.dateTime.setText("Date: " + DateFormat.getDateInstance().format(new Date(log.date)));
        viewHolder.sleepAmount.setText("Sleep: " + log.sleepHours + " Hours");
        viewHolder.exerciseAmount.setText("Exercise: " + log.exerciseHours + " Hours");
        viewHolder.weight.setText("Weight: " + log.weight + " lbs.");

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDelete(log);
            }
        });

        return convertView;
    }

    public static class ViewHolder {

        TextView dateTime, sleepAmount, exerciseAmount, weight;
        Button delete;

        public ViewHolder(View view){

            dateTime = view.findViewById(R.id.textViewDateTime);
            sleepAmount = view.findViewById(R.id.textViewSleepTime);
            exerciseAmount = view.findViewById(R.id.textViewLogExerciseTime);
            weight = view.findViewById(R.id.textViewLogWeight);
            delete = view.findViewById(R.id.buttonDelete);

        }

    }

    public interface LogListener {
        void onDelete(Log log);
    }

}
