package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewCustomers extends AppCompatActivity {
    ArrayList<customer> Customer = new ArrayList<>();
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    Button search;
    private  EditText search_text;
    String Branch;
    private AdView mAdView;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);


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
      // Toast.makeText(viewCustomers.this,  " data Added  "+search, Toast.LENGTH_SHORT).show();
        //search = "ka";

        ListView listView = (ListView)findViewById(R.id.testList);
    //    customer c = new customer();

       //for (int i = 0; i <2 ; i++) {
         //  c = new customer(name+i,address+i,contact+i,dob+i,date+i,dependents+i,income+i,remarks+i);
         //  Customer.add(c);
          // Toast.makeText(viewCustomers.this,  " data Added  "+i, Toast.LENGTH_SHORT).show();
//.orderBy("Policy_number").startAt(search)

//.orderBy("Policy_number").startAt(search)
       try {
           db.collection("Customers").document(Branch).collection(userName).orderBy("Policy_number").startAt(search).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
               @Override
               public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                  // Toast.makeText(viewCustomers.this,  "Passed", Toast.LENGTH_SHORT).show();
                   Customer.clear();
                   for (DocumentSnapshot snapshot :queryDocumentSnapshots){
                       Toast.makeText(viewCustomers.this, "Fail", Toast.LENGTH_SHORT).show();
                       Customer.add(snapshot.toObject(customer.class));




                       customerAdapter  myAdapter = new customerAdapter(viewCustomers.this,Customer);
                       listView.setAdapter(myAdapter);
                   }

               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(viewCustomers.this, " Fail", Toast.LENGTH_SHORT).show();
               }
           });


       }catch (Exception e){
           Toast.makeText(viewCustomers.this, e.toString(), Toast.LENGTH_SHORT).show();
       }


       //}
       customerAdapter  myAdapter = new customerAdapter(viewCustomers.this,Customer);
       listView.setAdapter(myAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //   Toast.makeText(viewCustomers.this,""+ myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            //   Toast.makeText(viewCustomers.this,""+ position, Toast.LENGTH_SHORT).show();
           //    String selectedItem = (String) parent.getItemAtPosition(position);
             //  Toast.makeText(viewCustomers.this,""+myAdapter.getItenUsername(position), Toast.LENGTH_SHORT).show();
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


               Intent intent = new Intent(viewCustomers.this,customerDetails.class);
               intent.putExtras(bundle1);
               startActivity(intent);


           }
       });



    }

}