package com.example.openevents.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Adapters.EventsAdapter;
import com.example.openevents.Adapters.UsersAdapter;
import com.example.openevents.R;
import com.example.openevents.Response.UserResponse;
import com.example.openevents.Response.UsersResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchUsersFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<UserResponse> users = new ArrayList<>();
    UsersAdapter usersAdapter;
    RecyclerView rvUsers;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchUsersFragment newInstance(String param1, String param2) {
        SearchUsersFragment fragment = new SearchUsersFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_users, container, false);

        setViews(v);
        searchView.setOnQueryTextListener(this);
        executeApiCall("");

        return v;
    }

    private void setViews(View v) {
        usersAdapter = new UsersAdapter(getContext(), users);
        rvUsers = v.findViewById(R.id.rv_users);
        rvUsers.setAdapter(usersAdapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        searchView = v.findViewById(R.id.search_users_widget);
    }

    private void executeApiCall(String email) {
        APIClient apiClient = APIClient.getInstance(getActivity());
        apiClient.searchUsersByString(email, new OpenEventsCallback<List<UserResponse>>() {
                    @Override
                    public void onResponseOpenEvents(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                        if (response.isSuccessful()) {
                            users.clear();
                            if (response.body() != null) {
                                users.addAll(response.body());
                                usersAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        executeApiCall(s);
        return false;
    }
}