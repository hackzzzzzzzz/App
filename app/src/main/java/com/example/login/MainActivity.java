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

public class MainActivity extends AppCompatActivity {

    String emailpattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mauth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        EditText Email = (EditText) findViewById(R.id.Email);
        EditText Password = (EditText) findViewById(R.id.password);

        MaterialButton loginbutton = (MaterialButton) findViewById(R.id.loginbutton);
        MaterialButton Registerbutton = (MaterialButton) findViewById(R.id.registerb);
        progressDialog = new ProgressDialog(this);
        mauth = FirebaseAuth.getInstance();
        mUser = mauth.getCurrentUser();

        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                performlogin();
            }

            private void performlogin() {
                String email = Email.getText().toString();
                String password = Password.getText().toString();


                if(!email.matches(emailpattern)){

                    Email.setError("Enter Correct Email");

                }

                else if(password.isEmpty() || password.length()<8){
                    Password.setError("Enter Correct Password");
                }

                else{
                    progressDialog.setMessage("Please Wait On Login");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendusertonextactivity();
                                Toast.makeText(MainActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            }

        }


        });
    }
    private void sendusertonextactivity() {

        Intent intent = new Intent(MainActivity.this,homepage.class);

        startActivity(intent);

    }
}
