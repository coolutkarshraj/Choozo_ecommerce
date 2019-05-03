package com.io.choozo.activity.splashActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.io.choozo.activity.OnBoardingActivity;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.R;

public class SplashActivity extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initilizeViews();
        startWorking();

    }
    private void initilizeViews() {

        activity = SplashActivity.this;
    }

    private void startWorking() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, OnBoardingActivity.class);
                startActivity(intent);
            }
        },4000);
    }


}
