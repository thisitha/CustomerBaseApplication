package com.kavinda.customerbaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class clientDashboard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //  private EditText edit;
    String userName;
    String Branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  edit = findViewById(R.id.clientHead);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("Name");
        Branch = bundle.getString("Branch");
        EditText edtText = (EditText) findViewById(R.id.clientHead);
        edtText.setText("Welcome " + userName);
    }

    public void addCustomer(View v) {

        Intent intent = new Intent(clientDashboard.this, addCustomer.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch", Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }

    public void chatMe(View v){
        Intent intent = new Intent(clientDashboard.this, ChatbotActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch", Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }

    public void addProspector(View v) {
        Intent intent = new Intent(clientDashboard.this, addProspector.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch", Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }
    public void viewCustomer(View v){
        Intent intent = new Intent(clientDashboard.this, viewCustomers.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch", Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }
    public  void viewProspector(View v){
        Intent intent = new Intent(clientDashboard.this, viewProspectors.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch", Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }


    public void  editProfile(View v){
        Intent intent = new Intent(clientDashboard.this, clientEditProfile.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);

        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);
    }


    public void upcomingBirthdays(View view) {
        Intent intent = new Intent(clientDashboard.this, upcomingCustomerBirthdays.class);
        Bundle bundle = new Bundle();
        bundle.putString("Name", userName);
        bundle.putString("Branch",Branch);
        intent.putExtras(bundle);
        clientDashboard.this.startActivity(intent);

    }
}

