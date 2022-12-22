package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView textView;
    String emailpattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textView = (TextView) findViewById(R.id.forgetpass);
        EditText Phone = (EditText) findViewById(R.id.phonenum);
        EditText Password = (EditText) findViewById(R.id.password);
        MaterialButton loginbutton = (MaterialButton) findViewById(R.id.loginbutton);
        MaterialButton Registerbutton = (MaterialButton) findViewById(R.id.registerb);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Forgetpass.class);
                startActivity(intent);
            }
        });


        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String shb = Phone.getText().toString();
                String phone = "+91"+shb;

                String password = Password.getText().toString();


                if(phone.isEmpty() || phone.length()<10){
                    Phone.setError("Enter Correct Number");
                }

                else if(password.isEmpty() || password.length()<8){
                    Password.setError("Enter Correct Password");
                }

                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)
                        {
                            if(snapshot.hasChild(phone))
                            {
                                final String getpassword = snapshot.child(phone).child("Password").getValue(String.class);

                                if(getpassword.equals(password)){
                                    Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(Login.this,homepage.class));
                                    finish();

                                }
                                else{
                                    Toast.makeText(Login.this, "Error PhoneNumber or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Login.this, "Error UserName or Password", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }


        });
    }
    private void sendusertonextactivity() {

        Intent intent = new Intent(Login.this,homepage.class);

        startActivity(intent);

    }
}