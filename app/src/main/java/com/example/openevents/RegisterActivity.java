package com.example.openevents;

import android.os.Bundle;
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

    private APIClient apiClient = APIClient.getInstance(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                        Toast.makeText(getApplicationContext(), "User created!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailureOpenEvents() {
                    }
                });
            } else {
                Toast.makeText(this, "Passwords are not the same, please check them", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private boolean checkPasswords(String password, String passwordConf) {
        return password.equals(passwordConf);
    }
}