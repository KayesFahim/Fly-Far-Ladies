package com.example.flyfarladies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {

            setContentView(R.layout.activity_auth);
        }

    }

    public void AuthRegistration(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void AuthLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}