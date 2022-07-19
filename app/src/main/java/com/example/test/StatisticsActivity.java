package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.test.bl.GraphViewData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StatisticsActivity extends AppCompatActivity {
        
    private BottomNavigationView navMenu;
    private GraphView graphView;
    LineGraphSeries<DataPoint> series;
    private List<String> dateList=new LinkedList<>();
    private int counter=0;
    SimpleDateFormat sdf = new SimpleDateFormat("d.M.yy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //get Data from Activity
        Intent intent = getIntent();
        dateList.add(intent.getStringExtra("date"));
        dateList.add("20.07.2022");
        navMenu=findViewById(R.id.navBarStat);
        graphView=findViewById(R.id.idGraphView);


        series=new LineGraphSeries<>(getDataPoint(dateList));

        graphView.addSeries(series);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){

            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    System.out.println("format label"+ sdf.format(new Date((long)value)));
                    return sdf.format(new Date((long)value));
                }
                else{
                    return super.formatLabel(value, isValueX);

                }
            }
        });

        graphView.getGridLabelRenderer().setNumHorizontalLabels(7);
        navMenu.getMenu().getItem(2).setEnabled(false);
        navMenu.getMenu().getItem(1).setChecked(true);
        navMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                //ask with if data is getting returned or not
                case R.id.miHome:
                    setResult(4);
                    finish();
                    break;

                case R.id.miCalender:
                    Toast.makeText(this,"click home first!",Toast.LENGTH_LONG);
                    break;
            }

            return true;
        });


    }

    private DataPoint[] getDataPoint(List<String> list) {


//        DataPoint[] dp = new DataPoint[list.size()+1];
        List<DataPoint> dp = new ArrayList<DataPoint>();

        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.size());
        try {

            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);

                Date date=sdf.parse(list.get(i));
                System.out.println("Date+ "+ date);
                dp.add(new DataPoint(date,70));
                System.out.println(dp.get(i));
            }
            //dateList.add()
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        for (DataPoint dataPoints:dp) {
            System.out.println(dataPoints);
        }


        return dp.toArray(new DataPoint[dp.size()]);
    }

    @Override
    public void onBackPressed () {

    }
}