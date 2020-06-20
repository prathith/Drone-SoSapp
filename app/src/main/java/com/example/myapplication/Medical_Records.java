package com.example.myapplication;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Medical_Records extends AppCompatActivity {
    Button update;
    FirebaseAuth fAuth;
    EditText mAge , mHeight , mWeight , mConditions;
    RadioGroup bloodPressure,diabetes;
    RadioButton bpBtn,diaBtn;
    Spinner mbloodType;
    String id,bp,age,height,weight,bloodType,dia,condtions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();
        String userID = fAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference medicalRecords = database.getReference(userID);
        setContentView(R.layout.activity_medical__records);
        bloodPressure=(RadioGroup)findViewById(R.id.bloodPressureGroup);
        diabetes=(RadioGroup)findViewById(R.id.diabetesgroup);
        mAge = (EditText)findViewById(R.id.age);
        mHeight=(EditText)findViewById(R.id.height);
        mWeight=(EditText)findViewById(R.id.weight);
        mbloodType=(Spinner) findViewById(R.id.SpinnerBloodType);
        mConditions=(EditText) findViewById(R.id.EditTextFeedbackBody);
        update=findViewById(R.id.medicalBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }


        });
    }

    private void update(){
        bpBtn=(RadioButton) findViewById(bloodPressure.getCheckedRadioButtonId());
        bp = bpBtn.getText().toString();
        bloodPressure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioBp1:
                        bp = "High Blood Pressure";
                        break;
                    case R.id.radioBp2:
                        bp = "Normal";
                        break;
                    case R.id.radioBp3:
                        bp="Low Blood Pressure";
                }
            }
        });
        diaBtn=(RadioButton)findViewById(diabetes.getCheckedRadioButtonId());
        dia = diaBtn.getText().toString();
        diabetes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioDia1:
                        bp = "Yes";
                        break;
                    case R.id.radioDia2:
                        bp = "No";
                        break;
                }
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userID = fAuth.getCurrentUser().getUid();
        DatabaseReference medicalRecords = database.getReference(userID);
        age = mAge.getText().toString().trim();
        height = mHeight.getText().toString().trim();
        weight = mWeight.getText().toString().trim();
        condtions=mConditions.getText().toString();
        bloodType=mbloodType.getSelectedItem().toString();
        medicalRecords.child("record").child("age").setValue(age);
        medicalRecords.child("record").child("height").setValue(height);
        medicalRecords.child("record").child("weight").setValue(weight);
        medicalRecords.child("record").child("blood_group").setValue(bloodType);
        medicalRecords.child("record").child("blood_pressure").setValue(bp);
        medicalRecords.child("record").child("diabetes").setValue(dia);
        medicalRecords.child("record").child("conditions").setValue(condtions);
        Toast.makeText(getApplicationContext(), "Medical Details Updated", Toast.LENGTH_SHORT).show();
    }
}
