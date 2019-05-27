package com.io.choozo.activity.About;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.model.responseModel.ContactUsResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvEmail,tvPhone,tvAddress,tvOpeningTime;
    String strEmail,strPhone,strAddress,strFacebookUrl,strGoogleUrl,strTwitter,endPoint;
    userOnlineInfo user;
    NewProgressBar dialog;
    Activity activity;
    ImageView ivFacebook,ivGooglePlus,ivTwitter,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        intializeViews();
        bindListner();
        startWorking();
    }


    /* ---------------------------------------------intialize views that are used in this activity----------------------------------*/
    private void intializeViews() {
        activity = ContactUsActivity.this;
        user = new userOnlineInfo();
        dialog = new NewProgressBar(activity);
        tvEmail = (TextView)findViewById(R.id.tv_email);
        tvPhone = (TextView)findViewById(R.id.tv_phone);
        tvAddress = (TextView)findViewById(R.id.tv_address);
        tvOpeningTime = (TextView)findViewById(R.id.tv_open);
        ivFacebook = (ImageView)findViewById(R.id.iv_facebook);
        ivGooglePlus = (ImageView)findViewById(R.id.iv_google);
        ivTwitter = (ImageView)findViewById(R.id.iv_twitter);
        back = (ImageView)findViewById(R.id.back);
    }

    /* ------------------------------------------bind all views that are used in this activity--------------------------------------*/
    private void bindListner() {
        ivFacebook.setOnClickListener(this);
        ivGooglePlus.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);
        back.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_facebook:
                facebookIntent();
                return;

            case R.id.iv_google:
                googlePlusIntent();
                return;

            case R.id.iv_twitter:
                twitterIntent();
                return;
            case R.id.back:
                onBackPressed();
                return;


        }

    }


    private void apiUrl(){
        endPoint = Config.Url.getSetting;

    }

    /*------------------------------------------------------- start working--------------------------------------------------------*/

    private void startWorking() {
        contactUsInfoGetFromApi();


    }

    /* ----------------------------------------------------data get from api-------------------------------------------------------*/

    private void contactUsInfoGetFromApi() {
        if (user.isOnline(activity)){
            dialog.show();
            apiUrl();
            ApiCaller.getSettings(activity, endPoint, new FutureCallback<ContactUsResponseModel>() {
                @Override
                public void onCompleted(Exception e, ContactUsResponseModel result) {
                    if(e!=null){
                        dialog.dismiss();
                        return;
                    }
                    dataSetIntoViews(result);
                }
            });

        }else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }

    }

    /* -----------------------------------------------------data set into views------------------------------------------------------*/

    private void dataSetIntoViews(ContactUsResponseModel result) {
        if(result.getStatus() ==1){
            dialog.dismiss();
            tvEmail.setText(result.getData().get(0).getStoreEmail());
            tvPhone.setText(result.getData().get(0).getStoreTelephone());
            tvAddress.setText(result.getData().get(0).getStoreAddress());
            strFacebookUrl = result.getData().get(0).getFacebook();
            strGoogleUrl = result.getData().get(0).getGoogle();
            strTwitter = result.getData().get(0).getTwitter();
        }
    }

    /* ------------------------------------------------------------social links open------------------------------------------------*/

    private void facebookIntent() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(strFacebookUrl));
        startActivity(i);
    }

    private void googlePlusIntent() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(strGoogleUrl));
        startActivity(i);
    }

    private void twitterIntent() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(strTwitter));
        startActivity(i);
    }


}
