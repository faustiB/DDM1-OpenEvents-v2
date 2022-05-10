package com.example.openevents.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.openevents.R;
import com.example.openevents.Response.EventResponse;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{
    Context context;
    ArrayList<EventResponse> events;

    public EventsAdapter(Context context, ArrayList<EventResponse> events) {
        this.context = context;
        this.events = events;
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
        //TODO: Añadir los demas elementos al holder
        holder.title.setText(events.get(position).getName());
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
            eventImage = view.findViewById(R.id.iv_event_item);
            title = view.findViewById(R.id.tv_event_item_title);
            dateAndHour = view.findViewById(R.id.tv_event_item_date_and_time);
            place = view.findViewById(R.id.tv_event_item_place);
        }
    }
}