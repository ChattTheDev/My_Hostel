package com.hostelmanage.myhostel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class RegisterCompliant extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView uploadnewimage;
    Button mButtonUpload1;
    EditText studname,studroll,studhostelroom,studhostelname,studdescription ;
    Uri mImageUri;
    boolean isImageAdded = false;
    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_compliant);
        Toolbar toolbar14 = findViewById(R.id.complaintpage);
        setSupportActionBar(toolbar14);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Complaint");
        toolbar14.setTitleTextColor(getResources().getColor(R.color.black));
        mButtonUpload1 = (Button) findViewById(R.id.lodgecomplaint);
        studname = (EditText) findViewById(R.id.namecr);
        studroll = (EditText) findViewById(R.id.rollcr);
        studhostelroom = (EditText)findViewById(R.id.hostelcr);
        studdescription = (EditText)findViewById(R.id.complaintcr);
        studhostelname = (EditText)findViewById(R.id.hostelname);

        uploadnewimage = (ImageView)findViewById(R.id.imagechooser) ;
        mStorageRef = FirebaseStorage.getInstance().getReference().child("ComplaintImages");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Complaints");
        uploadnewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName = studname.getText().toString();
                final String imageRoll = studroll.getText().toString();
                final String imageHostel = studhostelroom.getText().toString();
                final String imageHostelName = studhostelname.getText().toString();
                final String imageComplaint = studdescription.getText().toString();


                if( imageName!=null && imageRoll!=null && imageHostel!=null && imageComplaint!=null && imageHostelName!=null){
                    uploadFile(imageName, imageRoll, imageHostel, imageComplaint, imageHostelName);
                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadFile(final String name, final String imageRoll, final String imageHostel, final String imageName, final String imageHostelName) {
        if (mImageUri != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading...");
            dialog.show();

            final String key = mDatabaseRef.push().getKey();

            mStorageRef.child(key+".jpg").putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mStorageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("StudentName", name);
                            hashMap.put("StudentRoll", imageRoll);
                            hashMap.put("StudentHostelRoom", imageHostel);
                            hashMap.put("StudentHostelName", imageHostelName);
                            hashMap.put("StudentComplaint", imageName);
                            hashMap.put("ImageUrl1", uri.toString());

                            mDatabaseRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Successfully Uploaded", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    dialog.setMessage("Uploaded " + ((int) progress) + "%...");
                    dialog.dismiss();

                    //Creating Notification When Student Uploads A Complaint on Firebase Sever
//                    notification();

                }
            });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && data!=null){
            mImageUri = data.getData();
            isImageAdded=true;
            uploadnewimage.setImageURI(mImageUri);
        }
    }


//    private void notification() {
//        String name = studname.getText().toString();
//        String message = studdescription.getText().toString();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel channel =
//                    new NotificationChannel("n","n",NotificationManager.IMPORTANCE_DEFAULT);
//
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
//                .setContentText("My Hostel")
//                .setSmallIcon(R.drawable.home)
//                .setAutoCancel(true)
//                .setContentText(name + message);
//
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        managerCompat.notify(999,builder.build());
//    }
}