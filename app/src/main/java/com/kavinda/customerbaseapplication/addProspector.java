package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addProspector extends AppCompatActivity {
    private static String KEY_NAME = "Name";
    private static String KEY_ADDRESS = "Address";
    private static String KEY_CONTACT = "Contact";
    private static String KEY_DATE = "Date";
    private static String KEY_OCCUPATION = "Occupation";
    private static String KEY_DOB = "DOB";
    private static String KEY_DEPENDENTS = "Dependents";
    private static String KEY_INCOME = "Income";
    private static String KEY_ADate = "appointmentDate";
    private static String KEY_ATime = "appointmentTime";
    private static String KEY_AAddress = "appointmentAddress";
    private static String KEY_REMARKS = "Remarks";
    private String userName;
    private String branch;
    int hour, minute;
    private ProgressBar progressBar;


    private EditText editTextprospectorName;
    private EditText editTextprospectorAddress;
    private EditText editTextprospectorContact;
    private EditText editTextprospectorDate;
    private EditText editTextprospectorOccupation;
    private EditText editTextprospectorDOB;
    private EditText editTextprospectorDependents;
    private EditText editTextprospectorIncome;
    private EditText editTextprospectorADate;
    private EditText editTextprospectorATime;
    private EditText editTextprospectorAAddress;
    private EditText editTextprospectorRemarks;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatePicker picker;
    private DatePicker AppointmentDatePicker;
    private TimePicker ATimerPicker;
    String newProspectorDOB;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prospector);
        picker = (DatePicker) findViewById(R.id.datePicker1);
        ATimerPicker = (TimePicker) findViewById(R.id.timePicker1);

        AppointmentDatePicker = (DatePicker) findViewById(R.id.datePicker2);
        editTextprospectorName = findViewById(R.id.prospectorname);
        editTextprospectorAddress = findViewById(R.id.prospectorAddress);
        editTextprospectorContact = findViewById(R.id.prospectorContact);
    //    editTextprospectorDate = findViewById(R.id.prospectorDate);
        editTextprospectorOccupation = findViewById(R.id.prospectorOccupation);
        editTextprospectorDOB = findViewById(R.id.prospectorDOB);
        editTextprospectorDependents = findViewById(R.id.prospectorDependents);
        editTextprospectorIncome = findViewById(R.id.prospectorIncome);
        editTextprospectorADate = findViewById(R.id.prospectorADate);
        editTextprospectorATime = findViewById(R.id.prospectorATime);
        editTextprospectorAAddress = findViewById(R.id.prospectorAAddress);
        editTextprospectorRemarks = findViewById(R.id.prospectorRemarks);
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        branch = bundle.getString("Branch");
        progressBar = findViewById(R.id.progressAddProspecctor);
        progressBar.setVisibility(View.INVISIBLE);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1069698192639340/5111275588");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    }

    public void addProspector(View v) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        String am_pm;
        if (Build.VERSION.SDK_INT >= 23) {
            hour = ATimerPicker.getHour();
            minute = ATimerPicker.getMinute();
        } else {
            hour = ATimerPicker.getCurrentHour();
            minute = ATimerPicker.getCurrentMinute();
        }



    String AppointmentTime = String.valueOf(hour) + ":" + String.valueOf(minute);
    String prospectorname = editTextprospectorName.getText().toString();
    String prospectorAddress = editTextprospectorAddress.getText().toString();
    String prospectorContact = editTextprospectorContact.getText().toString();
    String prospectorDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

    String prospectorOccupation = editTextprospectorOccupation.getText().toString();
    String prospectorDOB = (picker.getYear() + "/" + (picker.getMonth() + 1) + "/" + picker.getDayOfMonth());
    String prospectorDependents = editTextprospectorDependents.getText().toString();
    String prospectorIncome = editTextprospectorIncome.getText().toString();
    String prospectorADate = (AppointmentDatePicker.getYear() + "/" + (AppointmentDatePicker.getMonth() + 1) + "/" + AppointmentDatePicker.getDayOfMonth());
    String prospectorATime = AppointmentTime;
    String prospectorAAddress = editTextprospectorAAddress.getText().toString();
    String prospectorRemarks = editTextprospectorRemarks.getText().toString();

    Map<String, Object> newProspector = new HashMap<>();

        newProspector.put(KEY_NAME,prospectorname);
        newProspector.put(KEY_ADDRESS,prospectorAddress);
        newProspector.put(KEY_AAddress,prospectorAAddress);
        newProspector.put(KEY_CONTACT,prospectorContact);
        newProspector.put(KEY_DATE,prospectorDate);
        newProspector.put(KEY_OCCUPATION,prospectorOccupation);
        newProspector.put(KEY_DOB,prospectorDOB);
        newProspector.put(KEY_DEPENDENTS,prospectorDependents);
        newProspector.put(KEY_INCOME,prospectorIncome);
        newProspector.put(KEY_ADate,prospectorADate);
        newProspector.put(KEY_ATime,prospectorATime);
        newProspector.put(KEY_AAddress,prospectorAAddress);
        newProspector.put(KEY_REMARKS,prospectorRemarks);

        try

    {
        db.collection("Prospectors").document(branch).collection(userName).document(prospectorname).set(newProspector)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addProspector.this, "Prospector has Been added Successfully", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addProspector.this, e.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                });


        editTextprospectorName.setText("");
        editTextprospectorAddress.setText("");
        editTextprospectorContact.setText("");
        //    editTextprospectorDate = findViewById(R.id.prospectorDate);
        editTextprospectorOccupation.setText("");
        editTextprospectorDOB = findViewById(R.id.prospectorDOB);
        editTextprospectorDependents.setText("");
        editTextprospectorIncome.setText("");
        editTextprospectorADate.setText("");
        editTextprospectorATime.setText("");
        editTextprospectorAAddress.setText("");
        editTextprospectorRemarks.setText("");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }catch(
    Exception e)

    {
        Toast.makeText(addProspector.this, e.toString(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }



    }

}