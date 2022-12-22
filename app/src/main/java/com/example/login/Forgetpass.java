package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AuthProvider;
import java.util.concurrent.TimeUnit;


public class Forgetpass extends AppCompatActivity {

    String codebysystem;
    TextView textView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass);

        EditText phone = (EditText) findViewById(R.id.phonenum);
        textView = (TextView) findViewById(R.id.otp);
        EditText enterotp = (EditText) findViewById(R.id.enterotp);
        MaterialButton confirm = (MaterialButton) findViewById(R.id.confirmbutton);
        auth = FirebaseAuth.getInstance();
        FirebaseAuth firebaseAuth;





        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phonee = phone.getText().toString();
                String phonenumm = "+91"+phonee;


                if(phonenumm.isEmpty() || phonenumm.length()<10){
                    phone.setError("Enter Correct Number");
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonenumm)){
                                Toast.makeText(Forgetpass.this, "otp sent", Toast.LENGTH_SHORT).show();
                                sendotp();

                            }
                            else{
                                Toast.makeText(Forgetpass.this, "User Not Found", Toast.LENGTH_SHORT).show();
                            }

                        }

                        private void sendotp() {
                            PhoneAuthProvider.verifyPhoneNumber(
                                    PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                                            .setActivity(Forgetpass.this)
                                            .setPhoneNumber(phonenumm)
                                            .setTimeout(60L,TimeUnit.SECONDS)
                                            .setCallbacks(mcallbacks)
                                            .build());
                        }

                        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks=
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        codebysystem = s;

                                    }

                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        String code = phoneAuthCredential.getSmsCode();
                                        if(code!=null){
                                            enterotp.setText(code);
                                            verifycode(code);

                                        }
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(Forgetpass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                };


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }




            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = enterotp.getText().toString();
                if(!code.isEmpty()){
                    verifycode(code);
                }
            }
        });

    }

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebysystem,code);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        openpasschange(credential);
                    }
                    else {
                        Toast.makeText(Forgetpass.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }



    private void openpasschange(PhoneAuthCredential credential) {
        Intent intent = new Intent(Forgetpass.this , passchange.class);
        startActivity(intent);
        finish();
    }


}
