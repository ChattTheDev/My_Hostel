package com.hostelmanage.myhostel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPage extends AppCompatActivity {

    EditText e1, e2;
    Button login, registernew;
    FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 1;
    FirebaseUser currentUser;
    FirebaseAuth.AuthStateListener mAuthlistner;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);
        //getSupportActionBar().hide();

        e1 = (EditText) findViewById(R.id.emaillogin);
        e2 = (EditText) findViewById(R.id.passlogin);
        login = (Button) findViewById(R.id.loginbutton);
        registernew = (Button) findViewById(R.id.registerbutton);

        registernew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        mAuthlistner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    Intent i = new Intent(getApplicationContext(), Main_DashBoard.class);
                    startActivity(i);
                }

            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();





        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 5) {

                    Toast.makeText(LoginPage.this, "Password Should Be Minimum 6 Digits", Toast.LENGTH_LONG).show();
                    return;

                }
                final ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
                progressDialog.setTitle("Logging You In!!!");
                progressDialog.setMessage("Processing.....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginPage.this, "Login Successfull!!", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(LoginPage.this, Main_DashBoard.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(LoginPage.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                e1.setText("");
                e2.setText("");

            }
        });


    }






}
