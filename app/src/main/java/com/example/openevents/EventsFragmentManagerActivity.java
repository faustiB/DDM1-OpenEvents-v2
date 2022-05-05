package com.example.openevents;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EventsFragmentManagerActivity extends AppCompatActivity {

    ActionBar actionBar;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_fragment_manager);

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(selectedListener);

        ExploreFragment fragment = new ExploreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment, "");
        fragmentTransaction.commit();

    }

    private NavigationBarView.OnItemSelectedListener selectedListener = menuItem -> {
        switch (menuItem.getItemId()) {

            case R.id.explore_events:
                ExploreFragment exploreFragmentfragment = new ExploreFragment();
                FragmentTransaction exploreFragmentTransaction = getSupportFragmentManager().beginTransaction();
                exploreFragmentTransaction.replace(R.id.flFragment, exploreFragmentfragment, "");
                exploreFragmentTransaction.commit();
                return true;

            case R.id.events:
                EventsFragment eventsFragment = new EventsFragment();
                FragmentTransaction eventsFragmentTransaction = getSupportFragmentManager().beginTransaction();
                eventsFragmentTransaction.replace(R.id.flFragment, eventsFragment, "");
                eventsFragmentTransaction.commit();
                return true;

            case R.id.timeline:
                TimelineFragment timelineFragment = new TimelineFragment();
                FragmentTransaction timelineFragmentTransaction = getSupportFragmentManager().beginTransaction();
                timelineFragmentTransaction.replace(R.id.flFragment, timelineFragment, "");
                timelineFragmentTransaction.commit();
                return true;

            case R.id.messages:
                MessagesFragment messagesFragment = new MessagesFragment();
                FragmentTransaction messagesFragmentTransaction = getSupportFragmentManager().beginTransaction();
                messagesFragmentTransaction.replace(R.id.flFragment, messagesFragment, "");
                messagesFragmentTransaction.commit();
                return true;

            case R.id.search_users:
                SearchUsersFragment searchUsersFragment = new SearchUsersFragment();
                FragmentTransaction searchusersFragmentTransaction = getSupportFragmentManager().beginTransaction();
                searchusersFragmentTransaction.replace(R.id.flFragment, searchUsersFragment, "");
                searchusersFragmentTransaction.commit();
                return true;
        }
        return false;
    };


}