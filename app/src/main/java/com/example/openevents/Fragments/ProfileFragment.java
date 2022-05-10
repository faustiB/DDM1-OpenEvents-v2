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
    ImageView imageView;
    TextView user_name, last_name, email, avgScore, numComments, percentageComments;

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
        setViews(v);
        setUserData(new UserResponse(1, "...", "...", "...", "...", "..."));

        apiClient = APIClient.getInstance(getActivity());
        apiClient.searchUsersByString("bet@openevents.com", new OpenEventsCallback<List<UserResponse>>() {
                    @Override
                    public void onResponseOpenEvents(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                        if (response.isSuccessful()) {
                            List<UserResponse> users = response.body();
                            if (users.size() > 0) {
                                setUserData(users.get(0));
                            }
                        }
                    }
                    @Override
                    public void onFailureOpenEvents() {
                        System.out.println("failure");
                    }
                }
        );

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

    private void setViews(View v) {
        imageView = v.findViewById(R.id.profile_picture);
        user_name = v.findViewById(R.id.user_name);
        last_name = v.findViewById(R.id.user_surname);
        email = v.findViewById(R.id.user_email);
        avgScore = v.findViewById(R.id.average_score);
        numComments = v.findViewById(R.id.number_user_comments);
        percentageComments = v.findViewById(R.id.percentage_of_comments);
    }

    private void setUserData(UserResponse user) {
        user_name.setText(user.getName());
        last_name.setText(user.getLast_name());
        email.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getImage()).into(imageView);

        avgScore.setText("22%");
        numComments.setText("2342");
        percentageComments.setText("50%");
    }
}