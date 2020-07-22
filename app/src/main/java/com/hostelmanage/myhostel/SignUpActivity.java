package com.hostelmanage.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button reg;
    EditText e1, e2, wm, collegeroll, stdmobileno, branch, year;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseAuth.AuthStateListener authStateListener;
    String ahostelroom = "0";
    String bhostelname = "K1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        reg = (Button) findViewById(R.id.newloginbutt);
        e1 = (EditText) findViewById(R.id.wardenemail);
        e2 = (EditText) findViewById(R.id.WardenPass);
        collegeroll = (EditText) findViewById(R.id.collegeroll);
        stdmobileno = (EditText) findViewById(R.id.mobilenumber);
        branch = (EditText) findViewById(R.id.editText2);
        year = (EditText) findViewById(R.id.whichyear);
        wm = (EditText)findViewById(R.id.wardenname) ;


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                final String name = wm.getText().toString().trim();
                final String collegeRoll = collegeroll.getText().toString().trim();
                final String mobileno = stdmobileno.getText().toString().trim();
                final String stdbranch = branch.getText().toString().trim();
                final String yearstd = year.getText().toString().trim();
                final String hostelroom = ahostelroom.toString().trim();
                final String hostelname = bhostelname.toString().trim();

                if (email.equals("") || password.equals("") || name.equals("") || collegeRoll.equals("") || mobileno.equals("") || stdbranch.equals("") || yearstd.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 5) {

                    Toast.makeText(SignUpActivity.this, "Password Should Be Minimum 6 Digits", Toast.LENGTH_LONG).show();
                    return;

                }

                final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setTitle("Registering!!!");
                progressDialog.setMessage("Processing.....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                mAuth = FirebaseAuth.getInstance();


                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            UserAdapter user = new UserAdapter(
                                    name,
                                    email,
                                    mobileno,
                                    stdbranch,
                                    yearstd,
                                    hostelroom,
                                    hostelname,
                                    collegeRoll
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.hide();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(SignUpActivity.this, Main_DashBoard.class);
                                        startActivity(i);

                                        finish();

                                    } else {
                                        //display a failure message
                                        Toast.makeText(SignUpActivity.this, "Something Went Wrong! Try Again Later", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                            e1.setText("");
                            e2.setText("");
                            wm.setText("");


                        }
                    }

                });

            }
        });
    }
}