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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.filter.InputFilterMinMax;

public class EditFoodActivity extends AppCompatActivity {
    private TextView textFoodLabel;
    private TextView etGramm;
    private TextView calculatedFoodKcal;
    private int rate;
    private String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.food_edit);
        calculatedFoodKcal=findViewById(R.id.tvCalculatedFoodKcal);
        etGramm=findViewById(R.id.etGramm);
        textFoodLabel=findViewById(R.id.tvFoodLabel);
        etGramm.setFilters(new InputFilter[]{new InputFilterMinMax("1","50000")});

        Intent intent= getIntent();
        name = intent.getStringExtra("foodname");
        double rate=(double) (intent.getIntExtra("calories",0))/100;
        textFoodLabel.setText(name);
        calculatedFoodKcal.setText((intent.getIntExtra("calories",0))+"");
        etGramm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            int gramm;
            if(etGramm.getText().toString().equals("")){
                gramm=0;
            }
            else{
                gramm=Integer.parseInt(etGramm.getText().toString());
            }

                calculatedFoodKcal.setText(rate*(double)gramm+"");
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickFoodEdit(View view)
    {

        Intent intent= new Intent(this,CalculatedActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("foodkcal",(int)Double.parseDouble(calculatedFoodKcal.getText().toString()));
        setResult(3,intent);
        finish();


    }

}
