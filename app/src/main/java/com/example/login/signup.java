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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    String  emailpattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mauth;
    FirebaseUser mUser;



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
        progressDialog = new ProgressDialog(this);
        mauth = FirebaseAuth.getInstance();
        mUser = mauth.getCurrentUser();



        String[] items = {"Farmer", "Fisherman", "Student", "Women"};
        AutoCompleteTextView autoCompleteTxt;
        ArrayAdapter<String> adapterItems;


        autoCompleteTxt = findViewById(R.id.autoComplete);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_view, items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performforAuth();
            }

            private void performforAuth() {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String Rpassword = Repassword.getText().toString();
                String phone = Phone.getText().toString();
                String username = Username.getText().toString();

                if(!email.matches(emailpattern)){

                    Email.setError("Enter Correct Email");

                }

                else if(password.isEmpty() || password.length()<8){
                    Password.setError("Enter Correct Password");
                }
                else if(phone.isEmpty() || phone.length()<10){
                    Phone.setError("Enter Correct Number");
                }
                else if(!password.equals(Rpassword)){
                    Repassword.setError("Password does not Match");
                }
                else{
                    progressDialog.setMessage("Please Wait On Registration");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();


                    mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendusertonextactivity();
                                Toast.makeText(signup.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });




    }

    private void sendusertonextactivity() {

        Intent intent = new Intent(signup.this,MainActivity.class);

        startActivity(intent);

    }


}
