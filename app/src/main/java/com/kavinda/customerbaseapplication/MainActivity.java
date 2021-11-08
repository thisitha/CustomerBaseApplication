package com.kavinda.customerbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static String KEY_TITLE = "username";
    private static String KEY_DESCRIPTION = "password";

    private EditText edittextUsername;
    private EditText edittextPassword;
    private TextView textView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressBar progressBar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        edittextUsername = findViewById(R.id.username);
        edittextPassword = findViewById(R.id.password);
        // textView = findViewById(R.id.textView_text);
       progressBar = findViewById(R.id.loginProgress);
        progressBar.setVisibility(View.INVISIBLE);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    }

    public void registerUser(View v) {

        String uName = edittextUsername.getText().toString();
        String pWord = edittextPassword.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, uName);
        note.put(KEY_DESCRIPTION, pWord);
        DocumentReference noteref = db.collection("users").document(uName);

        //  db.collection("Users").document(uName).set(note)
        noteref.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Note Saved", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        //  Log.d(TAG, e.toString());

                    }
                });

    }


    public void testFunction2(View v) {
      try {
          Intent intent = new Intent(MainActivity.this, viewCustomers.class);
          MainActivity.this.startActivity(intent);
      }catch (Exception e){
          Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
      }

    }

    public void Login(View v) {
        if(!(edittextPassword.getText().toString().isEmpty()||edittextUsername.getText().toString().isEmpty())){
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            String userName = edittextUsername.getText().toString();
            String passWord = edittextPassword.getText().toString();
            db.collection("Users").document(userName).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {

                                String user = documentSnapshot.getString("Username");
                                String pass = documentSnapshot.getString("Password");
                                String position = documentSnapshot.getString("Position");
                                String accessAllowed = documentSnapshot.getString("Access");
                                String branch = documentSnapshot.getString("Branch");


                                if (pass.equals(passWord) & accessAllowed.equals("Allowed")) {
                                    try {
                                        if (position.equals("Client")) {
                                            Toast.makeText(MainActivity.this, user + "  Client Logged IN", Toast.LENGTH_SHORT).show();

                                            edittextPassword.setText("");
                                            edittextUsername.setText("");
                                            Intent intent = new Intent(MainActivity.this, clientDashboard.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Name",user);
                                            bundle.putString("Branch",branch);
                                            intent.putExtras(bundle);
                                            MainActivity.this.startActivity(intent);

                                            progressBar.setVisibility(View.INVISIBLE);
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        } else if (position.equals("Manager")) {

                                            edittextPassword.setText("");
                                            edittextUsername.setText("");
                                            // Toast.makeText(MainActivity.this, "Login Authourized",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, managerDashboard.class);
                                            Bundle bundle2 = new Bundle();
                                            bundle2.putString("Name",user);
                                            bundle2.putString("Branch",branch);
                                            intent.putExtras(bundle2);
                                            MainActivity.this.startActivity(intent);
                                            Toast.makeText(MainActivity.this, user + " Manager Is Logged IN", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        } else if (position.equals("salesManager")) {
                                            edittextPassword.setText("");
                                            edittextUsername.setText("");
                                            Intent intent = new Intent(MainActivity.this, salesManagerDashboard.class);
                                            Bundle bundle3 = new Bundle();
                                            bundle3.putString("Name",user);
                                            bundle3.putString("Branch",branch);
                                            intent.putExtras(bundle3);
                                            MainActivity.this.startActivity(intent);
                                            progressBar.setVisibility(View.INVISIBLE);
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        } else if (position.equals("notAssigned")) {
                                            edittextPassword.setText("");
                                            edittextUsername.setText("");
                                            progressBar.setVisibility(View.INVISIBLE);
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            Toast.makeText(MainActivity.this, "Position Is not Updated(Contact Administrator)", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        edittextPassword.setText("");
                                        edittextUsername.setText("");
                                        progressBar.setVisibility(View.INVISIBLE);
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        Toast.makeText(MainActivity.this, "Position Is not Updated(Contact Administrator)", Toast.LENGTH_SHORT).show();

                                    }


                                } else {
                                    edittextPassword.setText("");

                                    progressBar.setVisibility(View.INVISIBLE);
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                edittextUsername.setText("");
                                edittextPassword.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else{
            Toast.makeText(MainActivity.this,"Please Fill Out the Fields", Toast.LENGTH_SHORT).show();
        }



    }

}

