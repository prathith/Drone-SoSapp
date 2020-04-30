package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.mobile);
        mRegisterBtn = findViewById(R.id.register_btn);
        tv3 = findViewById(R.id.textView3);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });




    }

    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String email, password,fullname,phone;


        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        fullname= mFullName.getText().toString();
        phone=mPhone.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is Required.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is Required.");
            return;
        }
        if(password.length() < 6) {
            mPassword.setError("Password Must be >= 6 Characters");
            return;
        }

        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User Created.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            String userID = fAuth.getCurrentUser().getUid();
                            DatabaseReference myRef = database.getReference(userID);
                            myRef.child("Email").setValue(email);
                            myRef.child("username").setValue(fullname);
                            myRef.child("password").setValue(password);
                            myRef.child("phone").setValue(phone);
                            myRef.child("Status").setValue("healthy");
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }
}
