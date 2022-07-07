package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class CalculatedActivity extends AppCompatActivity {

    private TextView tvKcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /*
        intent.putExtra("age",age);
        intent.putExtra("height",height);
        intent.putExtra("goalweight",goalweight);
        intent.putExtra("currentweight",currentweight);
        intent.putExtra("selected",selected);*/

        setContentView(R.layout.homepage);
        Intent intent = getIntent();
        tvKcal = findViewById(R.id.tvKcal);
        int age=intent.getIntExtra("age",0);
        int height=intent.getIntExtra("height",0);
        int goalweight=intent.getIntExtra("goalweight",0);
        int currentweight=intent.getIntExtra("currentweight",0);
        double kcalday=0;
        String gender= intent.getStringExtra("selected");
        if(gender.equals("male"))
        {
            //Männer: GU (kcal/Tag) = 66 + (13,8 x Gewicht in kg) + (5,0 x Größe in cm) – (6,8 x Alter in Jahren)
            kcalday=66+(13.8*currentweight)+(5*height)-(6.8*age);
        }
       else if(gender.equals("female")) {
            //   Frauen: GU (kcal/Tag) = 655 + (9,5 x Gewicht in kg) + (1,9 x Größe in cm) – (4,7 x Alter in Jahren)
            kcalday=655+(9.5*currentweight)+(1.9*height)-(4.7*age);
        }
        tvKcal.setText(kcalday+"");
    }
}
