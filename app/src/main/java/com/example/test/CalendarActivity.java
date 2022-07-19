package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView navMenu;
    @Override
    public void onBackPressed () {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        navMenu=findViewById(R.id.navBarCal);
        navMenu.getMenu().getItem(2).setChecked(true);
        navMenu.getMenu().getItem(1).setEnabled(false);
        navMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                //ask with if data is getting returned or not
                case R.id.miHome:
                    System.out.println("in CalendarActivity");
                    setResult(5);
                    finish();
                    break;

                case R.id.miGraph:
                    /*finish();
                    Intent intent = new Intent(this,StatisticsActivity.class);
                    startActivityForResult(intent,3);
                    break;*/
                    Toast.makeText(this,"click home first!",Toast.LENGTH_LONG);

            }

            return true;
        });
    }
}