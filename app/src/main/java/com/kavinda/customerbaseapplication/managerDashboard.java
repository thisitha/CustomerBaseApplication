package com.kavinda.customerbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class managerDashboard extends AppCompatActivity {
    String userName;
    String Branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        Branch = bundle.getString("Branch");

    }

    public void addNewSalesManager(View view) {
        Intent intent = new Intent(managerDashboard.this, addSalesManager.class);
        Bundle bundle3 = new Bundle();
        bundle3.putString("Name", userName);
        bundle3.putString("Branch", Branch);
        bundle3.putString("Position","Manager");
        intent.putExtras(bundle3);
        managerDashboard.this.startActivity(intent);
    }

    public void addManager(View view) {
        Intent intent = new Intent(managerDashboard.this, addSalesManager.class);
        startActivity(intent);
    }

    public void viewCustomers(View view) {
        Intent intent = new Intent(managerDashboard.this, ManagerViewCustomer.class);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Name", userName);
        bundle3.putString("Branch", Branch);
        bundle3.putString("Position","Manager");
        intent.putExtras(bundle3);
        startActivity(intent);
    }

    public void viewAllProspectors(View view) {
        Intent intent = new Intent(managerDashboard.this, ManagerViewProspector.class);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Name", userName);
        bundle3.putString("Branch", Branch);
        bundle3.putString("Position","Manager");
        intent.putExtras(bundle3);
        startActivity(intent);
    }


    public void EditProfile(View view) {
        Intent intent = new Intent(managerDashboard.this, clientEditProfile.class);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Name", userName);
        bundle3.putString("Branch", Branch);
        intent.putExtras(bundle3);
        startActivity(intent);
    }
}

//                                    Bundle bundle3 = new Bundle();
//                                        bundle3.putString("Name",user);
//                                                bundle3.putString("Branch",branch);
//                                                intent.putExtras(bundle3);