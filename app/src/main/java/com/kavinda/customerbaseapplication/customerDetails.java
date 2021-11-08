package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import java.util.jar.Attributes;

import javax.microedition.khronos.egl.EGLDisplay;

import static android.widget.Toast.LENGTH_SHORT;

public class customerDetails extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextContact;
    private EditText editTextDate;
    private EditText editTextOccupation;
    private EditText editTextDOB;
    //  private EditText editTextDependents;
    private EditText editTextIncome;
    private EditText editTextRemarks;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
    private EditText editTextGender;
    private EditText editTextCivil;
    private EditText editTextMode;
    private EditText editTextMonth;
    private EditText editTextAmount;

    private String username;
    private String Branch;
    private String Name;
    private AdView mAdView;
    String PolicyNumber;
    String name = "admin123";


    //private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String branchCode;
    private String userName;

    //    Bundle bundle = getIntent().getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        editTextName = findViewById(R.id.customerName);
        editTextContact = findViewById(R.id.customerContact);
        editTextAddress = findViewById(R.id.customerAddress);
        editTextDate = findViewById(R.id.customerDate);
        editTextOccupation = findViewById(R.id.customerOccupation);
        editTextDOB = findViewById(R.id.customerDOB);
        editTextCivil = findViewById(R.id.customerCivil);
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
        editTextGender = findViewById(R.id.customerGender);
        editTextMode = findViewById(R.id.customerMode);

        editTextMonth = findViewById(R.id.premiumMonth);
        editTextAmount = findViewById(R.id.premium);

        try {
            Bundle bundle = getIntent().getExtras();
            Name = bundle.getString("Name");
            String Address = bundle.getString("Address");
            String Contact = bundle.getString("Contact");
            String Date = bundle.getString("Date");
            String Occupation = bundle.getString("Occupation");
            String IDCARD = bundle.getString("ID_Card");
            String DOB = bundle.getString("DOB");
            String Civil = bundle.getString("Civil_Status");
            String Gender = bundle.getString("Gender");
            String Income = bundle.getString("Income");
            String Remarks = bundle.getString("Remarks");
            String SpouseDOB = bundle.getString("SpouseDOB");
            String child1 = bundle.getString("Child1");
            String child2 = bundle.getString("Child2");
            String child3 = bundle.getString("Child3");
            String child4 = bundle.getString("Child4");
            String Policyname = bundle.getString("Policy_name");
            PolicyNumber = bundle.getString("Policy_number");
            String PremiumAmmount = bundle.getString("Premium");
            String Term = bundle.getString("Term");
            String Expiary = bundle.getString("Expiary");
            String Mode = bundle.getString("Mode");
            username = bundle.getString("UserName");
            Branch = bundle.getString("Branch");


            editTextName.setText("Name :- " + Name);

            editTextContact.setText("Contact :- " + Contact);
            editTextAddress.setText("Address :- " + Address);
            editTextDate.setText("Date :- " + Date);
            editTextOccupation.setText("Occupation :- " + Occupation);
            editTextDOB.setText("DOB :-" + DOB);
            editTextCivil.setText("Civil Status :- " + Civil);
            editTextIncome.setText("Income :- " + Income);
            editTextRemarks.setText("Remarks :- " + Remarks);
            editTextIDCard.setText("ID Number :- " + IDCARD);
            editTextSpouceDOB.setText("Spouse DOB :- " + SpouseDOB);
            editTextChild1.setText("Child 1 :-" + child1);
            editTextChild2.setText("Child 2 :-" + child2);
            editTextChild3.setText("Child 3 :-" + child3);
            editTextChild4.setText("Chils 4 :- " + child4);
            editTextPolicyName.setText("Policy Name :-" + Policyname);
            editTextPolicyNumber.setText("Policy Number :- " + PolicyNumber);
            editTextPremiumAmount.setText("Premium :- " + PremiumAmmount);
            editTextTerm.setText("Term :- " + Term);
            editTextExpiary.setText("Expiary :- " + Expiary);
            editTextGender.setText("Gender :- " + Gender);
            editTextMode.setText("Mode :- " + Mode);
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

//            mAdView = findViewById(R.id.adView);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);
        } catch (Exception e) {
           // Toast.makeText(customerDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    public void makePayment(View v) {
        String month = editTextMonth.getText().toString();
        String amt = editTextAmount.getText().toString();

        Map<String, Object> newPayment = new HashMap<>();
        newPayment.put("Amount", amt);
        newPayment.put("Month", month);
        try {
            if (month.isEmpty() || amt.isEmpty()) {
                Toast.makeText(customerDetails.this, "Fill Out The Month And The Amount", Toast.LENGTH_SHORT).show();
            } else {
                db.collection("Customers").document(Branch).collection(username).document(PolicyNumber).collection(PolicyNumber + "_Payment").document(month + "_Payment").set(newPayment).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                              //  Toast.makeText(customerDetails.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                       ///         Toast.makeText(customerDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        } catch (Exception e) {

          //  Toast.makeText(customerDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    public void showHistory(View v) {
       // String name = "admin123";l
        try {
            Bundle bundle1 = new Bundle();
            bundle1.putString("UserName", username);
            bundle1.putString("Branch", Branch);
            bundle1.putString("Policy", PolicyNumber);
            //     bundle1.putString("hello","admin123");

            Intent intent = new Intent(customerDetails.this, ViewPaymentHistory.class);
            intent.putExtras(bundle1);

            startActivity(intent);
        }catch (Exception e) {

        }

    }
}