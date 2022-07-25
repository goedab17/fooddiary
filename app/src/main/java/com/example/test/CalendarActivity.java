package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView navMenu;
    private ImageButton ib3;
    private CalendarView calendarView;
    private String date = LocalDate.now().toString();
    private String line[] = date.split("-");
    private String year=line[0];
    private String month=line[1];
    int day = Integer.parseInt(line[2]);
    String returnDate=day+"."+month+"."+year;
    @Override
    public void onBackPressed () {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        navMenu=findViewById(R.id.navBarCal);
        ib3=findViewById(R.id.ib3);
        calendarView=findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                returnDate=String.format("%02d.%02d.%d",dayOfMonth,month+1,year);
            }
        });

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


        ib3.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    public void onButtonClicked(View view){
        System.out.println(returnDate);
        Intent intent= new Intent(this,CalculatedActivity.class);
        intent.putExtra("date",returnDate);
        setResult(5,intent);
        finish();
    }
}