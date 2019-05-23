package com.io.choozo.activity.Profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.EditProfileDataModel;
import com.io.choozo.model.responseModel.EditProfileResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.PermissionFile;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_WRITE_STORAGE = 1004;
    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 2;
    ImageView back;
    Activity activity;
    userOnlineInfo user ;
    NewProgressBar dialog;
    Button btnUpdateData;
    Dialog dialog1;
    RelativeLayout rlCameraOpen;
    PermissionFile permissionFile;
    Bitmap bitmap;
    String endPointProfile ,token,strName, strLastName,strEmail
            ,strAddress,strCountyId,strPinCode,strMobile,endPointEditProfile;
    EditText etName;
    EditText etLastName;
    EditText etEmail;
    EditText etAddress;
    EditText etCountryId;
    EditText etPinCode;
    EditText etMobile;
    String strImageFormet;
    ImageView profilePicture;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeViews();
        bindListner();
        startWorking();
    }

    /* -------------------------------------intialize all views that are used in this activity-----------------------------*/

    private void initializeViews() {
        activity = EditProfileActivity.this;
        user = new userOnlineInfo();
        permissionFile = new PermissionFile(activity);
        preferenceManager = new PreferenceManager(this);
        etName = (EditText)findViewById(R.id.et_name);
        etLastName = (EditText)findViewById(R.id.et_lastname);
        etEmail = (EditText)findViewById(R.id.et_email);
        etAddress = (EditText)findViewById(R.id.et_address);
        etCountryId = (EditText)findViewById(R.id.et_countryid);
        etPinCode = (EditText)findViewById(R.id.et_pinCode);
        etMobile = (EditText)findViewById(R.id.et_phoneNumber);
        back = (ImageView)findViewById(R.id.back);
        btnUpdateData = (Button) findViewById(R.id.btn_update);
        rlCameraOpen = (RelativeLayout) findViewById(R.id.rl_cam);
        profilePicture = (ImageView) findViewById(R.id.image);
        getDataFromLocalStorage();
    }


    /* -------------------------------------bind all views that are used in this activity--------------------------------*/

    private void bindListner() {
        back.setOnClickListener(this);
        btnUpdateData.setOnClickListener(this);
        rlCameraOpen.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;
            case R.id.btn_update :
                editProfileData();
                return;

            case R.id.rl_cam:
                readWritePermission();
                multiplePermission();
                selectImage();
                return;
        }

    }
    
    private void startWorking() {
        getProfileDataApi();
    }

    private void readWritePermission() {
        boolean hasPermission = (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    private void multiplePermission() {
        if (!permissionFile.checkLocStorgePermission(activity)) {
            permissionFile.checkLocStorgePermission(activity);
        }
    }

    private void selectImage() {  // Dialog for select Image from Gallaery and Camera

        dialog1 = new Dialog(activity);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog1.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setContentView(R.layout.user_profile_change_dailog);
        dialog1.setTitle("");
        final TextView Takephoto = (TextView) dialog1.findViewById(R.id.take);
        final TextView Gallery = (TextView) dialog1.findViewById(R.id.gall);
        final ImageView cancel = (ImageView) dialog1.findViewById(R.id.clear);
        final TextView Choose=(TextView)dialog1.findViewById(R.id.chooseoption);

        cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        Takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
            }
        });
        Gallery.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                galleryIntent();
            }
        });
        dialog1.show();

    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        profilePicture.setImageBitmap(bitmap);
        String img=getStringImage(bitmap);
        dialog1.dismiss();
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        profilePicture.setImageBitmap(bitmap);
        String img=getStringImage(bitmap);
        dialog1.dismiss();

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();

        strImageFormet = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.e("stringforment",strImageFormet);

        return strImageFormet;
    }



    /*---------------------------------------------------------- api url ------------------------------------------------------*/

    private void apiUrl(){

        endPointProfile = Config.Url.getUserData;
        endPointEditProfile = Config.Url.editProfile;
    }


    /* ---------------------------------------------login data get from local storgae---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();

    }


    /*-------------------------------------------------- get profile data from  api-----------------------------------------*/

    private void getProfileDataApi() {

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

    /* --------------------------------------------- api data pass into string---------------------------------------------*/

    private void getProfileData(GetProfileResponseModel result) {
        if(result.getStatus()==1){
            strName = result.getData().getFirstName();
            strLastName = result.getData().getLastName();
            strEmail = result.getData().getEmail();
            strMobile = result.getData().getMobileNumber();
            strAddress = (String) result.getData().getAddress();
            strCountyId = String.valueOf(result.getData().getCountryId());
            strPinCode = (String) result.getData().getPincode();
            dataSetIntoViews();
            
        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    /* -------------------------------------------------data set into edit text--------------------------------------------*/

    private void dataSetIntoViews() {
        etName.setText(strName);
        etLastName.setText(strLastName);
        etEmail.setText(strEmail);
        etMobile.setText(strMobile);
        etAddress.setText(strAddress);
        etCountryId.setText(strCountyId);
        etPinCode.setText(strPinCode);
    }

    /*------------------------- edit profile data-----------------------------------*/

    private void editProfileData() {
        strName = etName.getText().toString().trim();
        strLastName = etLastName.getText().toString().trim();
        strEmail = etEmail.getText().toString().trim();
        strMobile = etMobile.getText().toString().trim();
        strAddress = etAddress.getText().toString().trim();
        strCountyId = etCountryId.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        editprofileApi();
    }

    private void editprofileApi() {
        if(user.isOnline(activity)){
           dialog = new NewProgressBar(activity);
           dialog.show();
            apiUrl();
            ApiCaller.editProfile(activity, endPointEditProfile, strName, strLastName, strEmail, strAddress, strCountyId,
                    strPinCode, strMobile, token,strImageFormet, new FutureCallback<EditProfileResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, EditProfileResponseModel result) {
                            if(e!=null){
                                return;
                            }
                                if(result.getStatus()==1){
                                    Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    getProfileDataApi();
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
