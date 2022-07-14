package com.example.test;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homepage);
        tvKcal = findViewById(R.id.tvKcal);
        pb = findViewById(R.id.progressBar);
        tvFeedback = findViewById(R.id.tvFeedback);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("In Activity Result");

        if(requestCode==1&&resultCode==2){

            System.out.println("success");
            //ntent.putExtra("returnStr","Test");
            System.out.println(data.getIntExtra("trainkcal",0));
            int number=data.getIntExtra("trainkcal",0);

            summ=summ+number;
            if(pb.getProgress()<=summ)
            {
                tvFeedback.setText("You are doing great you are right on Track :D!");
            }
            tvKcal.setText(summ+kcalday+ "");
            pb.setMax(summ+kcalday);
        }
    }
}
