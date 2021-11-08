package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewProspectors extends AppCompatActivity {
    ArrayList<prospector> Prospector = new ArrayList<>();
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    Button search;
    private EditText search_text;
    String Branch;
    String userName;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prospectors);


        fillList(search);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    }




    public void fillList(View view){
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        Branch = bundle.getString("Branch");



        search_text = findViewById(R.id.searchText);

        String search = search_text.getText().toString();
       // Toast.makeText(viewProspectors.this,  " data Added  "+search, Toast.LENGTH_SHORT).show();
        //search = "ka";

        ListView listView = (ListView)findViewById(R.id.testList);
       // prospector c = new prospector();




        db.collection("Prospectors").document(Branch).collection(userName).orderBy("Name").startAt(search).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
             //   Toast.makeText(viewProspectors.this,  "Passed", Toast.LENGTH_SHORT).show();
                Prospector.clear();
                for (DocumentSnapshot snapshot :queryDocumentSnapshots){

                    Prospector.add(snapshot.toObject(prospector.class));
                 //   Toast.makeText(viewProspectors.this, "Fail", Toast.LENGTH_SHORT).show();

                    prospectorAdapter  myAdapter = new prospectorAdapter(viewProspectors.this,Prospector);
                    listView.setAdapter(myAdapter);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(viewProspectors.this, " Fail", Toast.LENGTH_SHORT).show();
            }
        });



        //}
        prospectorAdapter  myAdapter = new prospectorAdapter(viewProspectors.this,Prospector);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(viewProspectors.this,""+ myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
               // Toast.makeText(viewProspectors.this,""+ position, Toast.LENGTH_SHORT).show();
                // String selectedItem = (String) parent.getItemAtPosition(position);
              //  Toast.makeText(viewProspectors.this,""+myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(viewProspectors.this,prospectorDetails.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });



    }
    public void gettingData(){


    }}

