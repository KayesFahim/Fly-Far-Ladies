package com.example.flyfarladies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flyfarladies.Booking.ThankYouActivity;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

    }

    public void AuthRegistration(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void AuthLogin(View view) {
        startActivity(new Intent(this, ThankYouActivity.class));
    }
}