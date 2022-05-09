package com.example.openevents;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Request.CreateEventRequest;
import com.example.openevents.Response.CreateEventResponse;

import retrofit2.Call;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //TODO: preguntar cuales son las categorías.
    String[] categories = {"Cat1", "Cat2", "Cat3", "Cat4"};
    Spinner spinnerCategories;

    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        configureSpinner();

        apiClient = APIClient.getInstance(getApplicationContext());

        EditText etName = findViewById(R.id.et_create_event_name);
        EditText etTmage = findViewById(R.id.et_create_event_profile_picture);
        EditText etLocation = findViewById(R.id.et_create_event_location);
        EditText etDescription = findViewById(R.id.et_create_event_description);
        EditText etEventStart_date = findViewById(R.id.et_create_event_start_date);
        EditText etEventEnd_date = findViewById(R.id.et_create_event_end_date);
        EditText etN_participants = findViewById(R.id.et_create_event_participants);
        Spinner spType = findViewById(R.id.sp_create_event_categories);

        Button btnCreateEvent = findViewById(R.id.bt_create_event);
        btnCreateEvent.setOnClickListener(view -> {

            String name = etName.getText().toString();
            String image = etTmage.getText().toString();
            String location = etLocation.getText().toString();
            String description = etDescription.getText().toString();
            String startDate = etEventStart_date.getText().toString();
            String endDate = etEventEnd_date.getText().toString();
            int participants = Integer.parseInt(String.valueOf(etN_participants.getText()));
            String type = spType.getSelectedItem().toString();




            CreateEventRequest request = new CreateEventRequest(name,image,location,description,startDate,endDate,participants,type);
            apiClient.createEvent(request, new OpenEventsCallback<CreateEventResponse>() {
                @Override
                public void onResponseOpenEvents(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailureOpenEvents() {
                    Log.d("TAG", "onFailureOpenEvents: ");
                }
            });
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void configureSpinner() {
        spinnerCategories = findViewById(R.id.sp_create_event_categories);
        spinnerCategories.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);
    }
}