package com.example.flyfarladies.Booking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.flyfarladies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BookingPageActivity extends AppCompatActivity {

    Spinner mspinner;
    TextView mtitle;
    TextView mcost;
    TextView mdecription;
    TextView mduration;
    ImageView mimgurl;
    FirebaseAuth auth;
    Button bookbtn;
    EditText packs;

    ProgressDialog progressDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);

        String[] countries = new String[]{
                "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda",
                "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
                "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic",
                "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica",
                "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt",
                "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland",
                "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany",
                "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
                "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
                "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of",
                "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
                "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
                "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of",
                "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
                "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
                "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
                "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
                "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles",
                "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland",
                "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago",
                "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
                "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
                "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};


        String title= getIntent().getStringExtra("title");
        String details = getIntent().getStringExtra("details");
        String cost = getIntent().getStringExtra("price");
        String duration = getIntent().getStringExtra("duration");
        String image = getIntent().getStringExtra("image");
        String postId = getIntent().getStringExtra("pid");



        mtitle = findViewById(R.id.pplacename);
        mtitle.setText(title);
        mcost = findViewById(R.id.pcost);
        mcost.setText(cost +"৳");
        mdecription = findViewById(R.id.ptourdetails);
        mdecription.setText(details);
        mduration = findViewById(R.id.pduration);
        mduration.setText(duration);
        mimgurl = findViewById(R.id.pplaceimg);

        Glide.with(getApplicationContext())
                .load(image)
                .into(mimgurl);


        mspinner = findViewById(R.id.spinner);
        packs = findViewById(R.id.pack);
        packs.setText("1");

        String Qt = packs.getText().toString();
        int quantity =Integer.parseInt(Qt);


        ArrayAdapter<String> dlist = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        dlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner.setAdapter(dlist);

        bookbtn = findViewById(R.id.bookBtn);

        progressDialog = new ProgressDialog(BookingPageActivity.this);

            bookbtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    if(quantity<1) {
                        Toast.makeText(BookingPageActivity.this, "Minimun Packs at leats 1", Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog.setMessage("Booking......");
                        auth = FirebaseAuth.getInstance();
                        String user_id = auth.getCurrentUser().getUid();

                        Date date = new Date();
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat chiltime = new SimpleDateFormat("dd-MM-yyyy");
                        String datebook = chiltime.format(date);
                        DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Booking").child(user_id);

                        String dFrom = mspinner.getSelectedItem().toString();

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
                        String strDate = formatter.format(date);

                        int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);

                        // User Details
                        HashMap<String, Object> newPost = new HashMap<>();
                        newPost.put("bookingId", "" + strDate + randomNum + postId);
                        newPost.put("pacakageId", postId);
                        newPost.put("userId", user_id);
                        newPost.put("dipartureFrom", dFrom);
                        newPost.put("quantity", quantity);
                        newPost.put("createAt", datebook);


                        DB.push().setValue(newPost);
                        progressDialog.show();

                        Toast.makeText(BookingPageActivity.this, "Booking Done", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(BookingPageActivity.this, ThankYouActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });


        }
    }
