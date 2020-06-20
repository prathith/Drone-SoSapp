package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Profile extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button Button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        String userID= fAuth.getCurrentUser().getUid();
        final DatabaseReference mRef = database.getReference(userID);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                 String name= String.valueOf(dataSnapshot.child("phone").getValue());
                TextView vname=findViewById(R.id.pMobile);
                vname.setText(name);
                name= (String) dataSnapshot.child("Email").getValue();
                vname=findViewById(R.id.pEmail);
                vname.setText(name);
                name= (String) dataSnapshot.child("username").getValue();
                vname=findViewById(R.id.pName);
                vname.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
