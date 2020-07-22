package com.hostelmanage.myhostel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Main_DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView emailname;
    View hview;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    Button noticeshow;
    Button CompliantRegister, messmenu, viewdetails;
    AlertDialog.Builder builder;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__dash_board);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        hview = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);




        emailname = (TextView) hview.findViewById(R.id.textView);
        noticeshow = (Button)findViewById(R.id.noticeforstudents);
        CompliantRegister = (Button)findViewById(R.id.registercomplaint);
        messmenu = (Button)findViewById(R.id.messmenuchart);
        viewdetails = (Button)findViewById(R.id.viewdetails);

        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateDetails.class);
                startActivity(intent);
            }
        });

        messmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Messmenu.class);
                startActivity(i);

            }
        });

        CompliantRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterCompliant.class);
                startActivity(i);
            }
        });


        noticeshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ImageShowingNotices.class);
                startActivity(i);
            }
        });

        loginact();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i1 = new Intent(getApplicationContext(), UpdateDetails.class);
            startActivity(i1);
        }  else if (id == R.id.nav_slideshow) {
            Intent i2 = new Intent(getApplicationContext(), ImageShowingNotices.class);
            startActivity(i2);

        }  else if (id == R.id.nav_menu) {
            Intent i3 = new Intent(getApplicationContext(), Messmenu.class);
            startActivity(i3);

        }
        else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main_DashBoard.this);
            builder1.setMessage("Are You Sure?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mAuth = FirebaseAuth.getInstance();
                            mAuth.signOut();
                            Intent i3 = new Intent(getApplicationContext(), LoginPage.class);
                            startActivity(i3);
                            finishAffinity();



                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
        else if (id == R.id.nav_complaint) {
            Intent i4 = new Intent(getApplicationContext(), RegisterCompliant.class);
            startActivity(i4);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loginact() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();

            emailname.setText("" + email);


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }


    }
}
