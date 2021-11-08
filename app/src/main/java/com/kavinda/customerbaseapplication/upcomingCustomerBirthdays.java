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
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class upcomingCustomerBirthdays extends AppCompatActivity {
    ArrayList<customer> Customer = new ArrayList<>();
    ArrayList<customer> customerPremium = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button search;
    private EditText search_text;
    String Branch;
    String userName;
    String currentDate;
    String DOB;
    String Mode;
    String Date ;
private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_customer_birthdays);

        currentDate=new SimpleDateFormat("MM/dd", Locale.getDefault()).format(new Date());
       // Toast.makeText(upcomingCustomerBirthdays.this, currentDate, Toast.LENGTH_SHORT).show();
        fillList(search);
        fillSheduledPremiums(search);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


    }


    public void fillList(View view) {
        Customer.clear();
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        Branch = bundle.getString("Branch");


        search_text = findViewById(R.id.searchText);

        String search = search_text.getText().toString();
        //Toast.makeText(upcomingCustomerBirthdays.this, " data Added  " + search, Toast.LENGTH_SHORT).show();
        //search = "ka";

        ListView listView = (ListView) findViewById(R.id.testList);
        //    customer c = new customer();

        //for (int i = 0; i <2 ; i++) {
        //  c = new customer(name+i,address+i,contact+i,dob+i,date+i,dependents+i,income+i,remarks+i);
        //  Customer.add(c);
        // Toast.makeText(viewCustomers.this,  " data Added  "+i, Toast.LENGTH_SHORT).show();
//.orderBy("Policy_number").startAt(search)

//.orderBy("Policy_number").startAt(search)
        try {
            db.collection("Customers").document(Branch).collection(userName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               //     Toast.makeText(upcomingCustomerBirthdays.this, "Passed", Toast.LENGTH_SHORT).show();
                    Customer.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                       DOB = snapshot.get("DOB").toString();
                       DOB = DOB.substring(5);
                   //     Toast.makeText(upcomingCustomerBirthdays.this, DOB, Toast.LENGTH_SHORT).show();

                       if(currentDate.equals(DOB)){
                     //      Toast.makeText(upcomingCustomerBirthdays.this, "Fail", Toast.LENGTH_SHORT).show();
                           Customer.add(snapshot.toObject(customer.class));


                           customerAdapter myAdapter = new customerAdapter(upcomingCustomerBirthdays.this, Customer);
                           listView.setAdapter(myAdapter);
                       }


                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(upcomingCustomerBirthdays.this, " Fail", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {
            Toast.makeText(upcomingCustomerBirthdays.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        //}
        customerAdapter myAdapter = new customerAdapter(upcomingCustomerBirthdays.this, Customer);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(upcomingCustomerBirthdays.this, "" + myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                Toast.makeText(upcomingCustomerBirthdays.this, "" + position, Toast.LENGTH_SHORT).show();
                //    String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(upcomingCustomerBirthdays.this, "" + myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();
                String Name = myAdapter.getItenUsername(position);
                String Address = myAdapter.getCustomerAddress(position);
                String Contact = myAdapter.getCustomerContact(position);
                String ID_Card = myAdapter.getCustomerIDCard(position);
                String Date = myAdapter.getCutomerDate(position);
                String Occupation = myAdapter.getCustomerOccupation(position);
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
                bundle1.putString("Name", Name);
                bundle1.putString("Address", Address);
                bundle1.putString("Contact", Contact);
                bundle1.putString("DOB", DOB);
                bundle1.putString("Date", Date);
                bundle1.putString("ID_Card", ID_Card);
                bundle1.putString("SpouseDOB", SpouseDOB);
                bundle1.putString("Income", Income);
                bundle1.putString("Remarks", Remarks);
                bundle1.putString("Gender", Gender);
                bundle1.putString("Civil_Status", Civil_Status);
                bundle1.putString("Occupation", Occupation);
                bundle1.putString("Child1", Child1);
                bundle1.putString("Child2", Child2);
                bundle1.putString("Child3", Child3);
                bundle1.putString("Child4", Child4);
                bundle1.putString("Policy_name", Policy_name);
                bundle1.putString("Policy_number", Policy_number);
                bundle1.putString("Premium", Premium);
                bundle1.putString("Mode", Mode);
                bundle1.putString("Term", Term);
                bundle1.putString("Expiary", Expiary);
                bundle1.putString("UserName", userName);
                bundle1.putString("Branch", Branch);


                Intent intent = new Intent(upcomingCustomerBirthdays.this, customerDetails.class);
                intent.putExtras(bundle1);
                startActivity(intent);


            }
        });


    }

        public void fillSheduledPremiums(View v){
            customerPremium.clear();
            ListView premiumsList = (ListView)findViewById(R.id.premiumList);

            try {
                db.collection("Customers").document(Branch).collection(userName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                      //  Toast.makeText(upcomingCustomerBirthdays.this, "Passed", Toast.LENGTH_SHORT).show();
                        customerPremium.clear();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Mode = snapshot.get("Mode").toString();
                            Date = snapshot.get("Date").toString();


                            switch(Mode) {
                                case "Monthly":
                                    Date = Date.substring(5);
                                    if(currentDate.equals(Date)){
                                        customerPremium.add(snapshot.toObject(customer.class));


                                        customerAdapter myAdapter = new customerAdapter(upcomingCustomerBirthdays.this, customerPremium);
                                        premiumsList.setAdapter(myAdapter);
                                    }
                                    break;
                                case "Quartaly":
                                    // code block
                                    break;
                                case "Bi-Anually":
                                    // code block
                                    break;
                                case "Annually":
                                    // code block
                                    break;

                                default:
                                    // code block
                            }





                            //     Toast.makeText(upcomingCustomerBirthdays.this, DOB, Toast.LENGTH_SHORT).show();




                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(upcomingCustomerBirthdays.this, " Fail", Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (Exception e) {
                Toast.makeText(upcomingCustomerBirthdays.this, e.toString(), Toast.LENGTH_SHORT).show();
            }


            //}
            customerAdapter myAdapter = new customerAdapter(upcomingCustomerBirthdays.this, customerPremium);
            premiumsList.setAdapter(myAdapter);
            premiumsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(upcomingCustomerBirthdays.this, "" + myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                    Toast.makeText(upcomingCustomerBirthdays.this, "" + position, Toast.LENGTH_SHORT).show();
                    //    String selectedItem = (String) parent.getItemAtPosition(position);
                    Toast.makeText(upcomingCustomerBirthdays.this, "" + myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();
                    String Name = myAdapter.getItenUsername(position);
                    String Address = myAdapter.getCustomerAddress(position);
                    String Contact = myAdapter.getCustomerContact(position);
                    String ID_Card = myAdapter.getCustomerIDCard(position);
                    String Date = myAdapter.getCutomerDate(position);
                    String Occupation = myAdapter.getCustomerOccupation(position);
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
                    bundle1.putString("Name", Name);
                    bundle1.putString("Address", Address);
                    bundle1.putString("Contact", Contact);
                    bundle1.putString("DOB", DOB);
                    bundle1.putString("Date", Date);
                    bundle1.putString("ID_Card", ID_Card);
                    bundle1.putString("SpouseDOB", SpouseDOB);
                    bundle1.putString("Income", Income);
                    bundle1.putString("Remarks", Remarks);
                    bundle1.putString("Gender", Gender);
                    bundle1.putString("Civil_Status", Civil_Status);
                    bundle1.putString("Occupation", Occupation);
                    bundle1.putString("Child1", Child1);
                    bundle1.putString("Child2", Child2);
                    bundle1.putString("Child3", Child3);
                    bundle1.putString("Child4", Child4);
                    bundle1.putString("Policy_name", Policy_name);
                    bundle1.putString("Policy_number", Policy_number);
                    bundle1.putString("Premium", Premium);
                    bundle1.putString("Mode", Mode);
                    bundle1.putString("Term", Term);
                    bundle1.putString("Expiary", Expiary);
                    bundle1.putString("UserName", userName);
                    bundle1.putString("Branch", Branch);


                    Intent intent = new Intent(upcomingCustomerBirthdays.this, customerDetails.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);


                }
            });

        }

}

