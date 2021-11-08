package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ViewAllProspectors extends AppCompatActivity {
    ArrayList<prospector> Prospector = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String Branch;
    String userName;
    ListView listView;
    String arrayBranches[] = {"KDW", "GMP", "WAT"};
    String arrayUsers[];
    String User;
    int noOfUsers;
    ArrayList arrayList = new ArrayList();
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_prospectors);
        listView = (ListView)findViewById(R.id.testList);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        try {
            String branch;
            for (int i = 0; i < 3; i++) {
                branch = arrayBranches[i];

                String finalBranch = branch;
                db.collection("Users").whereEqualTo("Branch", branch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    //    Toast.makeText(ViewAllProspectors.this, "Passed", Toast.LENGTH_SHORT).show();
                        noOfUsers = 0;
                        String Username;

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Username = snapshot.get("Username").toString();
                            //   arrayList.add(Username);

                          //  Toast.makeText(ViewAllProspectors.this, Username, Toast.LENGTH_SHORT).show();

                            db.collection("Prospectors").document(finalBranch).collection(Username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                 //   Toast.makeText(ViewAllProspectors.this, "Passed", Toast.LENGTH_SHORT).show();
                                    //  Customer.clear();
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        Toast.makeText(ViewAllProspectors.this, "Fail", Toast.LENGTH_SHORT).show();
                                        Prospector.add(snapshot.toObject(prospector.class));


                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ViewAllProspectors.this, " Fail", Toast.LENGTH_SHORT).show();
                                }
                            });


                            // noOfUsers = noOfUsers + 1;

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewAllProspectors.this, " Fail", Toast.LENGTH_SHORT).show();
                    }
                });


            }


        } catch (Exception e) {
            Toast.makeText(ViewAllProspectors.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    // listView.

    public void hello(View view) {
        prospectorAdapter myAdapter = new prospectorAdapter(ViewAllProspectors.this, Prospector);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(ViewAllProspectors.this,""+ myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(ViewAllProspectors.this,""+ position, Toast.LENGTH_SHORT).show();
                // String selectedItem = (String) parent.getItemAtPosition(position);
             //   Toast.makeText(ViewAllProspectors.this,""+myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();

                String name = myAdapter.getItenUsername(position);
                String address =myAdapter.getProspectorAddress(position);
                String contact=myAdapter.getProspectorContact(position);
                String dob=myAdapter.getProspectorDOB(position);
                String date=myAdapter.getProspectorDate(position);
                String dependents=myAdapter.getProspectorDependents(position);
                String occupation = myAdapter.getProspectorOccupation(position);
                String income=myAdapter.getProspectorIncome(position);
                String remarks=myAdapter.getProspectorRemarks(position);
                String AAddress = myAdapter.getProspectorAAddress(position);
                String ADate = myAdapter.getProspectorsADate(position);
                String ATime = myAdapter.getProspectorATime(position);
                Bundle bundle1 = new Bundle();
                bundle1.putString("Name",name);
                bundle1.putString("Address",address);
                bundle1.putString("Contact",contact);
                bundle1.putString("DOB",dob);
                bundle1.putString("Date",date);
                bundle1.putString("Occupation",occupation);
                bundle1.putString("Dependents",dependents);
                bundle1.putString("Income",income);
                bundle1.putString("Remarks",remarks);

                bundle1.putString("AAddress",AAddress);
                bundle1.putString("ADate",ADate);
                bundle1.putString("ATime",ATime);
                Intent intent = new Intent(ViewAllProspectors.this,prospectorDetails.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });



    }




}