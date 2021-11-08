package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class clientEditProfile extends AppCompatActivity {

    private static String KEY_NAME = "Name";
    private static String KEY_EMAIL = "Email";
    private static String KEY_CODE = "Code";
    private static String KEY_GENDER = "Gender";
    private static String KEY_BRANCH = "Branch";
    private static String KEY_USERNAME = "Username";
    private static String KEY_PASSWORD = "Password";

    private EditText editTextClientName;
    private EditText editTextClientEmail;
    private EditText editTextClientCode;
    private RadioGroup radioGroupClientGender;
    private RadioGroup radioGroupClientBranch;
    private EditText editTextClientUsername;
    private EditText editTextClientPassword;
    private EditText editTextClientConfirmPassword;
    String Name ="";
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit_profile);
        editTextClientName = findViewById(R.id.clientName);
        editTextClientEmail = findViewById(R.id.clientEmail);
        editTextClientCode = findViewById(R.id.clientCode);
        editTextClientUsername = findViewById(R.id.clientUsername);
        editTextClientPassword = findViewById(R.id.clientPassword);
        editTextClientConfirmPassword = findViewById(R.id.clientConfirmPassword);
        radioGroupClientBranch = findViewById(R.id.clientBranch);
        radioGroupClientGender = findViewById(R.id.clientGender);

        Bundle bundle = getIntent().getExtras();
       Name =  bundle.getString("Name");
        editTextClientName.setText("Name :- "+Name);
        editTextClientUsername.setText("UserName :-"+Name);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1069698192639340/4644371721");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void addNewClient(View v) {
        String clientName = editTextClientName.getText().toString();

        String clientPassword = editTextClientPassword.getText().toString();
        String clientConfirmPassword = editTextClientConfirmPassword.getText().toString();



        Map<String, Object> newUser = new HashMap<>();
       newUser.put(KEY_NAME, clientName);

     //   newUser.put(KEY_USERNAME, clientUsername);
        newUser.put(KEY_PASSWORD, clientPassword);
     //   newUser.put(KEY_BRANCH, clientBranch);
     //   newUser.put(KEY_GENDER, clientGender);
      //  newUser.put("Access", "Allowed");
     //   newUser.put("Position", "Client");
       // newUser.put(KEY_BRANCH, clientBranch);
try {

//    if(clientName.isEmpty()||clientEmail.isEmpty() ||clientCode.isEmpty() ||clientUsername.isEmpty() ||clientPassword.isEmpty()
//            || clientBranch.isEmpty()|| clientGender.isEmpty()){
     //   Toast.makeText(clientEditProfile.this, "Please Fill Out All The Fields", Toast.LENGTH_LONG).show();
     if (clientPassword.equals(clientConfirmPassword)){
        db.collection("Users").document(Name).update("Password",clientPassword)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(clientEditProfile.this, "Details Updated", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(clientEditProfile.this, "Error Client Is already Existing Contact admin", Toast.LENGTH_LONG).show();
                    }
                });
    }else
    {
        Toast.makeText(clientEditProfile.this, "Confirm Password Do not Match", Toast.LENGTH_LONG).show();
    }

    if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
    } else {
        Log.d("TAG", "The interstitial wasn't loaded yet.");
    }
}catch (Exception e){
    Toast.makeText(clientEditProfile.this, e.toString(), Toast.LENGTH_LONG).show();


}


        // Toast.makeText(addUser.this, "Name"+clientGender+" Branch"+ clientBranch, Toast.LENGTH_LONG).show();
    }
}


