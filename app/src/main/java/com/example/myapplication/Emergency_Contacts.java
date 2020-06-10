package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Emergency_Contacts extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText remail, rname, rnum;
    String em;
    Button add1,add2,add3,del1,del2,del3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__contacts);
        rname = findViewById(R.id.name);
        remail = findViewById(R.id.email);
        rnum = findViewById(R.id.num);
        add1 = findViewById(R.id.add1);
        add2= findViewById(R.id.add2);
        add3=findViewById(R.id.add3);
        del1 = findViewById(R.id.del1);
        del2= findViewById(R.id.del2);
        del3=findViewById(R.id.del3);
        fAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userID = fAuth.getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference(userID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                   if(dataSnapshot.child("Emergency Contacts").getValue(contact.class)!= null)
                   {  contact cont=  dataSnapshot.child("Emergency Contacts").getValue(contact.class);
                    Log.d("Adebug","one"+ cont.getOne());
                    Log.d("Adebug","two"+ cont.getTwo());
                    Log.d("Adebug","three"+ cont.getThree());
                TextView contt = (TextView) findViewById(R.id.con1);
                contt.setText(cont.getOne());
                contt = (TextView) findViewById(R.id.con2);
                contt.setText(cont.getTwo());
                contt = (TextView) findViewById(R.id.con3);
                contt.setText(cont.getThree());}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                    DatabaseReference myRef = database.getReference(userID);
                    myRef.child("Emergency Contacts").child("one").setValue("NIL");
                }
            }
        });
        del2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                    DatabaseReference myRef = database.getReference(userID);
                    myRef.child("Emergency Contacts").child("two").setValue("NIL");
                }
            }
        });
        del3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                    DatabaseReference myRef = database.getReference(userID);
                    myRef.child("Emergency Contacts").child("three").setValue("NIL");
                }
            }
        });


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                em = rname.getText() + " " + remail.getText() + " " + rnum.getText();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                        DatabaseReference myRef = database.getReference(userID);
                        myRef.child("Emergency Contacts").child("one").setValue(em);
                    //myRef.child("Emergency Contacts").child("2").setValue(em);
                    //myRef.child("Emergency Contacts").child("3").setValue(em);
                    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                }
            }

        });
        add2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                em = rname.getText() + " " + remail.getText() + " " + rnum.getText();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                    DatabaseReference myRef = database.getReference(userID);
                    myRef.child("Emergency Contacts").child("two").setValue(em);
                    //myRef.child("Emergency Contacts").child("2").setValue(em);
                    //myRef.child("Emergency Contacts").child("3").setValue(em);
                    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                }
            }

        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                em = rname.getText() + " " + remail.getText() + " " + rnum.getText();
                if (fAuth.getCurrentUser() != null) {
                    String userID = fAuth.getCurrentUser().getUid();
                    DatabaseReference myRef = database.getReference(userID);
                    myRef.child("Emergency Contacts").child("three").setValue(em);
                    //myRef.child("Emergency Contacts").child("2").setValue(em);
                    //myRef.child("Emergency Contacts").child("3").setValue(em);
                    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                }
            }

        });

    }


    }






