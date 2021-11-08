package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addCustomer extends AppCompatActivity {
    private static String KEY_NAME = "Name";
    private static String KEY_ADDRESS = "Address";
    private static String KEY_CONTACT = "Contact";
    private static String KEY_IDENTITY = "ID_Card";
    private static String KEY_DATE = "Date";
    private static String KEY_OCCUPATION = "Occupation";
    private static String KEY_DOB = "DOB";
    private static String KEY_SPOUSE = "SpouseDOB";

    private static String KEY_INCOME = "Income";
    private static String KEY_REMARKS = "Remarks";
    private static String KEY_GENDER = "Gender";
    private static String KEY_CIVIL = "Civil_Status";
    private static String KEY_CHILD1 = "Child1";
    private static String KEY_CHILD2 = "Child2";
    private static String KEY_CHILD3 = "Child3";
    private static String KEY_CHILD4 = "Child4";
    private static String KEY_POLICY_NAME = "Policy_name";
    private static String KEY_POLICY_NUMBER = "Policy_number";
    private static String KEY_PREMIUM = "Premium";
    private static String KEY_MODE = "Mode";
    private static String KEY_TERM = "Term";
    private static String KEY_EXPIARY = "Expiary";


    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextContact;
    private EditText editTextDate;
    private EditText editTextOccupation;
    private EditText editTextDOB;
    //  private EditText editTextDependents;
    private EditText editTextIncome;
    private EditText editTextRemarks;

    private EditText editTextIDCard;
    private EditText editTextSpouceDOB;
    private EditText editTextChild1;
    private EditText editTextChild2;
    private EditText editTextChild3;
    private EditText editTextChild4;
    private EditText editTextPolicyName;
    private EditText editTextPolicyNumber;
    private EditText editTextPremiumAmount;
    private EditText editTextTerm;
    private EditText editTextExpiary;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String branchCode;
    private String userName;
    private RadioGroup radioGroupGender;
    private RadioGroup radioGroupCivil;
    private RadioGroup radioGroupMode;
    private RadioButton radioGender;
    private RadioButton radioCivil;
    private RadioButton radioMode;
    private DatePicker CustomerDOB;
    private DatePicker spouceDOB;
    private DatePicker child1DOB;
    private DatePicker child2DOB;
    private DatePicker child3DOB;
    private DatePicker child4DOB;
    private ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private AdView mAdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        try {
            editTextName = findViewById(R.id.customerName);
            editTextContact = findViewById(R.id.customerContact);
            editTextAddress = findViewById(R.id.customerAddress);
            editTextDate = findViewById(R.id.customerDate);
            editTextOccupation = findViewById(R.id.customerOccupation);
            editTextDOB = findViewById(R.id.customerDOB);
            //    editTextDependents = findViewById(R.id.customerDependents);
            editTextIncome = findViewById(R.id.customerIncome);
            editTextRemarks = findViewById(R.id.customerRemarks);
            editTextIDCard = findViewById(R.id.customerID);
            editTextSpouceDOB = findViewById(R.id.customerSpouseDOB);
            editTextChild1 = findViewById(R.id.child1DOB);
            editTextChild2 = findViewById(R.id.child2DOB);
            editTextChild3 = findViewById(R.id.child3DOB);
            editTextChild4 = findViewById(R.id.child4DOB);
            editTextPolicyName = findViewById(R.id.customerPolicyName);
            editTextPolicyNumber = findViewById(R.id.customerPolicyNumber);
            editTextPremiumAmount = findViewById(R.id.customerPremium);
            editTextTerm = findViewById(R.id.customerTerm);
            editTextExpiary = findViewById(R.id.customerExpiary);


            radioGroupGender = (RadioGroup) findViewById(R.id.radioCustomerGender);
            radioGroupCivil = (RadioGroup) findViewById(R.id.radioCustomerCivil);
            radioGroupMode = (RadioGroup) findViewById(R.id.radiocustomerMode);

            CustomerDOB = (DatePicker) findViewById(R.id.datePickerCustomerDOB);
            spouceDOB = (DatePicker) findViewById(R.id.datePickerSpouceDOB);

            child1DOB = (DatePicker) findViewById(R.id.datePickerChild1);
            child2DOB = (DatePicker) findViewById(R.id.datePickerChild2);
            child3DOB = (DatePicker) findViewById(R.id.datePickerChild13);
            child4DOB = (DatePicker) findViewById(R.id.datePickerChild14);

 

            Bundle bundle = getIntent().getExtras();
            userName = bundle.getString("Name");
            branchCode = bundle.getString("Branch");
            progressBar = findViewById(R.id.progressAddCustomer);
            progressBar.setVisibility(View.INVISIBLE);


            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {}
            });

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-1069698192639340/4644371721");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

