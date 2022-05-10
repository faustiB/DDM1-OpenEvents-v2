package com.example.openevents.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.EditProfileActivity;
import com.example.openevents.R;
import com.example.openevents.Response.UserResponse;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private APIClient apiClient;
    private int userId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        getUserId("guillem@openevents.com");
        System.out.println("User id: " + userId);

        ImageView imageView = v.findViewById(R.id.profile_picture);
        TextView user_name = v.findViewById(R.id.user_name);
        TextView last_name = v.findViewById(R.id.user_surname);
        TextView email = v.findViewById(R.id.user_email);
        TextView avgScore = v.findViewById(R.id.average_score);
        TextView numComments = v.findViewById(R.id.number_user_comments);
        TextView percentageComments = v.findViewById(R.id.percentage_of_comments);

        user_name.setText("Guillem");
        last_name.setText("Miro");
        email.setText("guillem@openevents.com");
        avgScore.setText("4.5");
        numComments.setText("10");
        percentageComments.setText("50%");

        //load an image from the internet using Glide
        Glide.with(getActivity()).load("https://i.imgur.com/ghy8Xx1.png").into(imageView);

        ExtendedFloatingActionButton fab = v.findViewById(R.id.edit_user_profile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("user_name", user_name.getText().toString());
                intent.putExtra("last_name", last_name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("profile_picture", "https://i.imgur.com/ghy8Xx1.png");
                startActivity(intent);
            }
        });

        return v;
    }


    private void getUserId(String email) {
        apiClient = APIClient.getInstance(getActivity());
        apiClient.searchUsersByString(email, new OpenEventsCallback<List<UserResponse>>() {
                    @Override
                    public void onResponseOpenEvents(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                        if (response.isSuccessful()) {
                            List<UserResponse> users = response.body();
                            System.out.println(users.get(0).getName() + " " + users.get(0).getId());
                            if (users.size() > 0) {
                                userId = users.get(0).getId();
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