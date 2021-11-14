package com.example.flyfarladies.Packages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flyfarladies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class CountryPackagesActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<PackagesModel> mlist;
    PackagesAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_packages);

        recyclerView = findViewById(R.id.countryPackageView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.countrysearch);
        progressDialog = new ProgressDialog(CountryPackagesActivity.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();


        Query query = FirebaseDatabase.getInstance().getReference("Packages").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                mlist = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mlist.add(ds.getValue(PackagesModel.class));
                }
                adapter = new PackagesAdapter(CountryPackagesActivity.this, mlist);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CountryPackagesActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });


    }
}