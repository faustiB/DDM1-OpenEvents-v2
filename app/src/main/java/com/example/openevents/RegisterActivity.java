package com.example.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        apiClient = APIClient.getInstance(getApplicationContext());

        EditText etEmail = findViewById(R.id.et_register_email);
        EditText etFirstName = findViewById(R.id.et_register_first_name);
        EditText etLastName = findViewById(R.id.et_register_last_name);
        EditText etPassword = findViewById(R.id.et_register_password);
        EditText etPasswordConf = findViewById(R.id.et_register_password_conf);
        EditText etProfilePicture = findViewById(R.id.et_register_profile_picture);
        Button btCreateAccount = findViewById(R.id.bt_register_create_account);

        btCreateAccount.setOnClickListener(view -> {
            if (checkPasswords(etPassword.getText().toString(), etPasswordConf.getText().toString())) {
                //TEST DATA
                etProfilePicture.setText("https://i.imgur.com/ghy8Xx1.png");
                //test DATA
                RegisterRequest registerRequest = new RegisterRequest(etFirstName.getText().toString(),
                        etLastName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etProfilePicture.getText().toString());

                apiClient.register(registerRequest, new OpenEventsCallback<RegisterResponse>() {
                    @Override
                    public void onResponseOpenEvents(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            System.out.println(response.body().toString());
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "User created! Now you must log in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            }, 1000);
                        } else {
                            System.out.println(response.body().toString());
                            Toast.makeText(getApplicationContext(),
                                    "There was an error creating the user...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailureOpenEvents() {
                        Toast.makeText(getApplicationContext(), "User not created :( !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private boolean checkPasswords(String password, String passwordConf) {
        if (!password.equals(passwordConf)) {
            Toast.makeText(this, "Passwords are not the same, please check them", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() < 8) {
            Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}