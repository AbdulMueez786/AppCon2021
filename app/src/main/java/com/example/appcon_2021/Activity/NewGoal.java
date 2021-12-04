package com.example.appcon_2021.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.appcon_2021.Model.goal;
import com.example.appcon_2021.Model.user;
import com.example.appcon_2021.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewGoal extends AppCompatActivity {

    private ImageView image;
    private int request_code=200;
    private Uri selectedImage_uri=null;

    // Firebase
    private FirebaseAuth auth;
    private DatabaseReference data_ref;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        data_ref = FirebaseDatabase.getInstance().getReference("goals");

        //Image
        image=findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        // title
        EditText title=findViewById(R.id.title);

        //description
        EditText description=findViewById(R.id.description);

        //category
        EditText category=findViewById(R.id.category);

        //Target amount
        EditText Tamt=findViewById(R.id.target_amount);

        //Raised amount
        EditText Ramt=findViewById(R.id.raised_amount);

        //Goal updates (text or images)


        // QR code generation



        Button btn=findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              System.out.println(title.getText().toString());
              System.out.println(description.getText().toString());
              System.out.println(category.getText().toString());
              System.out.println(Tamt.getText().toString());
              System.out.println(Ramt.getText().toString());
              StoreData(title.getText().toString(),description.getText().toString()
                      ,category.getText().toString(),Tamt.getText().toString(),Ramt.getText().toString(),
                      "0","Active","");
            }
        });


    }
    void openGallery(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,request_code);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            this.selectedImage_uri=data.getData();
            this.image.setImageURI(this.selectedImage_uri);
        }
    }

    void StoreData(String Title,String Description,String Category,String Target_Amount,String Raised_Amount,String No_of_participants,String Status,String QrCode) {

        if (selectedImage_uri != null) {

            String key=data_ref.push().getKey();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            storageReference = storageReference.child("goals/" + key);
            storageReference.putFile(this.selectedImage_uri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            // Toast.makeText(create_profile.this, "complited", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final String dp = uri.toString();


                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            data_ref.child(key).setValue(new goal(key,FirebaseAuth.getInstance().getCurrentUser().getUid(),dp,Title,Description,
                                    Category,Target_Amount,Raised_Amount,No_of_participants,Status,QrCode)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(create_profile.this, "Failuer", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}