//            mAdView = findViewById(R.id.adView);
//
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);


        }catch (Exception e){
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {}
            });

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.Interstitial_Ad));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

    }


    public void registerCustomer(View v) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        String currDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        String customerName = editTextName.getText().toString();
        String customerContact = editTextContact.getText().toString();
        String customerAddress = editTextAddress.getText().toString();
        String customerDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        String customerOccupation = editTextOccupation.getText().toString();
        String customerDOB = (CustomerDOB.getYear() + "/" + (CustomerDOB.getMonth() + 1) + "/" + CustomerDOB.getDayOfMonth());
//        String customerDependents = editTextDependents.getText().toString();
        String customerIncome = editTextIncome.getText().toString();
        String customerRemarks = editTextRemarks.getText().toString();
        String customerId = editTextIDCard.getText().toString();
        String customerSpouseDOB = (spouceDOB.getYear() + "/" + (spouceDOB.getMonth() + 1) + "/" + spouceDOB.getDayOfMonth());



        String customerChild1 = (child1DOB.getYear() + "/" + (child1DOB.getMonth() + 1) + "/" + child1DOB.getDayOfMonth());
        String customerChild2 = (child2DOB.getYear() + "/" + (child2DOB.getMonth() + 1) + "/" + child2DOB.getDayOfMonth());
        String customerChild3 = (child3DOB.getYear() + "/" + (child3DOB.getMonth() + 1) + "/" + child3DOB.getDayOfMonth());
        String customerChild4 = (child4DOB.getYear() + "/" + (child4DOB.getMonth() + 1) + "/" + child4DOB.getDayOfMonth());
         if(customerChild1.equals(currDate)){
             customerChild1="";
         }else if(customerChild2.equals(currDate)){
             customerChild2 = "";
         }else if(customerChild3.equals(currDate)){
             customerChild3 = "";
         }else if (customerChild4.equals(currDate)){
             customerChild4= "";
         }
        String customerPolicyName = editTextPolicyName.getText().toString();
        String customerPolicyNumber = editTextPolicyNumber.getText().toString();
        String customerPremiumAmount = editTextPremiumAmount.getText().toString();
        String customerTerm = editTextTerm.getText().toString();
        String customerExpiary = editTextExpiary.getText().toString();

        radioGender = (RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId());
        radioCivil = (RadioButton) findViewById(radioGroupCivil.getCheckedRadioButtonId());
        radioMode = (RadioButton) findViewById(radioGroupMode.getCheckedRadioButtonId());
        String customerGender = radioGender.getText().toString();
        String customerCivil = radioCivil.getText().toString();
        String customerMode = radioMode.getText().toString();

        if(customerName.isEmpty()||customerContact.isEmpty()|| customerAddress.isEmpty()|| customerDate.isEmpty()||customerOccupation.isEmpty()
                ||customerDOB.isEmpty() || customerIncome.isEmpty()||customerRemarks.isEmpty() ||customerId.isEmpty() || customerPolicyName.isEmpty()
                || customerPolicyNumber.isEmpty()||customerPremiumAmount.isEmpty() || customerTerm.isEmpty()|| customerExpiary.isEmpty()){
            Toast.makeText(addCustomer.this, "Please Fill Out All The Fields To Avoid Hassles In Future", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else {





            Map<String, Object> newCustomer = new HashMap<>();
            newCustomer.put(KEY_NAME, customerName);
            newCustomer.put(KEY_CONTACT, customerContact);
            newCustomer.put(KEY_ADDRESS, customerAddress);
            newCustomer.put(KEY_DATE, customerDate);
            newCustomer.put(KEY_DOB, customerDOB);
            newCustomer.put(KEY_OCCUPATION, customerOccupation);
            newCustomer.put(KEY_INCOME, customerIncome);
            newCustomer.put(KEY_REMARKS, customerRemarks);
            newCustomer.put(KEY_IDENTITY, customerId);
            newCustomer.put(KEY_SPOUSE, customerSpouseDOB);
            newCustomer.put(KEY_CHILD1, customerChild1);
            newCustomer.put(KEY_CHILD2, customerChild2);
            newCustomer.put(KEY_CHILD3, customerChild3);
            newCustomer.put(KEY_CHILD4, customerChild4);
            newCustomer.put(KEY_POLICY_NAME, customerPolicyName);
            newCustomer.put(KEY_POLICY_NUMBER, customerPolicyNumber);
            newCustomer.put(KEY_PREMIUM, customerPremiumAmount);
            newCustomer.put(KEY_TERM, customerTerm);
            newCustomer.put(KEY_EXPIARY, customerExpiary);
            newCustomer.put(KEY_GENDER, customerGender);
            newCustomer.put(KEY_CIVIL, customerCivil);
            newCustomer.put(KEY_MODE, customerMode);

            try {
                db.collection("Customers").document(branchCode).collection(userName).document(customerPolicyNumber).set(newCustomer)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(addCustomer.this, "Customer Has been saved", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addCustomer.this, e.toString(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        });

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                }
            } catch (Exception e) {
                Toast.makeText(addCustomer.this, e.toString(), Toast.LENGTH_SHORT).show();
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


}