package com.example.openevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.R;
import com.example.openevents.Response.AssistEventResponse;
import com.example.openevents.Response.EventResponse;
import com.example.openevents.Response.MessageResponse;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import retrofit2.Call;
import retrofit2.Response;

public class OwnEventActivity extends AppCompatActivity {
    ImageView eventImage;
    TextView eventName, eventDescription, eventStart, eventEnd, eventLocation;
    ExtendedFloatingActionButton attendEvent, editEvent, deleteEvent;
    EventResponse event;
    APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_event);
        apiClient = APIClient.getInstance(getApplicationContext());

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
                attendEvent();
            }
        });

        editEvent = findViewById(R.id.edit_event);
        editEvent.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEventActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        });

        deleteEvent = findViewById(R.id.delete_event);
        deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeEvent();
            }
        });
    }

    private void setTexts() {
        Intent i = getIntent();
        event = (EventResponse) i.getSerializableExtra("event");

        Glide.with(getApplicationContext())
                .load(event.getImage())
                .placeholder(R.drawable.event_image)
                .into(eventImage);

        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());

        eventStart.setText(event.getEventStart_date());
        eventEnd.setText(event.getEventEnd_date());

        eventLocation.setText(event.getLocation());
    }

    private void attendEvent() {
        int id = event.getId();
        apiClient.assistEvent(id, new OpenEventsCallback() {
            @Override
            public void onResponseOpenEvents(Call call, Response response) {

                if (((AssistEventResponse) response.body()).getAffectedRows() == 1) {
                    Toast.makeText(OwnEventActivity.this, "Attending event", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailureOpenEvents() {

            }
        });
    }

    private void removeEvent() {
        int id = event.getId();
        apiClient.deleteEvent(id, new OpenEventsCallback() {
            @Override
            public void onResponseOpenEvents(Call call, Response response) {
                System.out.println((MessageResponse) response.body());
                Toast.makeText(OwnEventActivity.this, "Deleted event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailureOpenEvents() {

            }
        });
    }
}