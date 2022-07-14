package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.bl.IOAccess;

public class MainActivity extends AppCompatActivity {
    private Button btGoNext;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btGoNext=findViewById(R.id.btNext);

    }
    public void onClickNext(View view)
    {

        Intent intent= new Intent(this,DataActivity.class);
        startActivity(intent);

    }

    public static void main(String[] args) {

    }
}