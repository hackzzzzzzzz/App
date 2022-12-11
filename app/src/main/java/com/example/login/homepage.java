package com.example.login;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class homepage extends AppCompatActivity implements View.OnClickListener {
    private CardView schemecard, languagecard, calendercard, feedbackcard, aboutuscard, wastecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        schemecard = (CardView) findViewById(R.id.schemes);
        languagecard = (CardView) findViewById(R.id.language);
        calendercard = (CardView) findViewById(R.id.calender);
        feedbackcard = (CardView) findViewById(R.id.Feedback);
        aboutuscard = (CardView) findViewById(R.id.Aboutus);
        wastecard = (CardView) findViewById(R.id.waste);

        schemecard.setOnClickListener(this);
        languagecard.setOnClickListener(this);
        calendercard.setOnClickListener(this);
        feedbackcard.setOnClickListener(this);
        aboutuscard.setOnClickListener(this);
        wastecard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.schemes:i = new Intent(this, com.example.login.scheme.class); startActivity(i);break;
            case R.id.language:i = new Intent(this, com.example.login.language.class);startActivity(i);break;
            case R.id.calender:i = new Intent(this, calender.class);startActivity(i);break;
            case R.id.Feedback:i = new Intent(this, com.example.login.feedback.class);startActivity(i);break;
            case R.id.Aboutus:i = new Intent(this, aboutus.class);startActivity(i);break;
            case R.id.waste:i = new Intent(this, com.example.login.waste.class);startActivity(i);break;
            default:break;
        }
    }
}