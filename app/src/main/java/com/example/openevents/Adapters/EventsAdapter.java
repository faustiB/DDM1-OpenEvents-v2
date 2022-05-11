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
import com.example.openevents.Response.EventResponse;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{
    Context context;
    ArrayList<EventResponse> events;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemclick(EventResponse event);
    }

    public EventsAdapter(Context context, ArrayList<EventResponse> events,OnItemClickListener listener) {
        this.context = context;
        this.events = events;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

        // Passing view to ViewHolder
        EventsAdapter.ViewHolder viewHolder = new EventsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        holder.bind(events.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView title;
        TextView dateAndHour;
        TextView place;

        public ViewHolder(View view) {
            super(view);
            eventImage = view.findViewById(R.id.event_card_image);
            title = view.findViewById(R.id.event_card_title);
            dateAndHour = view.findViewById(R.id.event_card_date_time);
            place = view.findViewById(R.id.event_card_place);
        }


        public void bind(EventResponse event, OnItemClickListener listener) {
            Glide.with(context).load(event.getImage()).into(eventImage);
            title.setText(event.getName());
            dateAndHour.setText(event.getEventStart_date());
            place.setText(event.getLocation());
            itemView.setOnClickListener(view -> listener.onItemclick(event));
        }
    }
}