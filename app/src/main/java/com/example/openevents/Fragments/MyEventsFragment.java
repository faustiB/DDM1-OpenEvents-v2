package com.example.openevents.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Adapters.EventsAdapter;
import com.example.openevents.EventActivity;
import com.example.openevents.MyEventActivity;
import com.example.openevents.R;
import com.example.openevents.Response.EventResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MyEventsFragment extends Fragment {

    public interface EventsFragmentOutput {
        void NavigateToCreate();
    }

    private Button btnCreateEvent;
    private ArrayList<EventResponse> events = new ArrayList<>();
    EventsAdapter eventsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyEventsFragment newInstance(String param1, String param2) {
        MyEventsFragment fragment = new MyEventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myevents, container, false);
        btnCreateEvent = v.findViewById(R.id.bt_create_event);
        btnCreateEvent.setOnClickListener(view -> {
            if (getActivity() instanceof EventsFragmentOutput) {
                ((EventsFragmentOutput) getActivity()).NavigateToCreate();
            }
        });

        setViews(v);
        int id = getUserId();
        executeApiCall(id);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        executeApiCall(getUserId());
    }

    private void executeApiCall(int id) {
        APIClient apiClient = APIClient.getInstance(getContext());
        apiClient.getUserAssistances(id, new OpenEventsCallback() {
            @Override
            public void onResponseOpenEvents(Call call, Response response) {
                if (response.isSuccessful()) {
                    events.clear();
                    if (response.body() != null) {
                        events.addAll((Collection<? extends EventResponse>) response.body());
                        eventsAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailureOpenEvents() {

            }
        });


    }

    private int getUserId() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("userId", MODE_PRIVATE);
        String id = sharedPreferences.getString("userId", null);
        return Integer.parseInt(id);
    }

    private void setViews(View v) {

        eventsAdapter = new EventsAdapter(getContext(), events, event -> {
            Intent intent = new Intent(getContext(), MyEventActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        RecyclerView rvEvents = v.findViewById(R.id.rv_my_events);
        rvEvents.setAdapter(eventsAdapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}