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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.SavedAdressRvAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.CountryListDataModel;
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;
import com.io.choozo.model.responseModel.AddAddressResponseModel;
import com.io.choozo.model.responseModel.CountryListResponseModel;
import com.io.choozo.model.responseModel.DeliveryAddressesItem;
import com.io.choozo.model.responseModel.GetAddressResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.address.AdressResponseModel;
import com.io.choozo.model.responseModel.address.EditAddressResponseModel;
import com.io.choozo.model.responseModel.district.GetDistrictDataModel;
import com.io.choozo.model.responseModel.district.GetDistrictResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.OnClick;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress  extends Fragment implements View.OnClickListener, OnClick {

    public static RecyclerView rvSavedAdress;
    String addressType ="Home";
    public static SavedAdressRvAdapter adapter;
    List<SavedAdressDataModel> item =new ArrayList<>();
    Activity activity;
    Button btnAddAddress;
    Dialog dialog,dialog2;
    NewProgressBar dialogs;
    userOnlineInfo user;
    String strName;
    String strAddress;
    String strMobile;
    String strState;
    String strPinCode;
    String token;
    String strendPointAddAdres,endPointGetAddress,editAddressEndPoint;
    int userId;
    OnClick onClick;
    private PreferenceManager preferenceManager;
    private List<GetDistrictDataModel> listdata = new ArrayList<>();
    List<String> listcity = new ArrayList<>();
    List<String> listDistict = new ArrayList<>();
    String spindata, spindistict;
    int defalutAddress =0;
    List<DeliveryAddressesItem> deliveryAddressesItem;


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
        onClick = this;
        dialogs = new NewProgressBar(activity);
        user= new userOnlineInfo();
        deliveryAddressesItem = new ArrayList<>();
        preferenceManager = new PreferenceManager(activity);
        rvSavedAdress = (RecyclerView)v.findViewById(R.id.rv_saved_address);
        btnAddAddress = (Button)v.findViewById(R.id.btn_add_address) ;
        getDataFromLocalStorage();
        getAddressListApi();
        getAllCities();
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
        Log.e("token",token);

    }

    /*------------------------------------------------- add address dialog----------------------------------------------------*/

    public void openDialogOfAddAdress() {
        dialog = new Dialog(activity,R.style.dialogTheme);
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
        final EditText Name = (EditText) dialog.findViewById(R.id.et_name);
        final EditText address = (EditText) dialog.findViewById(R.id.et_address);
        final Spinner et_city_spin = (Spinner) dialog.findViewById(R.id.spin_city);
        final Spinner et_area_spin = (Spinner) dialog.findViewById(R.id.spin_area);
        final EditText et_mobile = (EditText) dialog.findViewById(R.id.et_mobile);
        final EditText etPinCode = (EditText) dialog.findViewById(R.id.et_post_code);
        final RadioButton rbHome = (RadioButton) dialog.findViewById(R.id.rb_home);
        final RadioButton rbWork = (RadioButton) dialog.findViewById(R.id.rb_work);
        final RadioButton rb_isDefault = (RadioButton) dialog.findViewById(R.id.rb_isDefault);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);
        rbHome.setChecked(true);
        rbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="Home";
                rbHome.setChecked(true);
                rbWork.setChecked(false);
            }
        });
        rb_isDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check which radiobutton was pressed
                if (!rb_isDefault.isSelected()) {
                    rb_isDefault.setChecked(true);
                    rb_isDefault.setSelected(true);
                    defalutAddress =1;

                } else {
                    rb_isDefault.setChecked(false);
                    rb_isDefault.setSelected(false);
                    defalutAddress =0;
                }
            }
        });
        rbWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="Work";
                rbHome.setChecked(false);
                rbWork.setChecked(true);
            }
        });
        ArrayAdapter aa2 = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listDistict);
        et_city_spin.setAdapter(aa2);

        et_area_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (listcity.isEmpty() || listcity == null) {
                    Toast.makeText(activity, "please select District", Toast.LENGTH_SHORT).show();
                } else {
                    spindata = listcity.get(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_city_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spindistict = listDistict.get(i);
                listcity.clear();
                for (int j = 0; j < listdata.get(i).getCities().size(); j++) {

                    listcity.add(listdata.get(i).getCities().get(j).getName());
                }

                ArrayAdapter aa = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listcity);
                et_area_spin.setAdapter(aa);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              addAddressValidateData(Name,address,spindistict,spindata,etPinCode,et_mobile,addressType);
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

    private void addAddressValidateData(EditText et_name, EditText et_address, String  city,
                                        String area,EditText mobile, EditText etPinCode, String addressType) {
        strName = et_name.getText().toString().trim();
        strAddress = et_address.getText().toString().trim();
        strMobile = mobile.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        if(strName.isEmpty() || strAddress.isEmpty() || city.isEmpty() || area.isEmpty() ||strMobile.isEmpty()||  strPinCode.isEmpty()){
            et_name.setError("Please Enter Name");
            et_address.setError("Please Enter Address ");
            mobile.setError("Please Enter mobile number");
            etPinCode.setError("Please Enter PinCode");

            //etAddressType.setError("Please Enter Address Type");
        }else {

            addDataIntoApi(strName,strAddress,city,area,strPinCode, strMobile,addressType);

        }
    }

    /*---------------------------------------------------- add address api-----------------------------------------------------*/

    private void addDataIntoApi(String strName, String strAddress, String strCity,
                                String area, String strPinCode,String strMobile, String addressType) {

        if (user.isOnline(activity)){
            dialogs.show();
            apiUrl();
            ApiCaller.addAddress(activity, strendPointAddAdres, strName,
                    strAddress, strCity, area, strPinCode, strMobile, addressType,defalutAddress,  token,
                    new FutureCallback<AdressResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, AdressResponseModel result) {
                         if(e!=null){
                             Log.e("AddressAdd",""+e);
                             dialogs.dismiss();
                             ApiCaller.showAlertDialog(activity,Config.somethingWrong);
                             return;
                         }
                         if(result!=null){
                             dialogs.dismiss();
                             getData(result);
                         }else{
                             dialogs.dismiss();
                             ApiCaller.showAlertDialog(activity,Config.somethingWrong);
                         }

                        }
                    });


        }else {
            commonDialog commonDialog =new commonDialog();
            commonDialog.dialogbox(activity);
        }


    }


    /* ----------------------------------------------response of add api-----------------------------------------------------------*/

    private void getData(AdressResponseModel result) {
    if(result.isStatus()){
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
        endPointGetAddress = Config.Url.getUserData+userId;
        editAddressEndPoint = Config.Url.updateAddress;
    }


    /*--------------------------------------------------- Api for get Address list----------------------------------------------*/

    private void getAddressListApi(){
        if(user.isOnline(activity)){
            apiUrl();
            ApiCaller.getUserProfile(activity, endPointGetAddress, token, new FutureCallback<GetProfileResponseModel>() {
                @Override
                public void onCompleted(Exception e, GetProfileResponseModel result) {
                    if(e!=null){
                        ApiCaller.showAlertDialog(activity,Config.somethingWrong);
                        return;
                    }if(result!=null){
                        getAllAddressData(result.getData().getDeliveryAddresses());
                    }else{
                        ApiCaller.showAlertDialog(activity,Config.somethingWrong);
                    }


                }
            });
        }else {
            commonDialog commonDialog = new commonDialog();
            commonDialog.dialogbox(activity);
        }
    }


    /* ----------------------------------------------get All address data from response--------------------------------------------*/

    private void getAllAddressData(List<DeliveryAddressesItem> deliveryAddresses) {
        deliveryAddressesItem = deliveryAddresses;
           rvSavedAdress.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
           adapter = new SavedAdressRvAdapter(activity,deliveryAddresses,onClick);
           rvSavedAdress.setAdapter(adapter);


    }

    private void getAllCities () {
        if (user.isOnline(activity)) {
            ApiCaller.getdistic(activity, Config.Url.disticGet,
                    new FutureCallback<GetDistrictResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, GetDistrictResponseModel result) {
                            if (e != null) {
                                dialog.dismiss();
                                ApiCaller.showAlertDialog(activity, "Something Went Wrong");
                                return;
                            }


                            if (result != null) {
                                if (result.isStatus()) {
                                    listData(result.getData());
                                }
                                }else{

                                ApiCaller.showAlertDialog(activity, "Something Went Wrong");
                            }

                        }
                    });

        } else {
            ApiCaller.showAlertDialog(activity, "No Internet Connection");
        }

    }

    private void listData (List <GetDistrictDataModel> data) {
        listdata = data;
        listDistict.clear();
        for (int i = 0; i < data.size(); i++) {

            listDistict.add(data.get(i).getName());

        }
        
        Log.e("city", "" + listcity.size());
    }


    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


    @Override
    public void catId(int postion) {
        editAddress(postion)    ;
    }


    public void editAddress(int postion) {

        dialog2 = new Dialog(activity,R.style.dialogTheme);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog2.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog2.setContentView(R.layout.add_address_dialog);
        dialog2.setTitle("");
        final TextView Yes = (TextView) dialog2.findViewById(R.id.yes);
        final TextView No = (TextView) dialog2.findViewById(R.id.no);
        final EditText Name = (EditText) dialog2.findViewById(R.id.et_name);
        final EditText address = (EditText) dialog2.findViewById(R.id.et_address);
        final Spinner et_city_spin = (Spinner) dialog2.findViewById(R.id.spin_city);
        final Spinner et_area_spin = (Spinner) dialog2.findViewById(R.id.spin_area);
        final EditText et_mobile = (EditText) dialog2.findViewById(R.id.et_mobile);
        final EditText etPinCode = (EditText) dialog2.findViewById(R.id.et_post_code);
        final RadioButton rbHome = (RadioButton) dialog2.findViewById(R.id.rb_home);
        final RadioButton rbWork = (RadioButton) dialog2.findViewById(R.id.rb_work);
        final RadioButton rb_isDefault = (RadioButton) dialog2.findViewById(R.id.rb_isDefault);
        final ImageView Clear = (ImageView) dialog2.findViewById(R.id.clear);

        Name.setText(deliveryAddressesItem.get(postion).getName());
        address.setText(deliveryAddressesItem.get(postion).getAddress());
        et_mobile.setText(deliveryAddressesItem.get(postion).getPhoneNum());
        etPinCode.setText(deliveryAddressesItem.get(postion).getZipcode());

        if(deliveryAddressesItem.get(postion).getAddressType()=="Home"){
            rbHome.setChecked(true);
            rbWork.setChecked(false);
        }else{
            rbHome.setChecked(false);
            rbWork.setChecked(true);
        }

        rbHome.setChecked(true);
        rbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="Home";
                rbHome.setChecked(true);
                rbWork.setChecked(false);
            }
        });

        if(deliveryAddressesItem.get(postion).getIsDefault() ==1){
            rb_isDefault.setChecked(true);
        }else{
            rb_isDefault.setChecked(false);
        }
        rb_isDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check which radiobutton was pressed
                if (!rb_isDefault.isSelected()) {
                    rb_isDefault.setChecked(true);
                    rb_isDefault.setSelected(true);
                    defalutAddress =1;

                } else {
                    rb_isDefault.setChecked(false);
                    rb_isDefault.setSelected(false);
                    defalutAddress =0;
                }
            }
        });
        rbWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="Work";
                rbHome.setChecked(false);
                rbWork.setChecked(true);
            }
        });
        ArrayAdapter aa2 = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listDistict);
        et_city_spin.setAdapter(aa2);

        et_area_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (listcity.isEmpty() || listcity == null) {
                    Toast.makeText(activity, "please select District", Toast.LENGTH_SHORT).show();
                } else {
                    spindata = listcity.get(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_city_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spindistict = listDistict.get(i);
                listcity.clear();
                for (int j = 0; j < listdata.get(i).getCities().size(); j++) {

                    listcity.add(listdata.get(i).getCities().get(j).getName());
                }

                ArrayAdapter aa = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listcity);
                et_area_spin.setAdapter(aa);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editDataValidate(deliveryAddressesItem.get(postion).getAddressId(),Name,address,spindistict,spindata,etPinCode,et_mobile,addressType);
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
        dialog2.show();

    }

    /*----------------------------------------------- add address check fields empty or not-------------  ---------------------------*/

    private void editDataValidate(int addressId, EditText et_name, EditText et_address, String city,
                                  String area, EditText mobile, EditText etPinCode, String addressType) {
        strName = et_name.getText().toString().trim();
        strAddress = et_address.getText().toString().trim();
        strMobile = mobile.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        if(strName.isEmpty() || strAddress.isEmpty() || city.isEmpty() || area.isEmpty() ||strMobile.isEmpty()||  strPinCode.isEmpty()){
            et_name.setError("Please Enter Name");
            et_address.setError("Please Enter Address ");
            mobile.setError("Please Enter mobile number");
            etPinCode.setError("Please Enter PinCode");

            //etAddressType.setError("Please Enter Address Type");
        }else {

            editDataIntoApi(addressId,strName,strAddress,city,area,strPinCode, strMobile,addressType);

        }
    }



    private void editDataIntoApi(int addressId, String strName, String strAddress, String strCity,
                                 String area, String strPinCode, String strMobile, String addressType) {

        if (user.isOnline(activity)){
            dialogs.show();
            apiUrl();
            ApiCaller.editAddress(activity, editAddressEndPoint,addressId, strName,
                    strAddress, strCity, area, strPinCode, strMobile, addressType,defalutAddress,  token,
                    new FutureCallback<EditAddressResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, EditAddressResponseModel result) {
                            if(e!=null){
                                dialogs.dismiss();
                                return;
                            }
                            if(result!=null){
                                dialogs.dismiss();
                                dialog2.dismiss();
                               if(result.isStatus()){
                                   Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                               }else{
                                   dialog2.dismiss();
                               }
                            }else {
                               ApiCaller.showAlertDialog(activity,Config.somethingWrong);
                            }
                        }
                    });


        }else {
            commonDialog commonDialog =new commonDialog();
            commonDialog.dialogbox(activity);
        }


    }
}
