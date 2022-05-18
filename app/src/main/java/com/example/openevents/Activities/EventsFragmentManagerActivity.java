package com.example.openevents.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Fragments.ExploreFragment;
import com.example.openevents.Fragments.MyEventsFragment;
import com.example.openevents.Fragments.ProfileFragment;
import com.example.openevents.Fragments.SearchUsersFragment;
import com.example.openevents.R;
import com.example.openevents.Response.UserResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class EventsFragmentManagerActivity extends AppCompatActivity implements MyEventsFragment.EventsFragmentOutput {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_fragment_manager);

        setUserId();

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(selectedListener);

        ExploreFragment fragment = new ExploreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment, "");
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener selectedListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.explore_events:
                ExploreFragment exploreFragment = new ExploreFragment();
                FragmentTransaction exploreFragmentTransaction = getSupportFragmentManager().beginTransaction();
                exploreFragmentTransaction.replace(R.id.flFragment, exploreFragment, "");
                exploreFragmentTransaction.commit();
                return true;

            case R.id.myevents:
                MyEventsFragment myEventsFragment = new MyEventsFragment();
                FragmentTransaction eventsFragmentTransaction = getSupportFragmentManager().beginTransaction();
                eventsFragmentTransaction.replace(R.id.flFragment, myEventsFragment, "");
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
        Intent i = new Intent(this, CreateEventActivity.class);
        startActivity(i);
    }

    private void setUserId() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("email", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);

        APIClient apiClient = APIClient.getInstance(getApplicationContext());
        apiClient.searchUsersByString(email, new OpenEventsCallback<List<UserResponse>>() {
                    @Override
                    public void onResponseOpenEvents(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                        if (response.isSuccessful()) {
                            List<UserResponse> users = response.body();
                            if (users.size() > 0) {
                                UserResponse user = users.get(0);
                                SharedPreferences sp = getApplicationContext().getSharedPreferences("userId", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("userId", user.getIdString());
                                editor.apply();
                            }
                        }
                    }

                    @Override
                    public void onFailureOpenEvents() {
                        System.out.println("failure");
                    }
                }
        );
    }
}