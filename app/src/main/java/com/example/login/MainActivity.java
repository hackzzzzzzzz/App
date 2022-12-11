package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        TextView username = (TextView) findViewById(R.id.Username);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbutton = (MaterialButton) findViewById(R.id.loginbutton);
        MaterialButton Registerbutton = (MaterialButton) findViewById(R.id.registerb);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, homepage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                }
            }

        });

        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

    }

}

