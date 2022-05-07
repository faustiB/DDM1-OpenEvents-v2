package com.example.openevents;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiClient = APIClient.getInstance(getApplicationContext());

        //Sign up
        TextView tvSignUp = findViewById(R.id.tv_login_sign_up);
        tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        //Sign in
        Button btSignIn = findViewById(R.id.bt_login_sign_in);
        btSignIn.setOnClickListener(view -> {

            EditText etEmail = findViewById(R.id.et_login_email);
            EditText etPassword = findViewById(R.id.et_login_password);

            LoginRequest loginRequest = new LoginRequest(etEmail.getText().toString(), etPassword.getText().toString());
            apiClient.login(getApplicationContext(), loginRequest, new OpenEventsCallback<LoginResponse>() {
                @Override
                public void onResponseOpenEvents(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), EventsFragmentManagerActivity.class);

                        //Pass email as parameter to the next activity and save it in the shared preferences
                        intent.putExtra("email", etEmail.getText().toString());
                        saveEmail(etEmail.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailureOpenEvents() {
                    System.out.println("TEST CALL RESPONSE FAIL");
                }
            });
        });


    }

    private void saveEmail(String email) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("email", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.apply();
    }
}