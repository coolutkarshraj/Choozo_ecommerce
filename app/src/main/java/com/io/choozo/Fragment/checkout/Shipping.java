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
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.commonDialog;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class Shipping extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener {

    TextView firstTab,secondTab,freedeliverytxt,freedeliverySecondtext,freedeliveryThirdbtntext;
    String strFirstName,strLastName,strEmailId,strMobileNumber,strAddress,strPinCode,strCountry,strState="",strCity="";
    EditText etFirstName,etLastName,etMobileNumber,etAddress,etPinCode,etState,etCity;
    LinearLayout deliverFirstButton,deliverSecondButton,deliveryThirdButton,NewAdressLayout;
    RelativeLayout paymentbtn;
    ImageView rupeeIconsecond,rupeeIconThird;
    Activity activity;
    String token,endPointProfile;
    Spinner spinCountry;
    ViewPager viewPager;
    PreferenceManager preferenceManager;
    String[] item_country = {"-Nothing Selected-","Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Anterctica","Antigua and Barbuda","Argentina",
                               "Armenia","Arbua","Australia","Austria","Azerbaijan","Bahamas", "Bahrain", "Bangladesh","Barbados","Belarus","Belgium","Belize","Benin",
                                 "Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil","British Indian Ocean Territory",
                                   "Burnei Darussalam","Bulgaria","Burkina Faso","Buruandi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands",
                                    "Center African Republic","Chad","Chile","China","Christmas Island","Cacos(Keeling) Island","Colombia","Comoros","Congo","Cook Island",
                                     "Costa Rica","Cote D'Ivoire","Crotia","Cuba","Cyprus","Czech Republic","colom","Denmark","Djibouti","Dominica","Dominica Republic","East Timor","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea",
                                      "Estonia","Ethiopia","Falkland Island(Malvinas)","Faroe Island","Fiji","Finland","France, Metropolitan","French Guiana","French Polynesia","French Southern Territories","Gabon","Gambia","Georgia","Germany",
                                        "Ghana","Gibraltar","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Heard and Mc Donald Island","Honduras","Hong kong","Hungary","Iceland","India","Indonesia",
                                            "Iran (Islamic Republic of)","Iraq","Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","North Korea","South Korea","Kuwait","Kyrgyzstan","Lao People's Democratic Republic","Latvia","Lebanon","Lesotho","Liberia","Libyan Arab Jamahiriya",
                                              "Liechtenstein","Lithuania","Luxembourg","Macau","FYROM","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania","Maritius","Mayotte","Mexico","Micronesia,Federated States of","Moldova, Republic of","Monaco","Mangolia","Montserrat",
                                                "Morocco","Mozambique","Myanmar","Namibia","Naru","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Norfolk Island","Northern Mariana Island","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay",
                                                  "Peru","Philippines","Pitcairn","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia Federation","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Vincent and the Grenadies","Samoa","San Marino","Soa Tome and Prinicple","Saudi Arabia","Senegal","Seychelles","Sierra Leone",
                                                    "Singapore","Slovak Republic","Solvenia","Solomon Island","Somalia","South Africa","South Georgia & South Sandwich Island","Spain","Sri Lanka","St. Helena","St.Pierre and Miquelon","Sudan","Suriname","Svalbard and Jan Mayen Island","Swaziland","Sweden","Switzerland","Syrian Arab Republic","Taiwan","Tajikistan","Tanzania,United Republic of",
                                                      "Thailand","Togo","Tokelau","Tango","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and Caicos Island","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","United States Minor Outlying Island","Urugay","Uzbekistan","Vanuatu","Vatican City State","Venezuela","Viet Nam","Virgin Island(British)","Virgin Island(U.S.)","Wallis and Futuna Island",
                                                        "Western Sahara","Yemen","Democratic Republic of Cango","Zambia","Zimbabwe","Montengro","Serbia","Aland Islands","Bonaire, Sint Eustatius and Saba","Curacao","Palestinian Territory, Occupied","South Sudan","St.Barthelemy","St.Martin(French part)","Canary Islands","Ascension Island(British)","Kosovo, Republic of","Isle of Man","Tristan da Cunha","Guerynsey","Jersey"
                                        };

    String [] code ={"0","1","2","3","4 5","6","7","8","9","10","11",
            "12","13","14","15","16","17", "18", "19","20","21","22","23","24",
            "25","26","27","28","29","30","31","32",
            "33","34","35","36","37","38","39","40 41","42",
            "43","44","45","46","47","48","49","50","51","52",
            "53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68",
            "69","70","71","72","73","74","75, 76","77","78 79","80","81","82","83","84",
            "85","86","87","88","89","90","91","92","93","Guinea-Bissau","Guyana","Haiti","Heard and Mc Donald Island","Honduras","Hong kong","Hungary","Iceland","India","Indonesia",
            "Iran (Islamic Republic of)","Iraq","Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","North Korea","South Korea","Kuwait","Kyrgyzstan","Lao People's Democratic Republic","Latvia","Lebanon","Lesotho","Liberia","Libyan Arab Jamahiriya",
            "Liechtenstein","Lithuania","Luxembourg","Macau","FYROM","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania","Maritius","Mayotte","Mexico","Micronesia,Federated States of","Moldova, Republic of","Monaco","Mangolia","Montserrat",
            "Morocco","Mozambique","Myanmar","Namibia","Naru","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Norfolk Island","Northern Mariana Island","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay",
            "Peru","Philippines","Pitcairn","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia Federation","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Vincent and the Grenadies","Samoa","San Marino","Soa Tome and Prinicple","Saudi Arabia","Senegal","Seychelles","Sierra Leone",
            "Singapore","Slovak Republic","Solvenia","Solomon Island","Somalia","South Africa","South Georgia & South Sandwich Island","Spain","Sri Lanka","St. Helena","St.Pierre and Miquelon","Sudan","Suriname","Svalbard and Jan Mayen Island","Swaziland","Sweden","Switzerland","Syrian Arab Republic","Taiwan","Tajikistan","Tanzania,United Republic of",
            "Thailand","Togo","Tokelau","Tango","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and Caicos Island","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","United States Minor Outlying Island","Urugay","Uzbekistan","Vanuatu","Vatican City State","Venezuela","Viet Nam","Virgin Island(British)","Virgin Island(U.S.)","Wallis and Futuna Island",
            "Western Sahara","Yemen","Democratic Republic of Cango","Zambia","Zimbabwe","Montengro","Serbia","Aland Islands","Bonaire, Sint Eustatius and Saba","Curacao","Palestinian Territory, Occupied","South Sudan","St.Barthelemy","St.Martin(French part)","Canary Islands","Ascension Island(British)","Kosovo, Republic of","Isle of Man","Tristan da Cunha","Guerynsey","Jersey"
    };

    RecyclerView rvSavedAdress;
    SavedAdressRvAdapter adapter;
    float dileveryFee = (float) 0.0;
    List<SavedAdressDataModel> item =new ArrayList<>();

    public Shipping(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping,container,false);
        intilializeViews(view);
        bindListner();
        getDataFromLocalStorage();
        startWorking();
        return  view;
    }

    /* ----------------------------intilaizeall Views that are used in this activity------------------------------------------*/

    private void intilializeViews(View view) {
        activity = getActivity();
        viewPager = Config.viewPager;
        preferenceManager = new PreferenceManager(activity);
        firstTab = (TextView) view.findViewById(R.id.first);
        secondTab = (TextView)view.findViewById(R.id.second);
        deliverFirstButton = (LinearLayout)view.findViewById(R.id.ll_first);
        deliverSecondButton = (LinearLayout)view.findViewById(R.id.ll_second);
        deliveryThirdButton = (LinearLayout)view.findViewById(R.id.ll_third);
        freedeliverytxt = (TextView)view.findViewById(R.id.tv_first);
        freedeliverySecondtext = (TextView)view.findViewById(R.id.tv_second);
        freedeliveryThirdbtntext = (TextView)view.findViewById(R.id.tv_third);
        rupeeIconsecond =(ImageView)view.findViewById(R.id.rupeeicon);
        rupeeIconThird =(ImageView)view.findViewById(R.id.rupeeicon1);
        spinCountry =(Spinner)view.findViewById(R.id.spinner_country);
        NewAdressLayout = (LinearLayout)view.findViewById(R.id.ll_newaddress);
        paymentbtn = (RelativeLayout)view.findViewById(R.id.rl_paymnet);
        rvSavedAdress = (RecyclerView)view.findViewById(R.id.rv_saved_address);
        etFirstName = (EditText)view.findViewById(R.id.et_firstName);
        etLastName = (EditText)view.findViewById(R.id.et_lastName);
        etAddress  = (EditText)view.findViewById(R.id.et_address);
        etMobileNumber  = (EditText)view.findViewById(R.id.et_phone);
        etPinCode  = (EditText)view.findViewById(R.id.zipcode);
        etCity = (EditText)view.findViewById(R.id.et_city1);
        etState = (EditText)view.findViewById(R.id.et_state);

    }

    /* --------------------------------------------------clickable views---------------------------------------------------*/

    private void bindListner(){
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
        switch (v.getId()){
            case R.id.first :
                firstTab.setBackgroundResource(R.drawable.button_shape_red_left);
                firstTab.setTextColor(ContextCompat.getColor(activity,R.color.white));
                secondTab.setBackgroundResource(R.drawable.red_outline_rectangle_right);
                secondTab.setTextColor(ContextCompat.getColor(activity,R.color.grey));
                NewAdressLayout.setVisibility(View.VISIBLE);
                paymentbtn.setVisibility(View.VISIBLE);
                rvSavedAdress.setVisibility(View.GONE);

                return;
            case R.id.second :
                secondTab.setBackgroundResource(R.drawable.button_shape_red_right);
                secondTab.setTextColor(ContextCompat.getColor(activity,R.color.white));
                firstTab.setBackgroundResource(R.drawable.red_outline_rectangle_left);
                firstTab.setTextColor(ContextCompat.getColor(activity,R.color.grey));
                NewAdressLayout.setVisibility(View.GONE);
                paymentbtn.setVisibility(View.GONE);
                rvSavedAdress.setVisibility(View.VISIBLE);
               // dataSetToRecyclerView();

                return;

            case R.id.ll_first :

                deliverFirstButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity,R.color.red));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity,R.color.black));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity,R.color.black));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                dileveryFee = (float) 0.0;
                return;

            case R.id.ll_second :
                deliverFirstButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity,R.color.black));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity,R.color.red));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity,R.color.black));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                dileveryFee = (float) 149.0;
                return;

            case R.id.ll_third :
                deliverFirstButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliverSecondButton.setBackgroundResource(R.drawable.grey_outline_rectangle);
                deliveryThirdButton.setBackgroundResource(R.drawable.red_outline_rectangle);
                freedeliverytxt.setTextColor(ContextCompat.getColor(activity,R.color.black));
                freedeliverySecondtext.setTextColor(ContextCompat.getColor(activity,R.color.black));
                freedeliveryThirdbtntext.setTextColor(ContextCompat.getColor(activity,R.color.red));
                rupeeIconsecond.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                rupeeIconThird.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                dileveryFee = (float) 249.0;
                return;

            case R.id.rl_paymnet :
                ShippingDetail();
                return;
        }

    }


    /* ------------------------------------------------------ working start ------------------------------------------------------------*/


    public  void startWorking(){
        spinnerAdapterSet();
        getProfileDataApi();

    }

    /*----------------------------------------------- City adapter Set  to Spinner---------------------------------------------------*/

    private void spinnerAdapterSet() {
        ArrayAdapter aa = new ArrayAdapter(activity,android.R.layout.simple_spinner_item,item_country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinCountry.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        strCountry  = item_country[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*---------------------------------------------------------- api url ------------------------------------------------------------*/
    private void apiUrl(){
        endPointProfile = Config.Url.getUserData;
    }

    /*------------------------------------------------------- get profile data from  api---------------------------------------------*/

    public  void getProfileDataApi() {

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

        }

     /* ---------------------------------------------- Api data pass into string-------------------------------------------------------*/

    private void getProfileData(GetProfileResponseModel result) {
        if(result.getStatus() == 1){
            strFirstName = result.getData().getFirstName();
            strLastName = result.getData().getLastName();
            strEmailId = result.getData().getEmail();
            strAddress = result.getData().getAddress();
            strMobileNumber = result.getData().getMobileNumber();
            strPinCode = result.getData().getPincode();
            datasetintoEditText();
        }else {
            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
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
        if(strCity.equals("") || strState.equals("") || strCountry.equals("-Nothing Selected-") ){
            Toast.makeText(activity, "please enter fill data", Toast.LENGTH_SHORT).show();
        }
        else {
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
           Config.shipdeleveryCharge =dileveryFee;
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
