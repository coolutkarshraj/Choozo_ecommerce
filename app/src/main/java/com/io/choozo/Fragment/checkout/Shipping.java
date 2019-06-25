package com.io.choozo.Fragment.checkout;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.SavedAdressRvAdapter;
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;

import java.util.ArrayList;
import java.util.List;

public class Shipping extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener {

    TextView firstTab,secondTab,freedeliverytxt,freedeliverySecondtext,freedeliveryThirdbtntext;
    LinearLayout deliverFirstButton,deliverSecondButton,deliveryThirdButton,NewAdressLayout;
    RelativeLayout paymentbtn;
    ImageView rupeeIconsecond,rupeeIconThird;
    Activity activity;
    Spinner City,State,Country;
    String[] item_country = {"Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Anterctica","Antigua and Barbuda","Argentina",
                               "Armenia","Arbua","Australia","Austria","Azerbaijan","Bahamas", "Bahrain", "Bangladesh","Barbados","Belarus","Belgium","Belize","Benin",
                                "Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil","British Indian Ocean Territory",
                                "Burnei Darussalam","Bulgaria","Burkina Faso","Buruandi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands",
                                  "Center African Republic","Chad","Chile","China","Christmas Island","Cacos(Keeling Island","colom", "Djibouti","Eritrea","Germany","Haiti","India"};
    String[] item_state = {"Andhra Pradesh","Arunachal Pradesh", "Bihar", "Goa", "Haryana", "Karnataka","Manipur","Punjab","Telangana","Uttar Pradesh","West Bengal"};
    String[] item_city = {"Ludhiana","Amritsar", "Jalandhar", "Patiala", "Hoshiarpur", "Mohali","Batala","Khanna","Barnala","Rajpura","Pathankot"};
    RecyclerView rvSavedAdress;
    SavedAdressRvAdapter adapter;
    List<SavedAdressDataModel> item =new ArrayList<>();

    public Shipping(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping,container,false);
        intilializeViews(view);
        bindListner();
        startWorking();
        return  view;
    }

    /* ----------------------------intilaizeall Views that are used in this activity------------------------------------------*/

    private void intilializeViews(View view) {
        activity = getActivity();
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
        State =(Spinner)view.findViewById(R.id.spinner_state);
        City =(Spinner)view.findViewById(R.id.spinner_city);
        Country =(Spinner)view.findViewById(R.id.spinner_country);
        NewAdressLayout = (LinearLayout)view.findViewById(R.id.ll_newaddress);
        paymentbtn = (RelativeLayout)view.findViewById(R.id.rl_paymnet);
        rvSavedAdress = (RecyclerView)view.findViewById(R.id.rv_saved_address);

    }

    /* --------------------------------------------------clickable views---------------------------------------------------*/

    private void bindListner(){
        firstTab.setOnClickListener(this);
        secondTab.setOnClickListener(this);
        deliverFirstButton.setOnClickListener(this);
        deliverSecondButton.setOnClickListener(this);
        deliveryThirdButton.setOnClickListener(this);
        paymentbtn.setOnClickListener(this);
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
                return;

            case R.id.rl_paymnet :
                Config.viewPager.setCurrentItem(1);
                return;
        }

    }
    /* -------------------------------------------------working start-------------------------------------------------------*/


    public  void startWorking(){
        spinnerAdapterSet();
        stateSpinner();
        citySpinner();

    }

    /*----------------------------------------------- City adapter Set  to Spinner-------------------------------------------------*/

    private void spinnerAdapterSet() {
        ArrayAdapter aa = new ArrayAdapter(activity,android.R.layout.simple_spinner_item,item_country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        Country.setAdapter(aa);
    }

    /*----------------------------------------------- state adapter Set  to Spinner-------------------------------------------------*/

    private void stateSpinner() {
        ArrayAdapter aa = new ArrayAdapter(activity,android.R.layout.simple_spinner_item,item_state);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        State.setAdapter(aa);
    }
    /*----------------------------------------------- City adapter Set  to Spinner-------------------------------------------------*/

    private void citySpinner() {
        ArrayAdapter aa = new ArrayAdapter(activity,android.R.layout.simple_spinner_item,item_city);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        City.setAdapter(aa);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*----------------------------------------------- Saved Adress data---------------------------------------------------*/

  /*  private void dataSetToRecyclerView() {
        rvSavedAdress.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        item.clear();
        item.add(new SavedAdressDataModel("John smith","701, Block - B, Siddhi Vinayak Tower,Ahmedabad-380051, Gujarat, INDIA +91 98765 43210"));
        item.add(new SavedAdressDataModel("Vernon Martin","925 Buddy Motorway, New Street, USA 380152 +91 43210 98765 "));
        item.add(new SavedAdressDataModel("Ian Grant","4855 Durgan Wall, Perfect Arcade, USA 380152 +91 98765 43210"));
        adapter = new SavedAdressRvAdapter(activity,item);
        rvSavedAdress.setAdapter(adapter);
    }*/


}
