package com.example.flyfarladies.Booking;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flyfarladies.Dashboard.SettingsActivity;
import com.example.flyfarladies.MainActivity;
import com.example.flyfarladies.ProfileActivity;
import com.example.flyfarladies.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class BookingDetailsActivity extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d434549.40374533384!2d74.24349628287739!3d31.690830957117996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sPakistan+Lahore!5e0!3m2!1sen!2s!4v1395138949280 width=600 height=450 frameborder=0 style=border:0</iframe>";
        WebView googleMapWebView = (WebView) findViewById(R.id.googlemap_webView);
        googleMapWebView.getSettings().setJavaScriptEnabled(true);
        googleMapWebView.loadData(iframe, "text/html", "utf-8");
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoMyBooking(View view) {
        startActivity(new Intent(this, MyBookingActivity.class));
    }

    public void gotoMyProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void Settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}