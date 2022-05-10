package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText etName = findViewById(R.id.et_edit_name);
        EditText etLastname = findViewById(R.id.et_last_name);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etPassword1 = findViewById(R.id.et_password1);
        EditText etPassword2 = findViewById(R.id.et_password2);
        EditText etProfilePicture = findViewById(R.id.et_profile_picture);

        etName.setText(getIntent().getStringExtra("user_name"));
        etLastname.setText(getIntent().getStringExtra("last_name"));
        etEmail.setText(getIntent().getStringExtra("email"));
        etProfilePicture.setText(getIntent().getStringExtra("profile_picture"));

        Button btnEdit = findViewById(R.id.bt_edit_profile);
        btnEdit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String lastname = etLastname.getText().toString();
            String email = etEmail.getText().toString();
            String password1 = etPassword1.getText().toString();
            String password2 = etPassword2.getText().toString();
            String profilePicture = etProfilePicture.getText().toString();

            if (password1.isEmpty() || password2.isEmpty() ) {
                requestWithoutPassword(name, lastname, email, profilePicture);
            } else {
                if (password1.equals(password2)) {
                    if (password1.length() >= 8) {
                        requestWithPassword(name, lastname, email, password1, profilePicture);
                    } else {
                        Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Passwords are not the same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestWithPassword(String name, String lastname, String email, String password1, String profilePicture) {

    }

    private void requestWithoutPassword(String name, String lastname, String email, String profilePicture) {

    }
}