package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatedActivity extends AppCompatActivity {

    private TextView tvKcal;
    private TextView tvFeedback;
    private ProgressBar pb;
    private int kcalday = 0;
    private int summ=0;
    private TextView tvKcalOverUnder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homepage);
        tvKcal = findViewById(R.id.tvKcal);
        pb = findViewById(R.id.progressBar);
        tvFeedback = findViewById(R.id.tvFeedback);
        tvKcalOverUnder = findViewById(R.id.tvKcalOverUnder);
        tvFeedback.setBackgroundColor(Color.parseColor("#EAF8EA"));

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

    }
    @Override
    public void onBackPressed () {

    }
}
