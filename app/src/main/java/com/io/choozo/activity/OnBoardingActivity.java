package com.io.choozo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.activity.loginRegistrationflow.LoginActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.GetBannerListResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class OnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    Activity activity;
    SliderLayout sliderLayout;
    Button btn_start;
    PreferenceManager preferenceManager;
    String strImage,strImagePath,endPointBanner,endPointImageResize,strImageUrl;

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

    /*------------------------------------- intialize all views that are used in this actvity ----------------------------------------*/

    private void intializeViews() {
        activity = OnBoardingActivity.this;
        preferenceManager = new PreferenceManager(activity);
        btn_start =  findViewById(R.id.btn_start);
        sliderLayout = findViewById(R.id.image);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
    }

    /* -----------------------------------------  bind all Views that are used in this activity ------------------------------------*/

    private void bindListner() {
        btn_start.setOnClickListener(this);
    }

    /* ---------------------------------------------------------- click Listner -------------------------------------------------------*/

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_start :
               preferenceManager.putString(PreferenceManager.isFirstLaunch,"1");
               Intent i =new Intent(activity, LoginActivity.class);
               startActivity(i);
       }
    }

    /* -------------------------------------------------------- start Working --------------------------------------------------------*/
    private void startWorking() {
        getBannerApi();
    }

    /* ------------------------------------------------------ Api url endPoints ------------------------------------------------------*/

    private void apiUrl() {

        endPointBanner = Config.Url.getBanner;
        endPointImageResize = Config.Url.imageResize;
    }

    /*-------------------------------------------------- get Banner from Api ---------------------------------------------------------*/

    private void getBannerApi() {

        apiUrl();
        ApiCaller.getBanner(activity, endPointBanner, new FutureCallback<GetBannerListResponseModel>() {
            @Override
            public void onCompleted(Exception e, GetBannerListResponseModel result) {
                if(e!=null){
                    return;
                }
                getBannerDataFromApi(result);
            }
        });
    }

    /* ---------------------------------------------------- banner data from api response -------------------------------------------*/

    private void getBannerDataFromApi(GetBannerListResponseModel result) {
        if(result.getStatus() == 1){
            for (int i=0 ; i<result.getData().size();i++) {
                strImage = result.getData().get(i).getImage();
                strImagePath = result.getData().get(i).getImagePath();
                strImageUrl = UrlLocator.getFinalUrl(endPointImageResize +"width=3840&height=2160&name="+strImage+"&path="+strImagePath+"");
                SliderView sliderView = new DefaultSliderView(activity);
                sliderView.setImageUrl(strImageUrl);
                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                final int finalI = i;
                sliderLayout.addSliderView(sliderView);
            }
        }else{
            Toast.makeText(activity, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

}
