package com.io.choozo.activity.loginRegistrationflow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.io.choozo.R;

public class ForgotPasswodActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    Button otpScreen;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passwod);
        initializeViews();
        bindListner();
    }


    private void initializeViews() {
        activity = ForgotPasswodActivity.this;
        back = (ImageView)findViewById(R.id.back);
        otpScreen = (Button)findViewById(R.id.btnrquest);

    }

    private void bindListner() {
        back.setOnClickListener(this);
        otpScreen.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;

            case R.id.btnrquest :
                Intent intent = new Intent(activity,OtpForgotPasswordActivity.class);
                startActivity(intent);
                return;
        }

    }
}
