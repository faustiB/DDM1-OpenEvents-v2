package com.example.openevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExploreEventsActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploreevents);

        bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.events:
                        intent = new Intent(getApplicationContext(), EventsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.timeline:
                        intent = new Intent(getApplicationContext(), TimelineActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.messages:
                        intent = new Intent(getApplicationContext(), MessagesActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.search_users:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        break;
                }

                return false;
            }
        }
        );
    }
}