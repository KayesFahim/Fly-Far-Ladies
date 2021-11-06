package com.example.flyfarladies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flyfarladies.Packages.PackagesMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void packagesviewAll(View view) {
        startActivity(new Intent(this, PackagesMainActivity.class));
    }
}