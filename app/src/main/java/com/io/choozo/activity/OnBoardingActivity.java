package com.io.choozo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.activity.loginRegistrationflow.LoginActivity;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class OnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    Activity activity;
    SliderLayout sliderLayout;
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);
        intializeViews();
        bindListner();
        startWorking();
    }



    private void intializeViews() {
        activity = OnBoardingActivity.this;
        btn_start =  findViewById(R.id.btn_start);
        sliderLayout = findViewById(R.id.image);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
       sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :


    }

    private void bindListner() {
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_start :
               Intent i =new Intent(activity, LoginActivity.class);
               startActivity(i);
       }

    }

    private void startWorking() {
        setSliderViews();
    }

    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            SliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                   sliderView.setImageDrawable(R.drawable.shop1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.shop2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.shop3);
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {

                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }


}
