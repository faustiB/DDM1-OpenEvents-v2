package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.openevents.Fragments.EventsFragment;
import com.example.openevents.Fragments.ExploreFragment;
import com.example.openevents.Fragments.ProfileFragment;
import com.example.openevents.Fragments.SearchUsersFragment;
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

            case R.id.search_users:
                SearchUsersFragment searchUsersFragment = new SearchUsersFragment();
                FragmentTransaction searchusersFragmentTransaction = getSupportFragmentManager().beginTransaction();
                searchusersFragmentTransaction.replace(R.id.flFragment, searchUsersFragment, "");
                searchusersFragmentTransaction.commit();
                return true;

            case R.id.profile:
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction profileFragmentTransaction = getSupportFragmentManager().beginTransaction();
                profileFragmentTransaction.replace(R.id.flFragment, profileFragment, "");
                profileFragmentTransaction.commit();
                return true;

        }
        return false;
    };


    @Override
    public void NavigateToCreate() {
        Intent i = new Intent(this, CreateEventActivity.class );
        startActivity(i);
    }
}