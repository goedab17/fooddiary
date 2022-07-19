package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CalculatedActivity extends AppCompatActivity {

    private TextView tvKcal;
    private TextView tvFeedback;
    private ProgressBar pb;
    private int kcalday = 0;
    private int summ=0;
    private TextView tvKcalOverUnder;
    private BottomNavigationView navMenu;
    private TextView tvLabel;
    private DateTimeFormatter dtf=DateTimeFormatter.ofPattern("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homepage);
        tvKcal = findViewById(R.id.tvKcal);
        pb = findViewById(R.id.progressBar);
        tvFeedback = findViewById(R.id.tvFeedback);
        tvKcalOverUnder = findViewById(R.id.tvKcalOverUnder);
        navMenu = findViewById(R.id.navBar);
        tvLabel = findViewById(R.id.tvDate);
        tvFeedback.setBackgroundColor(Color.parseColor("#EAF8EA"));
        String date = LocalDate.now().toString();
        String line[] = date.split("-");
        String year=line[0];
        String month=line[1];
        int day = Integer.parseInt(line[2]);
        tvLabel.setText(day+"."+month+"."+year);

        Intent intent = getIntent();
        int age = intent.getIntExtra("age", 0);
        int height = intent.getIntExtra("height", 0);
        int goalweight = intent.getIntExtra("goalweight", 0);
        int currentweight = intent.getIntExtra("currentweight", 0);

        String gender = intent.getStringExtra("selected");
        if (gender.equals("male")) {
            //Männer: GU (kcal/Tag) = 66 + (13,8 x Gewicht in kg) + (5,0 x Größe in cm) – (6,8 x Alter in Jahren)
            kcalday = (int) (66 + (13.8 * currentweight) + (5 * height) - (6.8 * age));

        } else if (gender.equals("female")) {
            //   Frauen: GU (kcal/Tag) = 655 + (9,5 x Gewicht in kg) + (1,9 x Größe in cm) – (4,7 x Alter in Jahren)
            kcalday = (int) (655 + (9.5 * currentweight) + (1.9 * height) - (4.7 * age));

        }
        tvKcal.setText(kcalday + "");
        pb.setMax(kcalday);

        navMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.miHome:
                    Intent intent1 = new Intent(this, StatisticsActivity.class);
                    int number=day+1;
                    intent1.putExtra("date",number+"."+month+"."+year);
                    startActivityForResult(intent1,3);

                    break;
                case R.id.miGraph:
                    Intent intent2 = new Intent(this, StatisticsActivity.class);
                    intent2.putExtra("date",tvLabel.getText()+"");
                    startActivityForResult(intent2,3);
                    break;
                case R.id.miCalender:

                    Intent intent3 = new Intent(this, CalendarActivity.class);
                    startActivityForResult(intent3,4);
                    break;
            }

            return true;
        });
    }


    public void onClickTrainingNew(View view)
    {
        Intent intent= new Intent(this,TrainingActivity.class);
        startActivityForResult(intent,1);
    }
    public void onClickFoodNew(View view)
    {
        Intent intent= new Intent(this,FoodActivity.class);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("In Activity Result");

        if(requestCode==1&&resultCode==2){

            System.out.println("success");
            //ntent.putExtra("returnStr","Test");
            System.out.println(data.getIntExtra("trainkcal",0));
            int number=data.getIntExtra("trainkcal",0);

            kcalday=kcalday+number;

            if(kcalday>0)
            {
                //#808080
                tvKcal.setTextColor(Color.parseColor("#808080"));
                tvFeedback.setBackgroundColor(Color.parseColor("#EAF8EA"));
                tvKcalOverUnder.setText("kcal left");
                tvFeedback.setText("You are doing great you are right on Track :D!");
            }
            tvKcal.setText(kcalday+"");
            pb.setMax(kcalday);
        }
        if(requestCode==2&&resultCode==3){

            System.out.println("success");
            //ntent.putExtra("returnStr","Test");
            System.out.println(data.getIntExtra("foodkcal",0));
            int number=data.getIntExtra("foodkcal",0);
            int progress=pb.getProgress();
            pb.setProgress(progress+number);
            kcalday=kcalday-number;

            if(kcalday<0)
            {
                tvKcal.setText(kcalday*-1+"");
                tvKcal.setTextColor(Color.RED);
                tvFeedback.setBackgroundColor(Color.parseColor("#FB7979"));
                tvKcalOverUnder.setText("kcal over");
                tvFeedback.setText("You are over your limit be more careful :(!");
            }
            else {


                tvKcal.setText(kcalday + "");
            }
        }

        if(requestCode==3&&resultCode==4){
            System.out.println("in statistics");
            navMenu.getMenu().getItem(0).setChecked(true);
        }

        if(requestCode==4&&resultCode==5){
            System.out.println("in calendar");
            navMenu.getMenu().getItem(  0).setChecked(true);
        }

    }
    @Override
    public void onBackPressed () {

    }
}
