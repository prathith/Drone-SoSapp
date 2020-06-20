package com.example.myapplication;

import androidx.annotation.NonNull;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import  com.example.record;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        final TextView viewAge = (TextView)findViewById(R.id.vAge);
       FirebaseAuth fAuth= FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userID= fAuth.getCurrentUser().getUid();
        final DatabaseReference mRef = database.getReference(userID);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                   String name= (String) dataSnapshot.child("username").getValue();
                   TextView vname=findViewById(R.id.vName);
                   vname.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Button up=findViewById(R.id.medicalViewBtn);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("change").setValue(("true"));
                Intent intent = new Intent(getApplicationContext(), Medical_Records.class);
                startActivity(intent);
            }
        });
        if(database.getReference(userID).child("record")!=null) {
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("record").getValue(record.class) != null) {
                        record rec = dataSnapshot.child("record").getValue(record.class);
                        Log.d("Adebug", rec.getAge());
                        TextView age = (TextView) findViewById(R.id.vAge);
                        age.setText(rec.getAge() + " " + "years");
                        TextView height = (TextView) findViewById(R.id.vheight);
                        height.setText(rec.getHeight() + " " + "ft");
                        TextView weight = (TextView) findViewById(R.id.vWeight);
                        weight.setText(rec.getWeight() + " " + "kg");
                        TextView bg = (TextView) findViewById(R.id.vBloodtype);
                        bg.setText(rec.getBlood_group());
                        TextView bp = (TextView) findViewById(R.id.vBp);
                        bp.setText(rec.getBlood_pressure());
                        TextView dia = (TextView) findViewById(R.id.vDia);
                        dia.setText(rec.getDiabetes());
                        TextView cond = (TextView) findViewById(R.id.vConditions);
                        cond.setText(rec.getConditions());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                }

            });

        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Medical_Records.class);
            startActivity(intent);
        }



        }

    }