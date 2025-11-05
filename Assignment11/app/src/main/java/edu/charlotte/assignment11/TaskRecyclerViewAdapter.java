package edu.charlotte.assignment11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.charlotte.assignment11.models.Task;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    ArrayList<Task> mTasks;
    TaskRecyclerViewListener mListener;

    public TaskRecyclerViewAdapter(ArrayList<Task> tasks, TaskRecyclerViewListener listener){
        this.mTasks = tasks;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = mTasks.get(position);

        holder.name.setText(task.name);
        holder.category.setText(task.category);
        holder.priority.setText(task.priority_name);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDelete(task);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(task);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView name, category, priority;
        ImageView deleteButton;

        View view;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;

            this.name = view.findViewById(R.id.textViewName);
            this.category = view.findViewById(R.id.textViewCategory);
            this.priority = view.findViewById(R.id.textViewPriority);
            this.deleteButton = view.findViewById(R.id.imageView);

        }
    }

    public interface TaskRecyclerViewListener {
        void onClick(Task task);
        void onDelete(Task task);
    }

}
