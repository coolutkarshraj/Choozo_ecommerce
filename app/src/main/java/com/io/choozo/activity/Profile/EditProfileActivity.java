package com.io.choozo.activity.Profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.custom.CircularImageView;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.editProfiel.EditProfileResponseModel;
import com.io.choozo.util.ImageUtility;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.PermissionFile;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_WRITE_STORAGE = 1004;
    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 2;
    private static int GalleryPicker = 123;
    int CameraPicker = 124;
    ImageView back;
    Activity activity;
    userOnlineInfo user;
    NewProgressBar dialog;
    Button btnUpdateData;
    Dialog dialog1;
    RelativeLayout rlCameraOpen;
    PermissionFile permissionFile;
    Bitmap bitmap;
    int userId;
    String endPointProfile, token, strName, strLastName, strEmail, strAddress, strdob, strbio, strMobile, endPointEditProfile;
    EditText etName;
    EditText etLastName;
    EditText etEmail;
    EditText etAddress;
    EditText etbio;
    EditText etdob;
    EditText etMobile;
    String strImageFormet;
    String gender;
    ImageView profilePicture;
    Calendar dateSelected = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private PreferenceManager preferenceManager;
    RadioButton genderradioButton;
    RadioGroup radioGroup;
    CircularImageView image;
    RadioButton radioMale, radioFemale;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    File imagefile;
    private File imgFile;
    private String licenseFile = "";
    private ImageUtility imageUtility;
    private File destination;
    private Uri outputFileUri;


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
        imageUtility = new ImageUtility(activity);
        permissionFile = new PermissionFile(activity);
        preferenceManager = new PreferenceManager(this);
        etName = (EditText) findViewById(R.id.et_name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        etEmail = (EditText) findViewById(R.id.et_email);
        etAddress = (EditText) findViewById(R.id.et_address);
        etdob = (EditText) findViewById(R.id.et_dob);
        etbio = (EditText) findViewById(R.id.et_bio);
        etMobile = (EditText) findViewById(R.id.et_phoneNumber);
        back = (ImageView) findViewById(R.id.back);
        btnUpdateData = (Button) findViewById(R.id.btn_update);
        rlCameraOpen = (RelativeLayout) findViewById(R.id.rl_cam);
        profilePicture = (ImageView) findViewById(R.id.image);
        radioMale = findViewById(R.id.radioMale);
        image = findViewById(R.id.image);
        radioFemale = findViewById(R.id.radioFemale);
        getDataFromLocalStorage();
    }


    /* -------------------------------------bind all views that are used in this activity--------------------------------*/

    private void bindListner() {
        back.setOnClickListener(this);
        btnUpdateData.setOnClickListener(this);
        rlCameraOpen.setOnClickListener(this);
        etdob.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;
            case R.id.btn_update:
                editProfileData();
                return;

            case R.id.rl_cam:
                readWritePermission();
                multiplePermission();
                selectImage();
                return;
            case R.id.et_dob:
                datePicker();
                return;
        }

    }

    private void datePicker() {
        Calendar newCalendar = dateSelected;
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
                etdob.setText(sdf.format(dateSelected.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        etdob.setText(sdf.format(dateSelected.getTime()));
        datePickerDialog.show();

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

    private void cameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File root = permissionFile.getFile();
        root.mkdir();
        String fileName = permissionFile.getUniqueImageFilename();
        destination = new File(root, fileName);
        outputFileUri = FileProvider.getUriForFile(getApplicationContext(), activity.getPackageName() + ".provider", destination);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, CameraPicker);

    }

    private void galleryIntent() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setType("image/*");
        startActivityForResult(pickIntent, GalleryPicker);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == GalleryPicker) {
                onCaptureImageResult(data, "gallery");
            }
        } else if (resultCode == RESULT_OK && requestCode == CameraPicker) {
            onCaptureImageResult(data, "camera");

        }
    }

    void onCaptureImageResult(Intent data, String imageType) {
        if (imageType.equals("camera")) {
            licenseFile = imageUtility.compressImage(destination.getPath());
            Log.e("camerapic", licenseFile);
            imagefile = new File(licenseFile);
            if (imagefile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imagefile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }

        } else {
            licenseFile = imageUtility.compressImage(imageUtility.getRealPathFromURI(activity, data.getData()));

            Log.e("gallerypic", licenseFile);

            imgFile = new File(licenseFile);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        strImageFormet = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.e("stringforment",strImageFormet);

        return strImageFormet;
    }



    /*---------------------------------------------------------- api url ------------------------------------------------------*/

    private void apiUrl(){

        endPointProfile = Config.Url.getUserData + userId;
        endPointEditProfile = Config.Url.editProfile;
    }


    /* ---------------------------------------------login data get from local storgae---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
        userId = obj.getData().getUser().getUserId();

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
        if (result.isStatus() == true) {
            strName = result.getData().getName();
            strMobile = result.getData().getPhone();
            strAddress = (String) result.getData().getAddress();
            strdob = String.valueOf(result.getData().getDateOfBirth());
            strbio = (String) result.getData().getBio();
            if (result.getData().getGender() == "Male") {
                radioMale.setChecked(true);
                gender = result.getData().getGender();
            } else {
                radioFemale.setChecked(true);
                gender = result.getData().getGender();
            }
            if (result.getData().getAvatarPath() != null) {
                Glide.with(activity).load(Config.imageUrl + result.getData().getAvatarPath()).into(image);
            }
            dataSetIntoViews();

        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    /* -------------------------------------------------data set into edit text--------------------------------------------*/

    private void dataSetIntoViews() {
        etName.setText(strName);
//        etLastName.setText(strLastName);
        // etEmail.setText(strEmail);
        etMobile.setText(strMobile);
        // etAddress.setText(strAddress);
        etdob.setText(strdob);
        etbio.setText(strbio);
    }

    /*------------------------- edit profile data-----------------------------------*/

    private void editProfileData() {
        strName = etName.getText().toString().trim();
        strMobile = etMobile.getText().toString().trim();
        strdob = etdob.getText().toString().trim();
        strbio = etbio.getText().toString().trim();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);
        if (selectedId == -1) {
            Toast.makeText(activity, "Nothing selected", Toast.LENGTH_SHORT).show();
        } else {
            gender = genderradioButton.getText().toString();
            editprofileApi();
            // Toast.makeText(activity,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void editprofileApi() {
        if (user.isOnline(activity)) {
            dialog = new NewProgressBar(activity);
            dialog.show();
            apiUrl();
            ApiCaller.editProfile(activity, endPointEditProfile, strName, strbio, gender, strdob,
                    strMobile, token, imgFile, new FutureCallback<EditProfileResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, EditProfileResponseModel result) {
                            if (e != null) {
                                return;
                            }
                            if (result.isStatus() == true) {
                                Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                getProfileDataApi();
                            } else {
                                Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

        } else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
