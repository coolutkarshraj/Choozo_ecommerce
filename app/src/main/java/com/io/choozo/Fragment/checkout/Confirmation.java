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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
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
import com.io.choozo.util.ApiInterface;
import com.io.choozo.util.ServiceGenerator;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Confirmation extends Fragment implements View.OnClickListener {
    RelativeLayout orderNow;
    RecyclerView rv_Confirmation;
    ConfirmationAdapter adapter;
    String endPoint, token;
    String strP_Qty, strP_Price, strP_Name, strP_Id;
    TextView tvName, tvAddress, tvCity, tvOrderAmt, tvDelivery, tvTotalAmount;
    List<ConfirmationModel> list = new ArrayList<>();
    Activity activity;
    ProductDetail productDetail;
    PreferenceManager preferenceManager;
    ArrayList<ProductDetailData> list1;
    JsonObject jsonObj;

    private static String strData;


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
                apiProceedOrder();
                return;
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

        list.clear();

        JSONArray jArray = null;
        try {
            jArray = new JSONArray(Config.CartData);
            list.clear();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                ConfirmationModel confirmationModel = new ConfirmationModel();
                confirmationModel.setId(json_data.getString("Id"));
                confirmationModel.setName(json_data.getString("Name"));
                strP_Name = json_data.getString("Name");
                String img = json_data.getString("Image");
                img.replaceAll("\\\\", "");
                confirmationModel.setImage(img);
                confirmationModel.setQuantity(json_data.getString("Quantity"));
                strP_Qty = json_data.getString("Quantity");
                confirmationModel.setPrice(json_data.getString("Price"));

                strP_Price = json_data.getString("Price");
                confirmationModel.setPID(json_data.getString("P_ID"));
                strP_Id = json_data.getString("P_ID");
                list.add(confirmationModel);

                rv_Confirmation.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                adapter = new ConfirmationAdapter(activity, list);
                rv_Confirmation.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void billingAmount() {

    }

    /*--------------------------------------------------------- Api url ----------------------------------------------------------------*/

    private void apiUrl() {
        endPoint = Config.Url.checkoutendPoint;
    }


    /* ----------------------------------------------------- Data Store into Model Class ----------------------------------------------*/
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

        list1.clear();
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(Config.CartData);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                ConfirmationModel confirmationModel = new ConfirmationModel();
                strP_Name = json_data.getString("Name");
                strP_Qty = json_data.getString("Quantity");
                strP_Price = json_data.getString("Price");
                strP_Id = json_data.getString("P_ID");
                ProductDetailData productDetailData = new ProductDetailData();
                productDetailData.setName(strP_Name);
                productDetailData.setModel("");
                productDetailData.setPrice(strP_Price);
                productDetailData.setProductId(Integer.valueOf(strP_Id));
                productDetailData.setQuantity(Integer.valueOf(strP_Qty));
                list1.add(productDetailData);
                productDetail.setProductDetails(list1);

                Log.e("productDetail", "" + productDetail);
                System.out.println(productDetail);
                toJSon(productDetail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

  /*-------------------------------------------------- java data convert into json Forment------------------------------------------*/

    private  String toJSon(ProductDetail productDetail) {
        try {

             jsonObj = new JsonObject();
            //jsonObj = new HashMap<>();
            jsonObj.addProperty("shippingFirstName", productDetail.getShippingFirstName());
            jsonObj.addProperty("shippingLastName", productDetail.getShippingLastName());
            jsonObj.addProperty("shippingCompany", productDetail.getShippingCompany());
            jsonObj.addProperty("shippingAddress_1", productDetail.getShippingAddress1());
            jsonObj.addProperty("shippingAddress_2", productDetail.getShippingAddress2());
            jsonObj.addProperty("shippingCity", productDetail.getShippingCity());
            jsonObj.addProperty("shippingPostCode", productDetail.getShippingPostCode());
            jsonObj.addProperty("shippingCountry", productDetail.getShippingCountry());
            jsonObj.addProperty("shippingZone", productDetail.getShippingZone());
            jsonObj.addProperty("phoneNumber", productDetail.getPhoneNumber());
            jsonObj.addProperty("shippingAddressFormat", productDetail.getShippingAddressFormat());
            jsonObj.addProperty("emailId", productDetail.getEmailId());

            JsonArray jsonArr = new JsonArray();

            for (int i = 0; i < productDetail.getProductDetails().size(); i++) {
               JsonObject pnObj = new JsonObject();
               // Map<String, String> pnObj = new HashMap<>();
                pnObj.addProperty("productId", String.valueOf(productDetail.getProductDetails().get(i).getProductId()));
                pnObj.addProperty("quantity", String.valueOf(productDetail.getProductDetails().get(i).getQuantity()));
                pnObj.addProperty("price", productDetail.getProductDetails().get(i).getPrice());
                pnObj.addProperty("model", "");
                pnObj.addProperty("name", productDetail.getProductDetails().get(i).getName());
                jsonArr.add(String.valueOf(pnObj));
            }
            jsonObj.addProperty("productDetails", String.valueOf(jsonArr));
            strData = jsonObj.toString();

            Log.e("result", jsonObj.toString());

            return jsonObj.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    private void apiProceedOrder(){
       /* apiUrl();
        ApiCaller.proccedOrder(activity, endPoint, token, strData, new FutureCallback<PlaceOrderResponseModel>() {
            @Override
            public void onCompleted(Exception e, PlaceOrderResponseModel result) {
                if(e!=null){
                    return;
                }
                else {
                    Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        apiUrl();
        ApiInterface jsonPostService = ServiceGenerator.createService(ApiInterface.class,"http://18.208.183.9:8000/api/");


        RequestBody data = RequestBody.create(MediaType.parse("application/json"),strData);
        Call<PlaceOrderResponseModel> call1 =  jsonPostService.postSomething("Bearer "+ token,data);

        call1.enqueue(new Callback<PlaceOrderResponseModel>() {

            @Override
            public void onResponse(Call<PlaceOrderResponseModel> call, Response<PlaceOrderResponseModel> response) {
                try{
                    Log.e("response-success", response.body().toString());
                    Toast.makeText(activity, "done"+response.body().toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<PlaceOrderResponseModel> call, Throwable t) {
                Log.e("response-failure", call.toString());
                Toast.makeText(activity, "error"+call.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }



}
