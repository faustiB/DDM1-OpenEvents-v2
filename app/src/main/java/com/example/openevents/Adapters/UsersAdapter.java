package com.example.openevents.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.openevents.R;
import com.example.openevents.Response.UserResponse;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Context context;
    ArrayList<UserResponse> users;

    public UsersAdapter(Context context, ArrayList<UserResponse> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);

        // Passing view to ViewHolder
        UsersAdapter.ViewHolder viewHolder = new UsersAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName() + " " + users.get(position).getLast_name());
        Glide.with(context).load(users.get(position).getImage()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView name;

        public ViewHolder(View view) {
            super(view);
            userImage = view.findViewById(R.id.card_image);
            name = view.findViewById(R.id.card_user_name);
        }

    }
}