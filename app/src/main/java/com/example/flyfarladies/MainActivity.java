package com.example.flyfarladies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.flyfarladies.Booking.BookingPageActivity;
import com.example.flyfarladies.Dashboard.SettingsActivity;
import com.example.flyfarladies.Packages.CountryPackagesActivity;
import com.example.flyfarladies.Packages.PackagesMainActivity;
import com.example.flyfarladies.Videos.VideoActivity;
import com.example.flyfarladies.Videos.VideoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    TextView videotitle, videodes;
    String VideoId, videoLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       imageView = findViewById(R.id.videoview);
       videotitle = findViewById(R.id.videotitle);
       videodes = findViewById(R.id.videodes);

        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Video").child("1");


        ValueEventListener postListener = new ValueEventListener() {

            @SuppressLint({"SetTextI18n", "ResourceType"})
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                VideoModel videoModel = dataSnapshot.getValue(VideoModel.class);
                assert videoModel != null;

                String bool = videoModel.getVideoStatus();
                if(bool.equals("true")) {

                    VideoId = videoModel.getVideoId();
                    videotitle.setText(videoModel.getVideoTitle());
                    videodes.setText(videoModel.getVideoDescription());
                    videoLink = videoModel.getDetailsLink();

                    Glide.with(getApplicationContext())
                            .load(videoModel.getVideoThumbnail())
                            .into(imageView);
                }else{
                    videotitle.setText("No Video Found");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Couldn't get data.", Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(postListener);

    }

    public void packagesviewAll(View view) {
        startActivity(new Intent(this, PackagesMainActivity.class));
    }

    public void Settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void gotoProfile(View view) {startActivity(new Intent(this, ProfileActivity.class));
    }

    public void gotoBooking(View view) {
        startActivity(new Intent(this, BookingPageActivity.class));

    }

    public void VideoPlayBtn(View view) {
        Intent mintent= new Intent(this, VideoActivity.class);
        mintent.putExtra("videoid",VideoId);
        startActivity(mintent);

    }

    public void gotoDeatails(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(videoLink));
        startActivity(i);

    }

    public void gotoCountryPackages(View view) {
        startActivity(new Intent(this, CountryPackagesActivity.class));
    }
}