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
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class passchange extends AppCompatActivity {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_change);

        EditText phonenumber = (EditText) findViewById(R.id.phonenum);
        EditText newpass = (EditText) findViewById(R.id.newpass);
        EditText confirmnewpass = (EditText) findViewById(R.id.confirmnewpass);
        MaterialButton confirm = (MaterialButton) findViewById(R.id.updateconfirm);



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phonee = phonenumber.getText().toString();
                final String phone = "+91"+phonee;
                final String pass1 = newpass.getText().toString();
                final String pass2 = confirmnewpass.getText().toString();


                if(pass1.isEmpty() || pass1.length()<8 || pass2.isEmpty() || pass2.length()<8){
                    newpass.setError("Passowrd too Short Minimum 8 characters");
                }
                else if(!pass1.equals(pass2))
                {
                    Toast.makeText(passchange.this, "Passwords Dosen't Match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                    reference.child(phone).child("Password").setValue(pass2);
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }

            }
        });


      }
}