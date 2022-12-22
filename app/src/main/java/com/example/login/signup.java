package com.example.login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

    String emailpattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        EditText Username = (EditText) findViewById(R.id.Username);
        EditText Email = (EditText) findViewById(R.id.Email);
        EditText Phone = (EditText) findViewById(R.id.phonenum);
        EditText Password = (EditText) findViewById(R.id.password);
        EditText Repassword = (EditText) findViewById(R.id.Retype_password);
        MaterialButton regbtn = (MaterialButton) findViewById(R.id.registerbutton);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                final String Rpassword = Repassword.getText().toString();
                final String phone = Phone.getText().toString();
                final String username = Username.getText().toString();
                //---

                if(!email.matches(emailpattern)){
                    Email.setError("Please Enter Correct Email");
                }
                else if(password.isEmpty() || password.length()<8){
                    Password.setError("Passowrd too Short Minimum 8 characters");
                }
                else if(phone.isEmpty() || phone.length()<10){
                    Phone.setError("Enter Correct Number");
                }
                else if(!password.equals(Rpassword)){
                    Repassword.setError("Password does not Match");
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phone)){
                                Toast.makeText(signup.this, "Phone is already Registered", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                databaseReference.child("users").child(phone).child("Username").setValue(username);
                                databaseReference.child("users").child(phone).child("Email").setValue(email);
                                databaseReference.child("users").child(phone).child("Phone").setValue("+91"+phone);
                                databaseReference.child("users").child(phone).child("Password").setValue(password);


                                Toast.makeText(signup.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

                //---
            }
        });


    }


}