package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    private EditText etAge;
    private EditText currentWeight;
    private EditText wishWeight;
    private EditText etHeight;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_input);
        etAge=findViewById(R.id.Input_Age);
        wishWeight=findViewById(R.id.Input_Zielgewicht);
        currentWeight=findViewById(R.id.Input_CurrentWeight);
        spinner = findViewById(R.id.spinner);
        etHeight=findViewById(R.id.input_height);
        etAge.setFilters(new InputFilter[]{new InputFilterMinMax("1","120")});
        etHeight.setFilters(new InputFilter[]{new InputFilterMinMax("1","250")});
        wishWeight.setFilters(new InputFilter[]{new InputFilterMinMax("1","999")});
        currentWeight.setFilters(new InputFilter[]{new InputFilterMinMax("1","999")});

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("male");
        spinnerArray.add("female");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void onSubmit(View view)
    {
        try {
            int age = Integer.parseInt(etAge.getText().toString());
            int height = Integer.parseInt(etHeight.getText().toString());
            int goalweight = Integer.parseInt(wishWeight.getText().toString());
            int currentweight = Integer.parseInt(currentWeight.getText().toString());
            String selected = spinner.getSelectedItem().toString();

            Intent intent= new Intent(this,CalculatedActivity.class);
            intent.putExtra("age",age);
            intent.putExtra("height",height);
            intent.putExtra("goalweight",goalweight);
            intent.putExtra("currentweight",currentweight);
            intent.putExtra("selected",selected);
            if(height<=45 || currentweight<=30 || age <10){
                Toast.makeText(this,"Please enter valid numbers",Toast.LENGTH_LONG).show();

            }
            else {

                startActivity(intent);
            }
        }
        catch (NumberFormatException ex){
            Toast.makeText(this,"Fill out all the fields",Toast.LENGTH_LONG).show();
        }



    }

}
