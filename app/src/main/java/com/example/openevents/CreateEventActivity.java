package com.example.openevents;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.openevents.API.APIClient;

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

        Button btnCreateEvent = findViewById(R.id.bt_create_event);
        btnCreateEvent.setOnClickListener(view -> {
            //TODO:request para crear evento
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