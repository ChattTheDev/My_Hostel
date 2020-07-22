package com.hostelmanage.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UpdateDetails extends AppCompatActivity {

    TextView t1, t2, t3, t4;
    TextView e1, e2, e3;
    String user;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth.AuthStateListener authStateListener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        e1 = findViewById(R.id.nameedit);
        e2 = findViewById(R.id.Rolledit);
        e3 = findViewById(R.id.phoneedit);

        t1 = findViewById(R.id.whichhostel);
        t2 = findViewById(R.id.hostelroom);
        t3 = findViewById(R.id.year);
        t4 = findViewById(R.id.branch);
        progressBar = findViewById(R.id.newprogress);

        progressBar.setVisibility(View.VISIBLE);
        firebaseUser = mAuth.getInstance().getCurrentUser();
        user = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child(user).child("name").getValue(String.class);
                String roll = snapshot.child(user).child("collegeRoll").getValue(String.class);
                String email = snapshot.child(user).child("email").getValue(String.class);
                String hostelname = snapshot.child(user).child("hostelname").getValue(String.class);
                String hostelroom = snapshot.child(user).child("hostelroom").getValue(String.class);
                String mobileno = snapshot.child(user).child("mobileno").getValue(String.class);
                String branch = snapshot.child(user).child("stdbranch").getValue(String.class);
                String year = snapshot.child(user).child("yearstd").getValue(String.class);

                progressBar.setVisibility(View.GONE);

                e1.setText(name);
                e2.setText(roll);
                e3.setText(mobileno);
                t1.setText(hostelname);
                t2.setText(hostelroom);
                t3.setText(year);
                t4.setText(branch);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        





    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(authStateListener);
//    }
}