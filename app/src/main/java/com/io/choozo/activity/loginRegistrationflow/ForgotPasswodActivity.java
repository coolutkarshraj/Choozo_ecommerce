package com.io.choozo.activity.loginRegistrationflow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.model.responseModel.ForgotPasswordResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

public class ForgotPasswodActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    EditText etEmail;
    Button btnRequest;
    Activity activity;
    userOnlineInfo user;
    NewProgressBar dialog;
    String endPoint,strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passwod);
        initializeViews();
        bindListner();
    }

    /* ---------------------------------------intialize all views that are used in this activity-------------------------------*/
    private void initializeViews() {
        user = new userOnlineInfo();
        activity = ForgotPasswodActivity.this;
        back = (ImageView)findViewById(R.id.back);
        etEmail = (EditText)findViewById(R.id.et_email);
        btnRequest = (Button)findViewById(R.id.btnrquest);

    }

    /*-------------------------------------------------- bindListner------------------------------------------------------------*/

    private void bindListner() {
        back.setOnClickListener(this);
        btnRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;

            case R.id.btnrquest :
                forgotPasswordView();
                return;
        }

    }

    /* --------------------------------------------------------Validate data----------------------------------------------*/

    private void forgotPasswordView() {
     strEmail = etEmail.getText().toString().trim();
     if(strEmail.equals("")){
         etEmail.setError("Please Enter Email-Id");
     }else {
         forgotPasswordApi();
     }
    }


    private  void apiUrl(){
        endPoint = Config.Url.forgotPassword;
    }

    /*------------------------------------------------------- forgot api ---------------------------------------------------*/

    private void forgotPasswordApi() {
        if(user.isOnline(activity)){
            dialog = new NewProgressBar(activity);
            dialog.show();
            apiUrl();
            ApiCaller.forgotPassword(activity, endPoint, strEmail, new
                    FutureCallback<ForgotPasswordResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, ForgotPasswordResponseModel result) {
                          dialog.dismiss();
                            if(e!=null){
                                return;
                            }
                            if(result.getStatus()){
                                apiData(result);

                            }else {
                                Toast.makeText(activity, result.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }else {
            commonDialog comdialog = new commonDialog();
            comdialog.dialogbox(activity);
        }


    }

    private void apiData(ForgotPasswordResponseModel result) {
        if(result.getStatus()){
            dialog.dismiss();
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            Intent i =new Intent(activity, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }
}
