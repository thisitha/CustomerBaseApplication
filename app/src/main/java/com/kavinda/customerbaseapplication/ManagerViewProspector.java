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

public class ManagerViewProspector extends AppCompatActivity {
    String Position = "";
    String Branch;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> names = new ArrayList<>();
    Spinner spin;
    RadioGroup radioBranch;
    String clientBranch;
    ArrayAdapter aa;
    ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_prospector);
        Bundle bundle = getIntent().getExtras();
        Position = bundle.getString("Position");
        Branch = bundle.getString("Branch");
        radioBranch = findViewById(R.id.clientBranch);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        if (Position.equals("SalesManager")){
            radioBranch.getChildAt(1).setEnabled(false);
            radioBranch.getChildAt(2).setEnabled(false);
            radioBranch.getChildAt(0).setEnabled(false);

        }
        try {

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

//            mAdView = findViewById(R.id.adView);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);



            //  clientBranch = ((RadioButton)findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
            spin = (Spinner) findViewById(R.id.spinnerUsers);
            radioBranch = findViewById(R.id.clientBranch);
            clientBranch = ((RadioButton) findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
            aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
            // spin.setOnItemSelectedListener(this);

            //Creating the ArrayAdapter instance having the country list

//.whereEqualTo("Branch","KDW")
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            db.collection("Users").whereEqualTo("Branch", Branch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                  //  Toast.makeText(ManagerViewProspector.this, "Passed", Toast.LENGTH_SHORT).show();

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                     //   Toast.makeText(ManagerViewProspector.this, "Fail", Toast.LENGTH_SHORT).show();

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
                   // Toast.makeText(ManagerViewProspector.this, " Fail", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                }
            });


        } catch (Exception e) {
            Toast.makeText(ManagerViewProspector.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("TAG", e.toString());
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

    }


    public void viewDetaiils(View v) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        names.clear();


        clientBranch = ((RadioButton) findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();


        try {
            if (Position.equals("Manager")) {
                db.collection("Users").whereEqualTo("Branch", clientBranch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       // Toast.makeText(ManagerViewProspector.this, "Passed", Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                           // Toast.makeText(ManagerViewProspector.this, "Fail", Toast.LENGTH_SHORT).show();

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
                       // Toast.makeText(ManagerViewProspector.this, " Fail", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                });
            } else if (Position.equals("SalesManager")) {
                db.collection("Users").whereEqualTo("Branch", Branch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       // Toast.makeText(ManagerViewProspector.this, "Passed", Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Toast.makeText(ManagerViewProspector.this, "Fail", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(ManagerViewProspector.this, " Fail", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                });
            }

            // String  userName = spin.getSelectedItem().toString();
            //Toast.makeText(ManagerViewProspector.this, userName + "  " + clientBranch, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Toast.makeText(ManagerViewProspector.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("TAG", e.toString());
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        }

    }


    public void loadList(View v) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        String userName = spin.getSelectedItem().toString();
        //Toast.makeText(ManagerViewProspector.this, userName + "  " + Branch, Toast.LENGTH_SHORT).show();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        if (Position.equals("Manager")) {
            String br = ((RadioButton) findViewById(radioBranch.getCheckedRadioButtonId())).getText().toString();
            Intent intent = new Intent(ManagerViewProspector.this, viewProspectors.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("Name", userName);
            bundle2.putString("Branch", br);
            intent.putExtras(bundle2);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            startActivity(intent);

        }else if(Position.equals("SalesManager")){
            Intent intent = new Intent(ManagerViewProspector.this, viewProspectors.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("Name", userName);
            bundle2.putString("Branch", Branch);
            intent.putExtras(bundle2);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            startActivity(intent);
        }



    }


    public void loadInfo(View view) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (Position.equals("Manager")) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            Intent intent = new Intent(ManagerViewProspector.this, ViewAllProspectors.class);
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            startActivity(intent);
        } else {
            Toast.makeText(ManagerViewProspector.this, "No Permission Granted", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }

    }
}
