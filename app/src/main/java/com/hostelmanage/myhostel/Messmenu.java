package com.hostelmanage.myhostel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jsibbold.zoomage.ZoomageView;

public class Messmenu extends AppCompatActivity {
    ZoomageView zoomageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messmenu);

        Toolbar toolbar12 = findViewById(R.id.menucharttoolbar);
        setSupportActionBar(toolbar12);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu Chart");
        toolbar12.setTitleTextColor(getResources().getColor(R.color.black));

        zoomageView = (ZoomageView)findViewById(R.id.myZoomageView);
    }
}
