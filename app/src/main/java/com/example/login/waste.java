package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;


public class waste extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private CardView schemecard, languagecard, calendercard, feedbackcard, aboutuscard, wastecard;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste);
        //------------------------------------------------------------------------------------------
        // Initialization of items in the homepage
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

        //------------------------------------------------------------------------------------------
        drawerLayout = findViewById(R.id.Drawer);
        navigationView = findViewById(R.id.Navigation);
        toolbar = findViewById(R.id.include);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//code for home page ----------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.schemes:
                i = new Intent(this, com.example.login.scheme.class);
                startActivity(i);
                break;
            case R.id.language:
                i = new Intent(this, com.example.login.language.class);
                startActivity(i);
                break;
            case R.id.calender:
                i = new Intent(this, calender.class);
                startActivity(i);
                break;
            case R.id.Feedback:
                i = new Intent(this, com.example.login.feedback.class);
                startActivity(i);
                break;
            case R.id.Aboutus:
                i = new Intent(this, aboutus.class);
                startActivity(i);
                break;
            case R.id.waste:
                i = new Intent(this, com.example.login.waste.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem v) {


        switch (v.getItemId()) {
            case R.id.home:
                Toast.makeText(waste.this, "home is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.calen:
                Intent intent = new Intent(waste.this, calender.class);
                startActivity(intent);
                break;

            case R.id.about:
                Toast.makeText(waste.this, "about is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.feedback:
                Toast.makeText(waste.this, "feedback is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                intent = new Intent(waste.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}
