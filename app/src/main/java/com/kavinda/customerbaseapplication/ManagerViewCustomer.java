package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ManagerViewCustomer extends AppCompatActivity {
String userName;
ProgressBar progressBar;
String Branch;
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    ArrayList<String> names = new ArrayList<>();
    Spinner spin;
    RadioGroup radioBranch;
    String clientBranch;
    ArrayAdapter aa;
    String Position;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_customer);
        Bundle bundle = getIntent().getExtras();
        Position = bundle.getString("Position");
        Branch = bundle.getString("Branch");
            progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        radioBranch = findViewById(R.id.clientBranch);
        if (Position.equals("SalesManager")){
            radioBranch.getChildAt(1).setEnabled(false);
            radioBranch.getChildAt(2).setEnabled(false);
            radioBranch.getChildAt(0).setEnabled(false);

        }
//           radioBranch.getChildAt(0).setEnabled(false);
//            radioBranch.getChildAt(2).setEnabled(false);
//            radioBranch.getChildAt(0).setEnabled(false);



        try {
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });


//            mAdView = findViewById(R.id.adView);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {}
            });

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-1069698192639340/4644371721");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
          //  clientBranch = ((RadioButton)findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
            spin = (Spinner) findViewById(R.id.spinnerUsers);
         //   radioBranch = findViewById(R.id.clientBranch);
          //   clientBranch = ((RadioButton)findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
             aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,names);
            db.collection("Users").whereEqualTo("Branch",Branch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    //Toast.makeText(ManagerViewCustomer.this,  "Passed", Toast.LENGTH_SHORT).show();

                    for (DocumentSnapshot snapshot :queryDocumentSnapshots){
                     //   Toast.makeText(ManagerViewCustomer.this, "Fail", Toast.LENGTH_SHORT).show();

                        names.add(snapshot.get("Username").toString());


                    }

                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spin.setAdapter(aa);
                    progressBar.setVisibility(View.INVISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //  Toast.makeText(ManagerViewCustomer.this, " Fail", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Toast.makeText(ManagerViewCustomer.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("TAG", e.toString() );
        }

    }


    public void viewDetaiils(View v){
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
       names.clear();

        radioBranch = findViewById(R.id.clientBranch);
        clientBranch = ((RadioButton)findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();



        try {
            if(Position.equals("SalesManager")){
                db.collection("Users").whereEqualTo("Branch",Branch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     //   Toast.makeText(ManagerViewCustomer.this,  "Passed", Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot snapshot :queryDocumentSnapshots){
                           // Toast.makeText(ManagerViewCustomer.this, "Fail", Toast.LENGTH_SHORT).show();

                            names.add(snapshot.get("Username").toString());


                        }

                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spin.setAdapter(aa);
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                   //     Toast.makeText(ManagerViewCustomer.this, " Fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }else if(Position.equals("Manager")){

                db.collection("Users").whereEqualTo("Branch",clientBranch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       // Toast.makeText(ManagerViewCustomer.this,  "Passed", Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot snapshot :queryDocumentSnapshots){
                          //  Toast.makeText(ManagerViewCustomer.this, "Fail", Toast.LENGTH_SHORT).show();

                            names.add(snapshot.get("Username").toString());


                        }

                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spin.setAdapter(aa);
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                     //   Toast.makeText(ManagerViewCustomer.this, " Fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }


          // String  userName = spin.getSelectedItem().toString();
          //  Toast.makeText(ManagerViewCustomer.this, userName +"  "+ clientBranch, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            Toast.makeText(ManagerViewCustomer.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("TAG", e.toString() );


        }

    }



    public  void loadList(View v){
        if(Position.equals("Manager")){
            radioBranch = findViewById(R.id.clientBranch);
            String br = ((RadioButton)findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
            String  userName = spin.getSelectedItem().toString();
            String b  = "GMP";
            //Toast.makeText(ManagerViewCustomer.this, userName +"  "+ Branch, Toast.LENGTH_SHORT).show();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }


            Intent intent = new Intent(ManagerViewCustomer.this, viewCustomers.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("Name",userName);
            bundle2.putString("Branch",br);
            intent.putExtras(bundle2);
            startActivity(intent);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



        }else if (Position.equals("SalesManager")){
            String  userName = spin.getSelectedItem().toString();
            String b  = "GMP";
            //Toast.makeText(ManagerViewCustomer.this, userName +"  "+ Branch, Toast.LENGTH_SHORT).show();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

            Intent intent = new Intent(ManagerViewCustomer.this, viewCustomers.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("Name",userName);
            bundle2.putString("Branch",Branch);
            intent.putExtras(bundle2);
            startActivity(intent);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        }

    }

    public void viewAll(View view) {

        if(Position.equals("Manager")){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            Intent intent = new Intent(ManagerViewCustomer.this,ViewAllCustomers.class);
            startActivity(intent);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else{
            Toast.makeText(ManagerViewCustomer.this, "Permisstion Not Granted", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }

    }


}