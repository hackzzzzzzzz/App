package com.example.login;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.button.MaterialButton;

public class signup extends AppCompatActivity {
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
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });


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
    }


}
