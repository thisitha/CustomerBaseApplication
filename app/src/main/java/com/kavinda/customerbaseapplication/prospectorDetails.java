package com.kavinda.customerbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class prospectorDetails extends AppCompatActivity {
    EditText name ;
    EditText address ;
    EditText contact ;
    EditText date ;
    EditText occupation ;
    EditText dob ;
    EditText dependents ;
    EditText income ;
    EditText adate ;
    EditText atime ;

    EditText aaddress ;
    EditText remarks ;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospector_details);
        name = findViewById(R.id.prospectorname);
        address = findViewById(R.id.prospectorAddress);
        contact = findViewById(R.id.prospectorContact);
        date = findViewById(R.id.prospectorDate);
        occupation = findViewById(R.id.prospectorOccupation);
        dob = findViewById(R.id.prospectorDOB);
        dependents = findViewById(R.id.prospectorDependents);
        income = findViewById(R.id.prospectorIncome);
        adate = findViewById(R.id.prospectorADate);
        atime = findViewById(R.id.prospectorATime);
        aaddress = findViewById(R.id.prospectorAAddress);
        remarks = findViewById(R.id.prospectorRemarks);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        Bundle bundle = getIntent().getExtras();
        String Name = bundle.getString("Name");
        String Address = bundle.getString("Address");
        String Contact = bundle.getString("Contact");
        String Date = bundle.getString("Date");
        String Occupation = bundle.getString("Occupation");
        String DOB = bundle.getString("DOB");
        String Dependents = bundle.getString("Dependents");
        String Income = bundle.getString("Income");
        String Remarks = bundle.getString("Remarks");
        String ADate = bundle.getString("ADate");
        String ATime = bundle.getString("ATime");
        String AAddress = bundle.getString("AAddress");

        name.setText("Name:- "+Name);
        address.setText("Address:-"+Address);
        contact.setText("Contact:- "+Contact);
        date.setText("Date:- "+Date);
        occupation.setText("Occupation:- "+Occupation);
        dob.setText("DOB:- "+DOB);
        dependents.setText("Dependents:- "+Dependents);
        income.setText("Income:- "+Income);
        remarks.setText("Remarks:- "+Remarks);
        aaddress.setText("Appointment Address:- "+AAddress);
        atime.setText("Appointment Time:- "+ATime);
        adate.setText("Appointment Date:- "+ADate);


    }
}