package com.io.choozo.Fragment.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.SavedAdressRvAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;
import com.io.choozo.model.responseModel.AddAddressResponseModel;
import com.io.choozo.model.responseModel.GetAddressResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress  extends Fragment implements View.OnClickListener {

    public static RecyclerView rvSavedAdress;
    String addressType = "0";
    public static SavedAdressRvAdapter adapter;
    List<SavedAdressDataModel> item =new ArrayList<>();
    Activity activity;
    Button btnAddAddress;
    Dialog dialog;
    NewProgressBar dialogs;
    userOnlineInfo user;
    String strAddress1;
    String strAddress2;
    String strCity;
    String strState;
    String strPinCode;
    String token;
    String strendPointAddAdres,endPointGetAddress;
    int userId;
    private PreferenceManager preferenceManager;

    public SavedAddress(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.saved_address,container,false);
        intializeViews(v);
        bindListner();
        return v;
    }

    /* --------------------------------intialize all views that are used in this activity-----------------------------------------*/


    private void intializeViews(View v) {
        activity = getActivity();
        dialogs = new NewProgressBar(activity);
        user= new userOnlineInfo();
        preferenceManager = new PreferenceManager(activity);
        rvSavedAdress = (RecyclerView)v.findViewById(R.id.rv_saved_address);
        btnAddAddress = (Button)v.findViewById(R.id.btn_add_address) ;
        getDataFromLocalStorage();
        getAddressListApi();
    }

    /* -----------------------------------------bind all views that are used in this activity-----------------------------------*/

    private void bindListner() {
        btnAddAddress.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_address :
                openDialogOfAddAdress();
                return;
        }

    }

    /* ---------------------------------------------login data get from local storgae(for token get)---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
        userId = obj.getData().getUser().getUserId();


    }


    /*------------------------------------------------- add address dialog----------------------------------------------------*/

    public void openDialogOfAddAdress() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.add_address_dialog);
        dialog.setTitle("");
        final TextView Yes = (TextView) dialog.findViewById(R.id.yes);
        final TextView No = (TextView) dialog.findViewById(R.id.no);
        final EditText etAddress1 = (EditText) dialog.findViewById(R.id.et_address1);
        final EditText etAddress2 = (EditText) dialog.findViewById(R.id.et_address2);
        final EditText etCity = (EditText) dialog.findViewById(R.id.et_city);
        final EditText etState= (EditText) dialog.findViewById(R.id.et_state);
        final EditText etPinCode = (EditText) dialog.findViewById(R.id.et_post_code);
        final RadioButton rbHome = (RadioButton) dialog.findViewById(R.id.rb_home);
        final RadioButton rbWork = (RadioButton) dialog.findViewById(R.id.rb_work);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);
        rbHome.setChecked(true);
        rbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="0";
                rbHome.setChecked(true);
                rbWork.setChecked(false);
            }
        });
        rbWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="1";
                rbHome.setChecked(false);
                rbWork.setChecked(true);
            }
        });


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               addAddressValidateData(etAddress1,etAddress2,etCity,etState,etPinCode,addressType);
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


    /*----------------------------------------------- add address check fields empty or not-------------  ---------------------------*/

    private void addAddressValidateData(EditText etAddress1, EditText etAddress2, EditText etCity,
                                        EditText etState, EditText etPinCode, String addressType) {
        strAddress1 = etAddress1.getText().toString().trim();
        strAddress2 = etAddress2.getText().toString().trim();
        strCity = etCity.getText().toString().trim();
        strState = etState.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        if(strAddress1.isEmpty() || strAddress2.isEmpty() || strCity.isEmpty() || strState.isEmpty() || strPinCode.isEmpty()){
            etAddress1.setError("Please Enter Address 1");
            etAddress2.setError("Please Enter Address 2");
            etCity.setError("Please Enter City");
            etState.setError("Please Enter State");
            etPinCode.setError("Please Enter PinCode");
            //etAddressType.setError("Please Enter Address Type");
        }else {

            addDataIntoApi(strAddress1,strAddress2,strCity,strState,strPinCode,addressType);

        }
    }

    /*---------------------------------------------------- add address api-----------------------------------------------------*/

    private void addDataIntoApi(String strAddress1, String strAddress2, String strCity,
                                String strState, String strPinCode, String addressType) {

        if (user.isOnline(activity)){
            dialogs.show();
            apiUrl();
            ApiCaller.addAddress(activity, strendPointAddAdres, userId, strAddress1, strAddress2, strCity, strState, strPinCode, addressType, token,
                    new FutureCallback<AddAddressResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, AddAddressResponseModel result) {
                         if(e!=null){
                             return;
                         }
                         getData(result);
                        }
                    });


        }else {
            commonDialog commonDialog =new commonDialog();
            commonDialog.dialogbox(activity);
        }


    }


    /* ----------------------------------------------response of add api-----------------------------------------------------------*/

    private void getData(AddAddressResponseModel result) {
    if(result.getStatus() ==1){
        Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        getAddressListApi();
        dialogs.dismiss();
        dialog.dismiss();
    }
    else {
        Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        dialogs.dismiss();
        dialog.dismiss();
    }
    }


    /* -----------------------------------------------------------api url-------------------------------------------------------*/

    private void apiUrl(){
        strendPointAddAdres = Config.Url.addCustomerAddress;
        endPointGetAddress = Config.Url.getCustomerAddress;
    }


    /*--------------------------------------------------- Api for get Address list----------------------------------------------*/

    private void getAddressListApi(){
        if(user.isOnline(activity)){
            apiUrl();
            ApiCaller.getCustomerAddress(activity, endPointGetAddress, token, new FutureCallback<GetAddressResponseModel>() {
                @Override
                public void onCompleted(Exception e, GetAddressResponseModel result) {
                    if(e!=null){
                        return;
                    }
                    getAllAddressData(result);

                }
            });
        }else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }
    }


    /* ----------------------------------------------get All address data from response--------------------------------------------*/

    private void getAllAddressData(GetAddressResponseModel result) {
       if(result.getStatus() == 1){

           rvSavedAdress.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
           adapter = new SavedAdressRvAdapter(activity,result.getData());
           rvSavedAdress.setAdapter(adapter);
       }
       else {
           Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();

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
