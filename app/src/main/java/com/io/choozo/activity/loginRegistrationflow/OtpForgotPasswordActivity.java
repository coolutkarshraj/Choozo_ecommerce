package com.io.choozo.activity.loginRegistrationflow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.io.choozo.R;

public class OtpForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    Button changePassword;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_forgot_password);
        initializeViews();
        bindListner();
    }

    private void initializeViews() {
        activity = OtpForgotPasswordActivity.this;
        back = (ImageView)findViewById(R.id.back);
        changePassword = (Button) findViewById(R.id.btnsubmit);
    }

    private void bindListner() {
        back.setOnClickListener(this);
        changePassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;

            case R.id.btnsubmit :
                Intent intent = new Intent(activity,ChangePasswordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
        }

    }
}
