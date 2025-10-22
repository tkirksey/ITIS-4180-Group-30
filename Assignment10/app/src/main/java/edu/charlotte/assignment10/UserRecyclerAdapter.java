package edu.charlotte.assignment10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.charlotte.assignment10.models.User;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {

    ArrayList<User> mUsers;
    UserRecyclerAdapterListener mListener;

    public UserRecyclerAdapter(ArrayList<User> users, UserRecyclerAdapterListener listener){
        mUsers = users;
        mListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = mUsers.get(position);

        holder.mUser = user;

        holder.userName.setText(user.getName());
        holder.ageGroup.setText(user.getAgeGroup());
        holder.mood.setImageResource(user.getMood().getImageResourceId());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteUser(user);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSelected(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView mood, delete;
        TextView userName, ageGroup;

        User mUser;
        View mView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mood = itemView.findViewById(R.id.imageViewUserMood);
            this.delete = itemView.findViewById(R.id.imageViewDelete);

            this.userName = itemView.findViewById(R.id.textViewUserName);
            this.ageGroup = itemView.findViewById(R.id.textViewUserAgeGroup);

            mUser = null;
            mView = itemView;

        }
    }

    public interface UserRecyclerAdapterListener {
        void deleteUser(User user);
        void sendSelected(User user);
    }

}


