package com.example.openevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class EventsActivity extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        drawerLayout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_events));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navView = findViewById(R.id.nav_view_event);
        navView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.evt_item1){
                Toast.makeText(getApplicationContext(),"ITEM 1",Toast.LENGTH_SHORT).show();
            }
            if (item.getItemId() == R.id.evt_item2){
                Toast.makeText(getApplicationContext(),"ITEM 2",Toast.LENGTH_SHORT).show();
            }
            if (item.getItemId() == R.id.evt_item3){
                Toast.makeText(getApplicationContext(),"ITEM 3",Toast.LENGTH_SHORT).show();
            }

        return true;
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}