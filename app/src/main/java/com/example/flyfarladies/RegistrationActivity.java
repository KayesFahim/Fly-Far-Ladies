package com.example.flyfarladies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    EditText fullName, userEmail, userPassword;
    Button RegistrationBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullName = findViewById(R.id.editTextName);
        userEmail = findViewById(R.id.editTextEmailAddress);
        userPassword = findViewById(R.id.editTextPassword);
        RegistrationBtn = findViewById(R.id.RegBtn);

        auth = FirebaseAuth.getInstance();
        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String name = fullName.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, Enter Minimum 6 Characters!", Toast.LENGTH_LONG).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successfully :" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                                // Warning

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    String user_id = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                    DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("User").child(user_id);

                                    // User Details

                                    HashMap<String, Object> newPost = new HashMap<>();

                                    newPost.put("userName", name);
                                    newPost.put("userEmail", email);
                                    DB.setValue(newPost);

                                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    public void RegistrationBtn(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}