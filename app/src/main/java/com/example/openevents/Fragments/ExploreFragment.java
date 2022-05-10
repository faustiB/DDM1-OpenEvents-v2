package com.example.openevents.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Adapters.EventsAdapter;
import com.example.openevents.R;
import com.example.openevents.Response.EventResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

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

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
        View v = inflater.inflate(R.layout.fragment_explore, container, false);

        btnCreateEvent = v.findViewById(R.id.bt_create_event);
        btnCreateEvent.setOnClickListener(view -> {

            if (getActivity() instanceof MyEventsFragment.EventsFragmentOutput){
                ((MyEventsFragment.EventsFragmentOutput) getActivity()).NavigateToCreate();
            }

        });

        eventsAdapter = new EventsAdapter(getContext(),events);
        RecyclerView rvEvents = v.findViewById(R.id.rv_events);
        rvEvents.setAdapter(eventsAdapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext()));

        APIClient apiClient = APIClient.getInstance(getContext());

        apiClient.getEvents(new OpenEventsCallback<List<EventResponse>>() {
            @Override
            public void onResponseOpenEvents(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                events.clear();
                if (response.body() != null) {
                    events.addAll(response.body());
                    eventsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailureOpenEvents() {
                System.out.println("TEST CALL RESPONSE FAIL");
            }
        });




        return v;
    }
}