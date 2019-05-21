package com.io.choozo.activity.splashActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

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
        checkNet();
        startWorking();
    }

    /* --------------------------------intialize all views that are used in this activity--------------------------------------*/

    private void initilizeViews() {

        activity = SplashActivity.this;
    }

    /*------------------------------- check internet if internet is off then application will not open------------------------*/

    private  void checkNet(){
        if (!checkInternetConnection()) {
            Toast.makeText(activity, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectionmanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionmanager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true)

            return true;
        else
            return false;
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
