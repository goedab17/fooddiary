package com.example.test;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.filter.InputFilterMinMax;

import java.time.LocalDate;
import java.util.Date;

public class EditTrainingActivity extends AppCompatActivity {
    private TextView textLabel;
    private EditText etMinutes;
    private TextView calculatedKcal;
    private TextView tvKcal;
    private ProgressBar pb;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.training_edit);
        calculatedKcal=findViewById(R.id.tvCalculatedKcal);
        etMinutes=findViewById(R.id.etMinutes);
        etMinutes.setFilters(new InputFilter[]{new InputFilterMinMax("1","10000")});
        textLabel=findViewById(R.id.tvLabel);


        Intent intent = getIntent();
         name=intent.getStringExtra("name");
        Double number = intent.getDoubleExtra("rate",0);
        int minutes=Integer.parseInt(etMinutes.getText()+"");
        textLabel.setText(name);
        calculatedKcal.setText(number*minutes+"");

        etMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int minutes;
                if(etMinutes.getText().toString().equals(""))
                {
                    minutes=0;
                }
                else{
                    minutes=Integer.parseInt(etMinutes.getText().toString());
                }

                Double number = intent.getDoubleExtra("rate",0);
                calculatedKcal.setText(number*minutes+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickTrainingEdit(View view)
    {

        Intent intent= new Intent(this,CalculatedActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("trainkcal",(int)Double.parseDouble(calculatedKcal.getText().toString()));
        setResult(2,intent);
        finish();


    }

}
