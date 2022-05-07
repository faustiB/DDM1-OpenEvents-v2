package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.openevents.Fragments.EventsFragment;
import com.example.openevents.Fragments.ExploreFragment;
import com.example.openevents.Fragments.MessagesFragment;
import com.example.openevents.Fragments.SearchUsersFragment;
import com.example.openevents.Fragments.TimelineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EventsFragmentManagerActivity extends AppCompatActivity implements EventsFragment.EventsFragmentOutput {

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
            //TODO: Rename de los ids y cambio de las imagenes a las de las opciones que nos toca.
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
                //TODO: Añadir otra opción de mi propio usuario (incluir un fragment tb de my profile)
        }
        return false;
    };


    @Override
    public void NavigateToCreate() {
        Intent i = new Intent(this, CreateEventActivity.class );
        startActivity(i);
    }
}