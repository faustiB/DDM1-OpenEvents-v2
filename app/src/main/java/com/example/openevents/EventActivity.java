package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.openevents.Response.EventResponse;
import com.example.openevents.Response.UserResponse;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class EventActivity extends AppCompatActivity {
    ImageView eventImage;
    TextView eventName, eventDescription, eventStart, eventEnd, eventLocation;
    ExtendedFloatingActionButton attendEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        setViews();
        setTexts();
    }

    private void setViews() {
        eventImage = findViewById(R.id.event_image);
        eventName = findViewById(R.id.event_name);
        eventDescription = findViewById(R.id.description_of_event);
        eventStart = findViewById(R.id.start_time);
        eventEnd = findViewById(R.id.end_time);
        eventLocation = findViewById(R.id.location_of_event);

        attendEvent = findViewById(R.id.attend_to_event);
        attendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add attend event logic
                Toast.makeText(EventActivity.this, "Attending event", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTexts() {
        Intent i = getIntent();
        EventResponse event = (EventResponse) i.getSerializableExtra("event");

        Glide.with(getApplicationContext())
                .load(event.getImage())
                .placeholder(R.drawable.event_image)
                .into(eventImage);

        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());

        //TODO: apply format to date
        eventStart.setText(event.getEventStart_date());
        eventEnd.setText(event.getEventEnd_date());

        eventLocation.setText(event.getLocation());
    }
}