package com.example.openevents;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private APIClient apiClient = APIClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Sign up
        TextView tvSignUp = findViewById(R.id.tv_login_sign_up);
        tvSignUp.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "TEST CLICK SIGNUP", Toast.LENGTH_SHORT).show();
        });

        //Sign in
        Button btSignIn = findViewById(R.id.bt_login_sign_in);
        btSignIn.setOnClickListener(view -> {

            EditText etEmail = findViewById(R.id.et_login_email);
            EditText etPassword = findViewById(R.id.et_login_password);

            LoginRequest loginRequest = new LoginRequest(etEmail.getText().toString(), etPassword.getText().toString());
            //TODO: Not working, search how to put parameters in call body
            /*apiClient.login(loginRequest, new OpenEventsCallback<LoginResponse>() {
                @Override
                public void onResponseOpenEvents(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Toast.makeText(getApplicationContext(), "TEST CALL REPSONSE OK", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailureOpenEvents() {

                }

            });*/
        });


    }
}