package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;



public class calender extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste);


//------------------------------------------------------------------------------------------------
        drawerLayout = findViewById(R.id.Drawer);
        navigationView = findViewById(R.id.Navigation);
        toolbar = findViewById(R.id.include);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem v) {
                switch (v.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(calender.this, homepage.class);
                        startActivity(intent);
                        break;

                    case R.id.calen:
                        Toast.makeText(calender.this, "You are in Calender Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.about:
                        intent = new Intent(calender.this, aboutus.class);
                        startActivity(intent);
                        break;

                    case R.id.feedback:
                        intent = new Intent(calender.this, feedback.class);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        intent = new Intent(calender.this, Login.class);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });


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

}
