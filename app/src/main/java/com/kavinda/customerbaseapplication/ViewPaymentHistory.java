package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewPaymentHistory extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<payment> Payment = new ArrayList<>();
    String name;
    String Branch;
    String pNumber;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment_history);
        Bundle bundle = getIntent().getExtras();
        Branch = bundle.getString("Branch");
        pNumber = bundle.getString("Policy");
        String n = bundle.getString("UserName");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        try {

            ListView listView = (ListView) findViewById(R.id.paymentList);
            db.collection("Customers").document(Branch).collection(n).document(pNumber).collection(pNumber + "_Payment").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //    Toast.makeText(ViewPaymentHistory.this, "Passed", Toast.LENGTH_SHORT).show();
                    Payment.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {

                        Payment.add(snapshot.toObject(payment.class));
                      //  Toast.makeText(ViewPaymentHistory.this, "Fail", Toast.LENGTH_SHORT).show();
                        paymentAdapter paymentA = new paymentAdapter(Payment, ViewPaymentHistory.this);
                        listView.setAdapter(paymentA);
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ViewPaymentHistory.this, " Fail", Toast.LENGTH_SHORT).show();
                }
            });


            //}
            paymentAdapter myAdapter = new paymentAdapter(Payment, ViewPaymentHistory.this);
            listView.setAdapter(myAdapter);
        } catch (Exception e) {
            Toast.makeText(ViewPaymentHistory.this, e.toString(), Toast.LENGTH_SHORT).show();

        }


    }


}