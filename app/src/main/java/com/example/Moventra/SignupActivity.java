package com.example.Moventra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText name, email, password, confirmPassword;
    MaterialButton signupBtn;
    TextView signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupBtn = findViewById(R.id.signupBtn);
        signInBtn = findViewById(R.id.signInBtn);

        // SIGN UP BUTTON LOGIC
        signupBtn.setOnClickListener(v -> {

            String n = name.getText().toString().trim();
            String e = email.getText().toString().trim();
            String p = password.getText().toString().trim();
            String cp = confirmPassword.getText().toString().trim();

            // Validation
            if (n.isEmpty() || e.isEmpty() || p.isEmpty() || cp.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!p.equals(cp)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save user (dummy storage)
            SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("name", n);
            editor.putString("email", e);
            editor.putString("password", p);
            editor.apply();

            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();

            // Go to Login screen
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });

        // SIGN IN TEXT CLICK (Go to Login)
        signInBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }
}