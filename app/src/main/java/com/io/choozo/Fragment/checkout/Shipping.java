package com.io.choozo.Fragment.checkout;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.io.choozo.model.responseModel.CountryListResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.commonDialog;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shipping extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView firstTab, secondTab, freedeliverytxt, freedeliverySecondtext, freedeliveryThirdbtntext;
    String strFirstName, strLastName, strEmailId, strMobileNumber, strAddress, strPinCode, strCountry, strState = "", strCity = "",strad="";
    EditText etFirstName, etLastName, etMobileNumber, etAddress, etPinCode, etState, etCity;
    LinearLayout deliverFirstButton, deliverSecondButton, deliveryThirdButton, NewAdressLayout;
    RelativeLayout paymentbtn;
    ImageView rupeeIconsecond, rupeeIconThird;
    Activity activity;
    String token, endPointProfile, endPointCountryList;
    Spinner spinCountry;
    ViewPager viewPager;
    ArrayList<String> worldlist = new ArrayList<>();
    ArrayList<CountryListDataModel> world = new ArrayList<>();
    PreferenceManager preferenceManager;
    RecyclerView rvSavedAdress;
    SavedAdressRvAdapter adapter;
    float dileveryFee = (float) 0.0;
    List<SavedAdressDataModel> item = new ArrayList<>();

    public Shipping() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        intilializeViews(view);
        bindListner();
        getDataFromLocalStorage();
        startWorking();
        return view;
    }

    /* ----------------------------------- intilaize all Views that are used in this activity ------------------------------------------*/

    private void intilializeViews(View view) {
        activity = getActivity();
        viewPager = Config.viewPager;
        preferenceManager = new PreferenceManager(activity);
        firstTab = (TextView) view.findViewById(R.id.first);
        secondTab = (TextView) view.findViewById(R.id.second);
        deliverFirstButton = (LinearLayout) view.findViewById(R.id.ll_first);
        deliverSecondButton = (LinearLayout) view.findViewById(R.id.ll_second);
        deliveryThirdButton = (LinearLayout) view.findViewById(R.id.ll_third);
        freedeliverytxt = (TextView) view.findViewById(R.id.tv_first);
        freedeliverySecondtext = (TextView) view.findViewById(R.id.tv_second);
        freedeliveryThirdbtntext = (TextView) view.findViewById(R.id.tv_third);
        rupeeIconsecond = (ImageView) view.findViewById(R.id.rupeeicon);
        rupeeIconThird = (ImageView) view.findViewById(R.id.rupeeicon1);
        spinCountry = (Spinner) view.findViewById(R.id.spinner_country);
        NewAdressLayout = (LinearLayout) view.findViewById(R.id.ll_newaddress);
        paymentbtn = (RelativeLayout) view.findViewById(R.id.rl_paymnet);
        rvSavedAdress = (RecyclerView) view.findViewById(R.id.rv_saved_address);
        etFirstName = (EditText) view.findViewById(R.id.et_firstName);
        etLastName = (EditText) view.findViewById(R.id.et_lastName);
        etAddress = (EditText) view.findViewById(R.id.et_address);
        etMobileNumber = (EditText) view.findViewById(R.id.et_phone);
        etPinCode = (EditText) view.findViewById(R.id.zipcode);
        etCity = (EditText) view.findViewById(R.id.et_city1);
        etState = (EditText) view.findViewById(R.id.et_state);

    }

    /* --------------------------------------------------clickable views---------------------------------------------------*/

    private void bindListner() {
        firstTab.setOnClickListener(this);
        secondTab.setOnClickListener(this);
        deliverFirstButton.setOnClickListener(this);
        deliverSecondButton.setOnClickListener(this);
        deliveryThirdButton.setOnClickListener(this);
        paymentbtn.setOnClickListener(this);
        spinCountry.setOnItemSelectedListener(this);
    }

    /* ---------------------------------------------login data get from local storgae---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first:
                firstTab.setBackgroundResource(R.drawable.button_shape_red_left);
                firstTab.setTextColor(ContextCompat.getColor(activity, R.color.white));
                secondTab.setBackgroundResource(R.drawable.red_outline_rectangle_right);
                secondTab.setTextColor(ContextCompat.getColor(activity, R.color.grey));
                NewAdressLayout.setVisibility(View.VISIBLE);
                paymentbtn.setVisibility(View.VISIBLE);
                rvSavedAdress.setVisibility(View.GONE);

                return;
            case R.id.second:
                secondTab.setBackgroundResource(R.drawable.button_shape_red_right);
                secondTab.setTextColor(ContextCompat.getColor(activity, R.color.white));
                firstTab.setBackgroundResource(R.drawable.red_outline_rectangle_left);
                firstTab.setTextColor(ContextCompat.getColor(activity, R.color.grey));
                NewAdressLayout.setVisibility(View.GONE);
                paymentbtn.setVisibility(View.GONE);
                rvSavedAdress.setVisibility(View.VISIBLE);
                // dataSetToRecyclerView();

                return;

            case R.id.ll_first:

                deliverFirstButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity, R.color.red));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity, R.color.black));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity, R.color.black));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                dileveryFee = (float) 0.0;
                return;

            case R.id.ll_second:
                deliverFirstButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity, R.color.black));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity, R.color.red));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity, R.color.black));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                dileveryFee = (float) 149.0;
                return;

            case R.id.ll_third:
                deliverFirstButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity, R.color.black));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity, R.color.black));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity, R.color.red));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                dileveryFee = (float) 249.0;
                return;

            case R.id.rl_paymnet:
                ShippingDetail();
                return;
        }

    }


    /* ------------------------------------------------------ working start ------------------------------------------------------------*/


    public void startWorking() {
        getSpinnerDataFromApi();
        getProfileDataApi();

    }

    /* ------------------------------------------------- country list api call ---------------------------------------------------------*/

    private void getSpinnerDataFromApi() {
        apiUrl();
        ApiCaller.getcountryList(activity, endPointCountryList, new FutureCallback<CountryListResponseModel>() {
            @Override
            public void onCompleted(Exception e, CountryListResponseModel result) {
                if (e != null) {
                    return;
                } else {
                    datasetToSpinner(result);
                }
            }
        });
    }

    /* ------------------------------------------------ country list data set into views ---------------------------------------------*/

    private void datasetToSpinner(CountryListResponseModel result) {
        if (result.getStatus() == 1) {
            for (int i = 0; i < result.getData().size(); i++) {
                CountryListDataModel countryListDataModel = new CountryListDataModel();
                countryListDataModel.setCountryId(result.getData().get(i).getCountryId());
                countryListDataModel.setName(result.getData().get(i).getName());
                world.add(countryListDataModel);
                worldlist.add(countryListDataModel.getName());
                ArrayAdapter aa = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, worldlist);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinCountry.setAdapter(aa);
            }

        } else {
            Toast.makeText(activity, "countrylist not get", Toast.LENGTH_SHORT).show();
        }
    }


    /* ------------------------------------------------- Spinner Clickable ----------------------------------------------------------*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        strCountry = String.valueOf(world.get(position).getCountryId());
        strad = world.get(position).getName();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*---------------------------------------------------------- api url ------------------------------------------------------------*/
    private void apiUrl() {
        endPointProfile = Config.Url.getUserData;
        endPointCountryList = Config.Url.countryList;
    }

    /*------------------------------------------------------- get profile data from  api---------------------------------------------*/

    public void getProfileDataApi() {

        apiUrl();
        ApiCaller.getUserProfile(activity, endPointProfile, token, new FutureCallback<GetProfileResponseModel>() {
            @Override
            public void onCompleted(Exception e, GetProfileResponseModel result) {
                if (e != null) {
                    return;
                }
                getProfileData(result);
            }
        });

    }

    /* ---------------------------------------------- Api data pass into string-------------------------------------------------------*/

    private void getProfileData(GetProfileResponseModel result) {
        if (result.getStatus() == 1) {
            strFirstName = result.getData().getFirstName();
            strLastName = result.getData().getLastName();
            strEmailId = result.getData().getEmail();
            strAddress = result.getData().getAddress();
            strMobileNumber = result.getData().getMobileNumber();
            strPinCode = result.getData().getPincode();
            datasetintoEditText();
        } else {
            Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*----------------------------------------------- Data set into edit text --------------------------------------------------------*/

    private void datasetintoEditText() {
        etFirstName.setText(strFirstName);
        etLastName.setText(strLastName);
        etMobileNumber.setText(strMobileNumber);
        etAddress.setText(strAddress);
        etPinCode.setText(strPinCode);
    }

    /* ------------------------------------------------- Shipping Detail ------------------------------------------------------------*/

    private void ShippingDetail() {
        strFirstName = etFirstName.getText().toString().trim();
        strLastName = etLastName.getText().toString().trim();
        strMobileNumber = etMobileNumber.getText().toString().trim();
        strAddress = etAddress.getText().toString().trim();
        strCity = etCity.getText().toString().trim();
        strState = etState.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        if (strCity.equals("") || strState.equals("") || strCountry.equals("-Nothing Selected-")) {
            Toast.makeText(activity, "please enter fill data", Toast.LENGTH_SHORT).show();
        } else {
            viewPager.setCurrentItem(1);
            Config.shipFirstName = strFirstName;
            Config.shipLastName = strLastName;
            Config.shipEmail = strEmailId;
            Config.shipphone = strMobileNumber;
            Config.shipAddress = strAddress;
            Config.shipCountry = strCountry;
            Config.shipState = strState;
            Config.shipCity = strCity;
            Config.shipPinCode = strPinCode;
            Config.shipdeleveryCharge = dileveryFee;
            Config.C_Name = strad;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
