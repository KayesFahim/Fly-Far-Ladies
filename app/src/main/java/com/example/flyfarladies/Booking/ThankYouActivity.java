package com.example.flyfarladies.Booking;

import androidx.appcompat.app.AppCompatActivity;
import com.example.flyfarladies.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

    }

    public void Settings(View view) {

    }

    public void gotoMyBooking(View view) {
        startActivity(new Intent(this, MyBookingActivity.class));
    }
}