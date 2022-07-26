package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.test.bl.IOAccess;

public class MainActivity extends AppCompatActivity {
    private Button btGoNext;
    private String value;
    private LottieAnimationView lottie;
    private ImageView imView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        lottie=findViewById(R.id.lottie);
        imView=findViewById(R.id.ivTitle);
        imView.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        lottie.animate().translationY(1500).setDuration(1000).setStartDelay(5000);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,DataActivity.class));
            }
        },6000);


    }
}