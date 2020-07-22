package com.hostelmanage.myhostel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ImageShowingNotices extends AppCompatActivity {

     RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<Uplaodfiles> options;
    FirebaseRecyclerAdapter<Uplaodfiles, MyViewHolder> adapter;
    ProgressBar mProgressCircle;
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_showing_notices);
        Toolbar toolbar123 = findViewById(R.id.imgehowingtoolbar);
        setSupportActionBar(toolbar123);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notices");
        toolbar123.setTitleTextColor(getResources().getColor(R.color.black));
        mRecyclerView = (RecyclerView)this.findViewById(R.id.recyclerview);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("NoticeUploads");
        mProgressCircle = findViewById(R.id.progress_circle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        options = new FirebaseRecyclerOptions.Builder<Uplaodfiles>().setQuery(mDatabaseRef, Uplaodfiles.class).build();
        adapter = new FirebaseRecyclerAdapter<Uplaodfiles, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Uplaodfiles model) {
                holder.textView.setText(model.getNoticeImage());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);
                mProgressCircle.setVisibility(View.INVISIBLE);

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_items, parent, false);


                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);

    }




}
