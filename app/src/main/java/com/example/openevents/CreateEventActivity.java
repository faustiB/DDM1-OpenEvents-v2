package com.example.openevents;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    EditText etName, etImage, etLocation, etDescription, etEventStart_date, etEventEnd_date, etN_participants;

    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        apiClient = APIClient.getInstance(getApplicationContext());

        etName = findViewById(R.id.et_create_event_name);
        etImage = findViewById(R.id.et_create_event_profile_picture);
        etLocation = findViewById(R.id.et_create_event_location);
        etDescription = findViewById(R.id.et_create_event_description);
        etEventStart_date = findViewById(R.id.et_create_event_start_date);
        etEventEnd_date = findViewById(R.id.et_create_event_end_date);
        etN_participants = findViewById(R.id.et_create_event_participants);
        spinnerCategories = findViewById(R.id.sp_create_event_categories);

        configureSpinner();

        //TODO:Delete testing data method.
        setTestingData();

        Button btnCreateEvent = findViewById(R.id.bt_create_event);
        btnCreateEvent.setOnClickListener(view -> {

            String name = etName.getText().toString();
            String image = etImage.getText().toString();
            String location = etLocation.getText().toString();
            String description = etDescription.getText().toString();
            String startDate = etEventStart_date.getText().toString();
            String endDate = etEventEnd_date.getText().toString();
            int participants = Integer.parseInt(String.valueOf(etN_participants.getText()));
            String type = spinnerCategories.getSelectedItem().toString();


            CreateEventRequest request = new CreateEventRequest(name, image, location, description, startDate, endDate, participants, type);
            apiClient.createEvent(request, new OpenEventsCallback<CreateEventResponse>() {
                @Override
                public void onResponseOpenEvents(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {

                    Toast.makeText(getApplicationContext(), "Event: " + etName.getText().toString() + " created successfully", Toast.LENGTH_SHORT).show();

                    System.out.println(response.body());
                }

                @Override
                public void onFailureOpenEvents() {
                    Log.d("TAG", "onFailureOpenEvents: ");
                }
            });
        });

    }

    private void setTestingData() {
        etName.setText("test name FBG");
        etImage.setText("https://i.imgur.com/JprpLyc.jpg");
        etLocation.setText("Madrid");
        etDescription.setText("Learn the bla bla bla");
        etEventStart_date.setText("2022-01-20T12:00:00.000Z");
        etEventEnd_date.setText("2022-01-20T13:30:00.000Z");
        etN_participants.setText("128");
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