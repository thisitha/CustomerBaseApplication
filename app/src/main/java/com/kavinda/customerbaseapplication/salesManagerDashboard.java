package com.kavinda.customerbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class salesManagerDashboard extends AppCompatActivity {
String userName;
String Branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager_dashboard);
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        Branch = bundle.getString("Branch");
    }

    public void viewCustomer(View v){

    }

    public void addNewSalesManager(View view) {
        Intent intent  = new Intent(salesManagerDashboard.this,addUser.class);
        Bundle bundle3 = new Bundle();

        bundle3.putString("Branch", Branch);
        intent.putExtras(bundle3);
        startActivity(intent);
    }

    public void viewCustomers(View view) {
        Intent intent  = new Intent(salesManagerDashboard.this,addUser.class);
        Bundle bundle3 = new Bundle();

        bundle3.putString("Branch", Branch);
        intent.putExtras(bundle3);
        startActivity(intent);
    }
    public void editProfile(View view){
        Intent intent = new Intent(salesManagerDashboard.this, clientEditProfile.class);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Name", userName);
        bundle3.putString("Branch", Branch);
        intent.putExtras(bundle3);
        startActivity(intent);
    }

    public void viewCustomersList(View view) {
        Intent intent = new Intent(salesManagerDashboard.this, ManagerViewCustomer.class);

        Bundle bundle3 = new Bundle();

        bundle3.putString("Branch", Branch);
        bundle3.putString("Position","SalesManager");
        intent.putExtras(bundle3);
        startActivity(intent);
    }

    public void ProspectorList(View view) {
        Intent intent = new Intent(salesManagerDashboard.this, ManagerViewProspector.class);

        Bundle bundle3 = new Bundle();

        bundle3.putString("Branch", Branch);
        bundle3.putString("Position","SalesManager");
        intent.putExtras(bundle3);
        startActivity(intent);
    }
}