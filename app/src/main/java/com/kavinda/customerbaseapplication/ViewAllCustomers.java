package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewAllCustomers extends AppCompatActivity {
    ArrayList<customer> Customer = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button search;
    private EditText search_text;
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
        setContentView(R.layout.activity_view_all_customers);
        listView = (ListView) findViewById(R.id.teseList);

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
                       // Toast.makeText(ViewAllCustomers.this, "Passed", Toast.LENGTH_SHORT).show();
                        noOfUsers = 0;
                        String Username;

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Username = snapshot.get("Username").toString();
                            //   arrayList.add(Username);

                           // Toast.makeText(ViewAllCustomers.this, Username, Toast.LENGTH_SHORT).show();

                            db.collection("Customers").document(finalBranch).collection(Username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    //Toast.makeText(ViewAllCustomers.this, "Passed", Toast.LENGTH_SHORT).show();
                                    //  Customer.clear();
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        //Toast.makeText(ViewAllCustomers.this, "Fail", Toast.LENGTH_SHORT).show();
                                        Customer.add(snapshot.toObject(customer.class));


                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ViewAllCustomers.this, " Fail", Toast.LENGTH_SHORT).show();
                                }
                            });


                            // noOfUsers = noOfUsers + 1;

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewAllCustomers.this, " Fail", Toast.LENGTH_SHORT).show();
                    }
                });


            }


        } catch (Exception e) {
            Toast.makeText(ViewAllCustomers.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


   // listView.

    public void hello(View view) {
        customerAdapter myAdapter = new customerAdapter(ViewAllCustomers.this, Customer);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ViewAllCustomers.this,""+ myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                Toast.makeText(ViewAllCustomers.this,""+ position, Toast.LENGTH_SHORT).show();
                //    String selectedItem = (String) parent.getItemAtPosition(position);
               // Toast.makeText(ViewAllCustomers.this,""+myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();
                String Name =myAdapter.getItenUsername(position);
                String Address =myAdapter.getCustomerAddress(position);
                String Contact= myAdapter.getCustomerContact(position);
                String ID_Card =myAdapter.getCustomerIDCard(position);
                String Date = myAdapter.getCutomerDate(position) ;
                String Occupation= myAdapter.getCustomerOccupation(position) ;
                String DOB = myAdapter.getCustomerDOB(position);
                String SpouseDOB = myAdapter.getCustomerSpouseDOB(position);

                String Income = myAdapter.getCustomerIncome(position);
                String Remarks = myAdapter.getCustomerRemarks(position);
                String Gender = myAdapter.getCustomerGender(position);
                String Civil_Status = myAdapter.getCustomerCivil(position);
                String Child1 = myAdapter.getChild1(position);
                String Child2 = myAdapter.getChild2(position);
                String Child3 = myAdapter.getChild3(position);
                String Child4 = myAdapter.getChild4(position);
                String Policy_name = myAdapter.getPolicyName(position);
                String Policy_number = myAdapter.getPolicyNumber(position);
                String Premium = myAdapter.getPremiumAmount(position);
                String Mode = myAdapter.getCustomerMode(position);
                String Term = myAdapter.getCustomerTerm(position);
                String Expiary = myAdapter.getCustomerExpiary(position);



                Bundle bundle1 = new Bundle();
                bundle1.putString("Name",Name);
                bundle1.putString("Address",Address);
                bundle1.putString("Contact",Contact);
                bundle1.putString("DOB",DOB);
                bundle1.putString("Date",Date);
                bundle1.putString("ID_Card",ID_Card);
                bundle1.putString("SpouseDOB",SpouseDOB);
                bundle1.putString("Income",Income);
                bundle1.putString("Remarks",Remarks);
                bundle1.putString("Gender",Gender);
                bundle1.putString("Civil_Status",Civil_Status);
                bundle1.putString("Occupation",Occupation);
                bundle1.putString("Child1",Child1);
                bundle1.putString("Child2",Child2);
                bundle1.putString("Child3",Child3);
                bundle1.putString("Child4",Child4);
                bundle1.putString("Policy_name",Policy_name);
                bundle1.putString("Policy_number",Policy_number);
                bundle1.putString("Premium",Premium);
                bundle1.putString("Mode",Mode);
                bundle1.putString("Term",Term);
                bundle1.putString("Expiary",Expiary);
                bundle1.putString("UserName",userName);
                bundle1.putString("Branch",Branch);


                Intent intent = new Intent(ViewAllCustomers.this,customerDetails.class);
                intent.putExtras(bundle1);
                startActivity(intent);


            }
        });


//


    }


}