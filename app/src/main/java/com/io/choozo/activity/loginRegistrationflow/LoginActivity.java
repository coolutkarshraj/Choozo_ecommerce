package com.io.choozo.activity.loginRegistrationflow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.CustomerRegisterResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.editProfiel.EditProfileResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import static com.io.choozo.localStorage.PreferenceManager.loginData;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout loginLayout,registerLayout;
    TextView loginOrRegister;
    RelativeLayout imageLogin,imageRegister;
    RelativeLayout loginTab,registerTab;
    Activity activity;
    TextView tvSkip, tvForgotPassword;
    userOnlineInfo user;
    NewProgressBar dialog;
    private PreferenceManager preferenceManager;

    /* Registration views*/
    EditText etName,etEmail,etPhone,etPassword;
    String strName,strEmail,strPhone,strPassword,strConfrimPassword,endpointRegistration;
    Button btnRegister;

    /* Login views*/
    Button btnLogin;
    EditText loginEmail,loginPassword;
    String strLoginEmail,strLoginPassword,endPointLogin,token,userr;
    private String endPointEditProfile;
    private String gender = "Not Defined";
    private String bio = "Dincharya user";
    private String dateOfBirth = "Not Defined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        bindListner();
    }

    private void initializeViews() {
      activity = LoginActivity.this;
      user = new userOnlineInfo();
      preferenceManager = new PreferenceManager(this);
      loginLayout = (RelativeLayout)findViewById(R.id.loginlayout) ;
      registerLayout = (RelativeLayout)findViewById(R.id.register_layout) ;
      loginOrRegister = (TextView) findViewById(R.id.tv_signin);
      imageLogin = (RelativeLayout)findViewById(R.id.image_login);
      imageRegister = (RelativeLayout)findViewById(R.id.image_signup);
      loginTab = (RelativeLayout)findViewById(R.id.tab);
      registerTab = (RelativeLayout)findViewById(R.id.tab1);
      tvSkip = (TextView) findViewById(R.id.tv_skip);
      tvForgotPassword = (TextView) findViewById(R.id.tv_forgotpassword);
      intializeViewsRegistration();
      intializeViewsLogin();
    }


    /*------------------------------------- intialize Views of Registration layout--------------------------------------------*/

    private void intializeViewsRegistration() {
        etName = (EditText)findViewById(R.id.et_name);
        etEmail = (EditText)findViewById(R.id.et_email);
        etPhone = (EditText)findViewById(R.id.et_phone);
        etPassword = (EditText)findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);

    }
    /*------------------------------------- intialize Views of Login layout--------------------------------------------------*/

    private void intializeViewsLogin() {
        btnLogin = (Button) findViewById(R.id.btnlogin);
        loginEmail = (EditText) findViewById(R.id.et_login_email);
        loginPassword = (EditText) findViewById(R.id.et_login_password);
    }

    /*------------------------------------------- bind all views that are used in this activity-------------------------------*/
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
                return;

            /*case R.id.tv_skip :
                Intent i = new Intent(activity,MainActivity.class);
                startActivity(i);
                return;*/

            case R.id.tv_forgotpassword :
                Intent intent = new Intent(activity,ForgotPasswodActivity.class);
                startActivity(intent);
                return;
        }

    }

    /* -------------------------------------------------Registrion layout work-----------------------------------------------*/

    private void registrationStartWorking() {
        registrationValidation();
    }

    /* --------------------------------------------------registration validation----------------------------------------------*/

    private void registrationValidation() {
        strName = etName.getText().toString().trim();
        strEmail = etEmail.getText().toString().trim();
        strPhone = etPhone.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        strConfrimPassword = etPassword.getText().toString().trim();

        if(strName.equals("") || strEmail.equals("") || strPhone.equals("") ||strPassword.equals("")){
            etName.setError("Please Enter Name");
            etEmail.setError("Please Enter Email");
            etPhone.setError("Please Enter Phone");
            etPassword.setError("Please Enter Password");
        }
        else {
            registrationApi();
        }
    }

    /*---------------------------------------------------- api's endpoint----------------------------------------------------*/
    private void urlapi()
    {
        endpointRegistration = Config.Url.registerCustomer;
        endPointLogin = Config.Url.loginCustomer;
        endPointEditProfile = Config.Url.editProfile;
    }

    /*----------------------------------------------- Registration Api------------------------------------------------------*/

    private void registrationApi() {
        if (user.isOnline(activity)) {
        dialog = new NewProgressBar(activity);
        dialog.show();
         urlapi();
         ApiCaller.registerCustomer(activity, endpointRegistration, strName, strEmail, strPhone, strPassword, strConfrimPassword,
                 new FutureCallback<CustomerRegisterResponseModel>() {
                     @Override
                     public void onCompleted(Exception e, CustomerRegisterResponseModel result) {
                         if(e!=null){
                             dialog.dismiss();
                             return;
                         }
                         if(result.getStatus()){
                             dialog.dismiss();
                             strLoginEmail = strEmail;
                             strLoginPassword = strPassword;
                             registrationApiData(result);
                         }else {
                             Toast.makeText(activity, result.getMessage(), Toast.LENGTH_SHORT).show();
                             dialog.dismiss();
                         }

                     }
                 });
        }else {
            commonDialog comdialog = new commonDialog();
            comdialog.dialogbox(activity);
        }

    }

    /* --------------------------------------------registration data from api----------------------------------------------*/

    private void registrationApiData(CustomerRegisterResponseModel result) {
      if(result.getStatus()){
          loginApi(0);
      }else {
          Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
          dialog.dismiss();
      }

    }

    /* -----------------------------------------------Login layout work-----------------------------------------------------*/

    private void loginStartWorking() {
        loginValidations();
    }


    /* -------------------------------validations apply for login views like email and password-----------------------------*/

    private void loginValidations() {
        strLoginEmail = loginEmail.getText().toString().trim();
        strLoginPassword = loginPassword.getText().toString().trim();
        if(strLoginEmail.equals("") || strLoginPassword.equals("")){
            loginEmail.setError("Please Enter Email");
            loginPassword.setError("Please Enter Password");
        }
        else {
            loginApi(1);
        }
    }

    /* ------------------------------------------------------lOGIN Api-------------------------------------------------------*/
    private void loginApi(int i) {
        if (user.isOnline(activity)) {
            dialog = new NewProgressBar(activity);
            dialog.show();
            urlapi();
            ApiCaller.loginCustomer(activity, endPointLogin, strLoginEmail, strLoginPassword,
                    new FutureCallback<LoginResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, LoginResponseModel result) {
                            if(e!=null){
                                dialog.dismiss();
                                return;
                            }
                            if(result.getStatus()){
                                dialog.dismiss();
                                loginData(result,i);
                            }else {
                                Toast.makeText(activity, result.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                        }
                    });

        }else {
            commonDialog comdialog = new commonDialog();
            comdialog.dialogbox(activity);
        }


    }

    /*-------------------------------------------------- Login data from api------------------------------------------------*/

    private void loginData(LoginResponseModel result, int i) {

        if(result.getStatus()){
            dialog.dismiss();
            if(i == 1){
                saveLoginData(result);
                Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity , MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }else {
                editprofileApi(result);
            }



        }
        else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        }
    }

    /* ----------------------------------------save Login data into sharedPrefrence ---------------------------------*/

    private void saveLoginData(LoginResponseModel result) {
        Gson gson = new Gson();
        String json = gson.toJson(result);
        preferenceManager.putString(loginData,json);



    }

    private void editprofileApi(LoginResponseModel loginResult) {
        if(user.isOnline(activity)){
            urlapi();
            dialog = new NewProgressBar(activity);
            dialog.show();
            ApiCaller.editProfileSignup(activity, endPointEditProfile, strName, bio, gender,
                    dateOfBirth,strPhone, loginResult.getData().getToken(), new FutureCallback<EditProfileResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, EditProfileResponseModel result) {
                            if(e!=null){
                                dialog.dismiss();
                                return;
                            }
                            if(result.isStatus()){
                                dialog.dismiss();
                                saveLoginData(loginResult);
                                Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(activity , MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }else {
                                Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

        }else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }
    }


}
