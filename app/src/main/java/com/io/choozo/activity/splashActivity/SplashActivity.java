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

import com.google.gson.Gson;
import com.io.choozo.activity.OnBoardingActivity;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.R;
import com.io.choozo.activity.loginRegistrationflow.LoginActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.LoginResponseModel;

public class SplashActivity extends AppCompatActivity {

    Activity activity;
    PreferenceManager preferenceManager;
    String strEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initilizeViews();
        checkNet();
        getSharedPreferanceData();
        startWorking();
    }

    /* --------------------------------intialize all views that are used in this activity--------------------------------------*/

    private void initilizeViews() {
        activity = SplashActivity.this;
        preferenceManager = new PreferenceManager(activity);

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

    private void getSharedPreferanceData() {
        try {
            Gson gson = new Gson();
            String json = preferenceManager.getString(PreferenceManager.loginData);
            LoginResponseModel obj = gson.fromJson(json, LoginResponseModel.class);
            strEmail = obj.getData().getUser().getEmail();
            preferenceManager.putString(PreferenceManager.email, strEmail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void startWorking() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            navigateScreen();
            }
        },4000);
    }


    private void navigateScreen() {
        if(preferenceManager.getString(PreferenceManager.isFirstLaunch).equals("") ){
            goToInTroSliderScreen();
        }else if (preferenceManager.getString(PreferenceManager.loginData).equals("")){
            gotoLoginScreen();
        }
        else {
            if(preferenceManager.getString(PreferenceManager.email).equals("")){
                gotoLoginScreen();
            }else {
                AfterLoginScreen();
            }

        }
    }

    private void goToInTroSliderScreen() {
        Intent intent = new Intent(activity,OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private void gotoLoginScreen() {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void AfterLoginScreen(){
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
