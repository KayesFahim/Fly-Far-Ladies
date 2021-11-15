package com.example.flyfarladies.Packages;

import androidx.appcompat.app.AppCompatActivity;
import com.example.flyfarladies.R;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PackagePlaceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_package_place_details);

    }
}