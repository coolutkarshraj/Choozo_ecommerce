package com.io.choozo.Fragment.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.activity.Profile.EditProfileActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.ChangePasswordResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import static com.io.choozo.localStorage.PreferenceManager.loginData;

public class Profile_Information extends Fragment implements View.OnClickListener {
    Dialog dialog;
    Activity activity;
    ImageView imEditProfile;
    TextView tvName,tvEmail,tvMobile;
    String token,strOldPassword,strNewPassword,endPoint,endPointProfile;
    RelativeLayout rl_changepassword;
    userOnlineInfo user;
    NewProgressBar dialogs;
    private PreferenceManager preferenceManager;


    public Profile_Information(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_information,container,false);
        intializeViews(v);
        bindListner();
        startWorking();
        return v;
    }

    /* -------------------------------------intialize all views that are used in this activity----------------------------*/

    private void intializeViews(View v) {
        activity = getActivity();
        user = new userOnlineInfo();
        preferenceManager = new PreferenceManager(activity);
        tvName = (TextView)v.findViewById(R.id.tv_name);
        imEditProfile = (ImageView) v.findViewById(R.id.edit_profile);
        tvEmail = (TextView)v.findViewById(R.id.tv_email);
        tvMobile = (TextView)v.findViewById(R.id.tv_mobile);
        rl_changepassword = (RelativeLayout)v.findViewById(R.id.rl_changepassword);
        getDataFromLocalStorage();
    }

    /* -------------------------------------bind all views that are used in this activity---------------------------------*/

    private void bindListner() {
        rl_changepassword.setOnClickListener(this);
        imEditProfile.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_changepassword :
                changePassword();

            case R.id.edit_profile :
                Intent i =new Intent(activity, EditProfileActivity.class);
                startActivity(i);

        }

    }

    private void startWorking() {
        getProfileDataApi();
    }

    /*-------------------------------------------------- get profile data from  api-----------------------------------------*/

    public  void getProfileDataApi() {

        if(user.isOnline(activity)){

            apiUrl();
            ApiCaller.getUserProfile(activity, endPointProfile, token, new FutureCallback<GetProfileResponseModel>() {
                @Override
                public void onCompleted(Exception e, GetProfileResponseModel result) {
                    if(e!=null){
                        return;
                    }
                      getProfileData(result);
                }
            });

        }else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }

    }

    /* --------------------------------------------- data set into text views---------------------------------------------*/

    private void getProfileData(GetProfileResponseModel result) {
        if(result.getStatus()==1){

         tvName.setText(result.getData().getFirstName() +" "+result.getData().getLastName());
         tvEmail.setText(result.getData().getEmail());
         tvMobile.setText(result.getData().getMobileNumber());
         dataAddIntoLocalStorage(result);

        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    /*-------------------------------------------- profile data add into local storage---------------------------------------*/

    private void dataAddIntoLocalStorage(GetProfileResponseModel result) {
        Gson gson = new Gson();
        String json = gson.toJson(result);
        preferenceManager.putString(PreferenceManager.profileData,json);
    }

    /* ---------------------------------------------login data get from local storgae---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();

    }

    /*------------------------------------------------- change password dialog---------------------------------------------*/
    public void changePassword() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.change_password_dialog);
        dialog.setTitle("");
        final TextView Yes = (TextView) dialog.findViewById(R.id.yes);
        final TextView No = (TextView) dialog.findViewById(R.id.no);
        final EditText oldPassword = (EditText) dialog.findViewById(R.id.et_old_password);
        final EditText newPassword = (EditText) dialog.findViewById(R.id.et_new_password);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changePasswordValidateData(oldPassword,newPassword);
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    /*--------------------------------------------------- Change Password validate data---------------------------------------*/

    private void changePasswordValidateData(EditText oldPassword, EditText newPassword) {
         strOldPassword = oldPassword.getText().toString().trim();
         strNewPassword = newPassword.getText().toString().trim();
         if(strOldPassword.equals("") || strNewPassword.equals("")) {
             oldPassword.setError("Please enter old password");
             newPassword.setError("Please enter new password");
         }else {
           changePasswordApi();
         }

    }

    /*----------------------------------------------------- api send point------------------------------------------------*/

    private void apiUrl(){
        endPoint = Config.Url.changePassword;
        endPointProfile = Config.Url.getUserData;
    }


    /* -------------------------------------------------------change password Api------------------------------------------*/
    private void changePasswordApi() {

       if(user.isOnline(activity)){
           dialogs = new NewProgressBar(activity);
           dialogs.show();
           apiUrl();
           ApiCaller.changePassword(activity, endPoint, strOldPassword, strNewPassword, token,
                   new FutureCallback<ChangePasswordResponseModel>() {
                       @Override
                       public void onCompleted(Exception e, ChangePasswordResponseModel result) {
                           if(e!=null){
                               return;
                           }
                           changePasswordData(result);
                       }
                   });
       }else {
           commonDialog commonDialog = new commonDialog();
           commonDialog.dialogbox(activity);
       }
    }

    /* ------------------------------------------------change password api data----------------------------------------*/

    private void changePasswordData(ChangePasswordResponseModel result) {
        if(result.getStatus() == 1){
            dialogs.dismiss();
            dialog.dismiss();
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            dialogs.dismiss();
            dialog.dismiss();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
