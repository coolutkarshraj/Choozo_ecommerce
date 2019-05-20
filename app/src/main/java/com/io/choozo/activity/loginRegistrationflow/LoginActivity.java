package com.io.choozo.activity.loginRegistrationflow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout loginLayout,registerLayout;
    TextView loginOrRegister;
    RelativeLayout imageLogin,imageRegister;
    RelativeLayout loginTab,registerTab;
    Activity activity;
    Button btnLogin,btnRegister;
    TextView tvSkip, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        bindListner();
    }

    private void initializeViews() {
      activity = LoginActivity.this;
      loginLayout = (RelativeLayout)findViewById(R.id.loginlayout) ;
      registerLayout = (RelativeLayout)findViewById(R.id.register_layout) ;
      loginOrRegister = (TextView) findViewById(R.id.tv_signin);
      imageLogin = (RelativeLayout)findViewById(R.id.image_login);
      imageRegister = (RelativeLayout)findViewById(R.id.image_signup);
      loginTab = (RelativeLayout)findViewById(R.id.tab);
      registerTab = (RelativeLayout)findViewById(R.id.tab1);
      btnRegister = (Button) findViewById(R.id.btn_register);
      btnLogin = (Button) findViewById(R.id.btnlogin);
      tvSkip = (TextView) findViewById(R.id.tv_skip);
      tvForgotPassword = (TextView) findViewById(R.id.tv_forgotpassword);
    }


    private void bindListner() {
        imageLogin.setOnClickListener(this);
        imageRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.image_login :
                loginLayout.setVisibility(View.VISIBLE);
                registerLayout.setVisibility(View.GONE);
                loginOrRegister.setText("Sign In");
                registerTab.setBackgroundColor(Color.parseColor("#000000"));
                loginTab.setBackgroundColor(Color.parseColor("#ff0000"));
                return;

            case R.id.image_signup :
                registerLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                loginOrRegister.setText("Sign Up");
                loginTab.setBackgroundColor(Color.parseColor("#000000"));
                registerTab.setBackgroundColor(Color.parseColor("#ff0000"));
                return;

             case  R.id.btn_register :
                    registrationStartWorking();
                    return;

            case R.id.btnlogin :
                loginStartWorking();

            case R.id.tv_skip :
                Intent i = new Intent(activity,MainActivity.class);
                startActivity(i);

            case R.id.tv_forgotpassword :
                Intent intent = new Intent(activity,ForgotPasswodActivity.class);
                startActivity(intent);
        }

    }

    /* -------------------------------------------------Registrion layout work----------------------------------------*/

    private void registrationStartWorking() {
        Intent i =new Intent(activity, MainActivity.class);

        startActivity(i);
    }

    /* ----------------------------------------------------Login layout work----------------------------------------*/

    private void loginStartWorking() {
        Intent i =new Intent(activity, MainActivity.class);

        startActivity(i);
    }
}
