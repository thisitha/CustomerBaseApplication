package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addSalesManager extends AppCompatActivity {
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
    private ProgressBar progressBar;
    private AdView mAdView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_manager);

        editTextClientName = findViewById(R.id.clientName);
        editTextClientEmail = findViewById(R.id.clientEmail);
        editTextClientCode = findViewById(R.id.clientCode);
        editTextClientUsername = findViewById(R.id.clientUsername);
        editTextClientPassword = findViewById(R.id.clientPassword);
        editTextClientConfirmPassword = findViewById(R.id.clientConfirmPassword);
        radioGroupClientBranch = findViewById(R.id.clientBranch);
        radioGroupClientGender = findViewById(R.id.clientGender);
        progressBar = findViewById(R.id.progressAddSales);
        progressBar.setVisibility(View.INVISIBLE);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

    public void addNewSalesManager(View v) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        String clientName = editTextClientName.getText().toString();
        String clientEmail = editTextClientEmail.getText().toString();
        String clientCode = editTextClientCode.getText().toString();
        String clientUsername = editTextClientUsername.getText().toString();
        String clientPassword = editTextClientPassword.getText().toString();
        String clientConfirmPassword = editTextClientConfirmPassword.getText().toString();
        String clientBranch = ((RadioButton)findViewById(radioGroupClientBranch.getCheckedRadioButtonId())).getText().toString();
        String clientGender = ((RadioButton)findViewById(radioGroupClientGender.getCheckedRadioButtonId())).getText().toString();

        if(clientName.isEmpty()||clientEmail.isEmpty()||clientCode.isEmpty()||clientUsername.isEmpty()||clientPassword.isEmpty()||clientConfirmPassword.isEmpty()
        ||clientBranch.isEmpty()||clientGender.isEmpty()){
            Toast.makeText(addSalesManager.this, "Please Fill All", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else if(clientConfirmPassword.equals(clientPassword)){
            try {
                Map<String, Object> newUser = new HashMap<>();
                newUser.put(KEY_NAME,clientName);
                newUser.put(KEY_EMAIL,clientEmail);
                newUser.put(KEY_CODE,clientCode);
                newUser.put(KEY_USERNAME,clientUsername);
                newUser.put(KEY_PASSWORD,clientPassword);
                newUser.put(KEY_BRANCH,clientBranch);
                newUser.put(KEY_GENDER,clientGender);
                newUser.put("Access","Allowed");
                newUser.put("Position","salesManager");
                newUser.put(KEY_BRANCH,clientBranch);
                db.collection("Users").document(clientUsername).set(newUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addSalesManager.this, "New Client has been Saved", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addSalesManager.this, "Error Client Is already Existing Contact admin", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        });
            }catch (Exception e){
                Toast.makeText(addSalesManager.this, e.toString(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        }else{
            Toast.makeText(addSalesManager.this, "Confirm Password Missmatch", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        // Toast.makeText(addUser.this, "Name"+clientGender+" Branch"+ clientBranch, Toast.LENGTH_LONG).show();
    }
}