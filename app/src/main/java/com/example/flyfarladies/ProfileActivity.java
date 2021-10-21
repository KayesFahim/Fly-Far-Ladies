package com.example.flyfarladies;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.flyfarladies.Dashboard.ChangePasswordActivity;
import com.example.flyfarladies.Dashboard.EditProfileActivity;
import com.example.flyfarladies.Dashboard.SettingsActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
    }

    public void ChangePassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    public void EditProfile(View view) {
        startActivity(new Intent(this, EditProfileActivity.class));

    }

    public void Settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}