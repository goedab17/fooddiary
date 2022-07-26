package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StatisticsActivity extends AppCompatActivity {

    private BottomNavigationView navMenu;
    private GraphView graphView;
    private TextView tvGoalweight;
    private  TextView tvLabel;
    private EditText etUpdateFood;
    private TextView tvGoalFeedback;
    private Button btUpdate;
    private  TextView tvStartweight;
    private KonfettiView konfettiView;

    LineGraphSeries<DataPoint> series;
    private List<String> dateList=new LinkedList<>();
    private int counter=0;
    SimpleDateFormat sdf = new SimpleDateFormat("d.M.yy");
    private double min;
    private double max=0;
    private int startweight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //get Data from Activity
        Intent intent = getIntent();

        startweight =intent.getIntExtra("startweight",0);

        konfettiView=findViewById(R.id.viewKonfetti);
        navMenu=findViewById(R.id.navBarStat);
        graphView=findViewById(R.id.idGraphView);
        tvGoalweight=findViewById(R.id.tvGoalweight);
        tvGoalFeedback=findViewById(R.id.tvGoalFeedback);
        tvLabel=findViewById(R.id.tvFoodLabel);
        etUpdateFood=findViewById(R.id.etWeightInput);
        btUpdate=findViewById(R.id.btUpdate);
        tvStartweight=findViewById(R.id.tvStartWeight);
        tvStartweight.setText(startweight+"");
        if(savedInstanceState != null){
            System.out.println("hello");
            String myString=savedInstanceState.getString("my_string");
            System.out.println(myString);
            System.out.println("max restored"+ max);
            tvLabel.setText("myString"+"");
        }
        dateList.add("21.07.2022");
        dateList.add("22.07.2022");
        dateList.add("23.07.2022");
        dateList.add("24.07.2022");



        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHumanRounding(false);
        graphView.getGridLabelRenderer().setPadding(50);

        try {

            min = sdf.parse(intent.getStringExtra("date")).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        series=new LineGraphSeries<>(getDataPoint(dateList,false));


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
        //sets horizontel collumns
        graphView.getGridLabelRenderer().setNumHorizontalLabels(dateList.size());
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putString("my_string","Welcome back");
        savedInstanceState.putDouble("max",max);

        super.onSaveInstanceState(savedInstanceState);

    }

    private DataPoint[] getDataPoint(List<String> list,boolean value) {


//        DataPoint[] dp = new DataPoint[list.size()+1];
        List<DataPoint> dp = new ArrayList<DataPoint>();

        //for setting Min and Max
        try {

            Intent intent = getIntent();
            tvGoalweight.setText(intent.getIntExtra("goalweight",0)+"");
            max = sdf.parse(intent.getStringExtra("date")).getTime();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);

                Date date=sdf.parse(list.get(i));
                if(date.getTime()<min){
                    min=date.getTime();
                }

                    max=sdf.parse(intent.getStringExtra("date")).getTime();

                System.out.println("Date+ "+ date);
                dp.add(new DataPoint(date, startweight ));
                System.out.println(dp.get(i));
            }
            if(value==true) {
                double weight= Double.parseDouble(etUpdateFood.getText().toString());
                double lostweight= weight-startweight;
                Date actDate =sdf.parse(intent.getStringExtra("date"));
                if(actDate.getTime()<min){
                    min=actDate.getTime();
                }
                if(actDate.getTime()>max)
                {
                    max=actDate.getTime();
                }
                DataPoint dataPoint =new DataPoint(sdf.parse(intent.getStringExtra("date")),weight);
                dp.add(dataPoint);

                if(lostweight <0||lostweight==0)
                {
                    tvGoalFeedback.setBackgroundColor(Color.parseColor("#E6FAE7"));
                    double result=weight-intent.getIntExtra("goalweight",0);
                    if(lostweight==0){
                        tvGoalFeedback.setText(" ");
                        tvGoalFeedback.setBackgroundColor(Color.TRANSPARENT);

                    }
                    else if(result<0||result==0){

                        tvGoalFeedback.setText("You are have reached your goal, and have lost: "+lostweight+" kg amazing job");
                        DisplayMetrics display= new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(display);
                        konfettiView.build()
                                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                                .addSizes(new Size(12, 5f))
                                .setPosition(-50f, display.widthPixels + 50f, -50f, -50f)

                                .streamFor(300, 5000L);
                    }
                    else{
                        tvGoalFeedback.setText("You are closer to your goal and have lost"+lostweight+"kg keep going like that!");
                    }
                }
                else{
                    tvGoalFeedback.setText("You gained  "+lostweight+" kg but with enough willpower you can do it and reach your goal!");
                    tvGoalFeedback.setBackgroundColor(Color.parseColor("#FB7979"));

                }

            }
            //dateList.add()

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        for (DataPoint dataPoints:dp) {
            System.out.println(dataPoints);
        }
        graphView.getViewport().setXAxisBoundsManual(true);
        System.out.println("Min "+min);
        System.out.println("max restored"+ max);


        graphView.getViewport().setMinX(min);
        graphView.getViewport().setMaxX(max);


        return dp.toArray(new DataPoint[dp.size()]);

    }
    @Override
    public void onBackPressed () {
        Intent intent= NavUtils.getParentActivityIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        NavUtils.navigateUpTo(this,intent);

    }
  public void onButtonPressed(View view){

          graphView.removeAllSeries();
          series=new LineGraphSeries<>(getDataPoint(dateList,true));
          graphView.addSeries(series);
          graphView.refreshDrawableState();


      }

}