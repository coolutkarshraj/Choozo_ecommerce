package com.io.choozo.Fragment.checkout;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.activity.ordersucessfull.OrderSucessfullyPlacedActivity;
import com.io.choozo.adapter.ConfirmationAdapter;
import com.io.choozo.adapter.ShopingBagAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.ProductDetail;
import com.io.choozo.model.ProductDetailData;
import com.io.choozo.model.dummydataModel.ConfirmationModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.PlaceOrderResponseModel;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class Confirmation extends Fragment implements View.OnClickListener {
    RelativeLayout orderNow;
    RecyclerView rv_Confirmation;
    ConfirmationAdapter adapter;
    String endPoint,token;
    TextView tvName, tvAddress, tvCity, tvOrderAmt, tvDelivery, tvTotalAmount;
    List<ConfirmationModel> list = new ArrayList<>();
    Activity activity;
    ProductDetail productDetail;
    PreferenceManager preferenceManager;

    ArrayList<ProductDetailData> list1;


    public Confirmation() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        intializeView(view);
        getDataFromLocalStorage();
        bindListner();
        startWorking();
        return view;
    }

    /* ------------------------------------------- intialize all Views that are used in this activity --------------------------------*/

    private void intializeView(View view) {
        activity = getActivity();
        preferenceManager = new PreferenceManager(activity);
        rv_Confirmation = (RecyclerView) view.findViewById(R.id.rv_items);
        orderNow = (RelativeLayout) view.findViewById(R.id.rl_ordernow);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvAddress = (TextView) view.findViewById(R.id.tv_address);
        tvCity = (TextView) view.findViewById(R.id.tv_city);
        tvOrderAmt = (TextView) view.findViewById(R.id.tv_order_amt);
        tvDelivery = (TextView) view.findViewById(R.id.tv_delivery);
        tvTotalAmount = (TextView) view.findViewById(R.id.tv_total_order_amt);
        productDetail = new ProductDetail();
        list1 = new ArrayList<>();

    }

    /*--------------------------------------------- bind All views that are used in this activity -----------------------------------*/

    private void bindListner() {
        orderNow.setOnClickListener(this);
    }

    /* --------------------------------------- login data get from local storgae(for token get) --------------------------------------- */

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_ordernow:

                apiPlaceOrder();
                /*Intent i =new Intent(activity, OrderSucessfullyPlacedActivity.class);
                startActivity(i);*/
        }

    }


    /*-------------------------------------------------------------- start working ------------------------------------------------*/

    private void startWorking() {

        shippingAddress();
        allConfirmationDatasetToRV();
        billingAmount();
    }


    /*---------------------------------------------------- Shipping Address work -------------------------------------------------*/

    private void shippingAddress() {
        tvName.setText(Config.shipFirstName + " " + Config.shipLastName);
        tvAddress.setText(Config.shipAddress);
        tvCity.setText(Config.shipCity + " -" + Config.shipPinCode + ", " + Config.shipState + ", " + Config.shipCountry + " " + Config.shipphone);
    }


    private void allConfirmationDatasetToRV() {
        rv_Confirmation.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list.clear();
        list.add(new ConfirmationModel(R.drawable.bluestriptop, "Blue Strip Top", "Size: S", "Color: Red", "3299", "4999", "3"));
        list.add(new ConfirmationModel(R.drawable.roundneckdree, "Round Neck Dress", "Size: S", "Color: Black", "1299", "2000", "1"));
        list.add(new ConfirmationModel(R.drawable.green, "Green Crop T- Shirt", "Size: M", "Color: Green", "900", "20000", "5"));
        list.add(new ConfirmationModel(R.drawable.green, "Green Crop T- Shirt", "Size: M", "Color: Green", "900", "20000", "5"));
        list.add(new ConfirmationModel(R.drawable.green, "Green Crop T- Shirt", "Size: M", "Color: Green", "900", "20000", "5"));
        list.add(new ConfirmationModel(R.drawable.green, "Green Crop T- Shirt", "Size: M", "Color: Green", "900", "20000", "5"));
        adapter = new ConfirmationAdapter(activity, list);
        rv_Confirmation.setAdapter(adapter);
    }

    private void billingAmount() {

    }


    private void apiUrl() {
        endPoint = Config.Url.checkoutendPoint;
    }

    private void apiPlaceOrder() {
        productDetail.setEmailId(Config.shipEmail);
        productDetail.setPhoneNumber(Config.shipphone);
        productDetail.setShippingAddress1(Config.shipAddress);
        productDetail.setShippingAddress2(Config.shipAddress);
        productDetail.setShippingFirstName(Config.shipFirstName);
        productDetail.setShippingLastName(Config.shipLastName);
        productDetail.setShippingCity(Config.shipCity);
        productDetail.setShippingCountry(Config.shipCountry);
        productDetail.setShippingPostCode(Config.shipPinCode);
        productDetail.setShippingZone("xzc");
        productDetail.setShippingAddressFormat("");

        try {

            ProductDetailData productDetailData = new ProductDetailData();
            productDetailData.setName("Apple iPhone X");
            productDetailData.setModel("");
            productDetailData.setPrice(91900);
            productDetailData.setProductId(287);
            productDetailData.setQuantity(2);
            list1.add(productDetailData);
            productDetail.setProductDetails(list1);

            System.out.println(productDetail);

            apiUrl();
            ApiCaller.registerUser(productDetail, activity,endPoint,token,
                    new FutureCallback<PlaceOrderResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, PlaceOrderResponseModel result) {
                            if (e != null) {
                             System.out.println(e);
                            } else {
                              System.out.println(result);
                            }
                        }
                    });
        } catch (Exception e) {
            System.out.println(e);
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
