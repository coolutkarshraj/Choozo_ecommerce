package com.io.choozo.util;

import com.google.gson.JsonObject;
import com.io.choozo.model.ProductDetail;
import com.io.choozo.model.requestModel.PlaceOrderRequestModel;
import com.io.choozo.model.responseModel.PlaceOrderResponseModel;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    String URL_BASE = "http://18.208.183.9:8000/api/";

    /*@Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @POST("orders/customer-checkout")
    Call<PlaceOrderResponseModel> postRawJSON(@Body ProductDetail locationPost, @Header("Authorization") String authHeader );*/


    @Headers("Content-Type: application/json")
    @POST("orders/customer-checkout")
    Call<String> getUser(@Body Map<String, String> body, @Header("Authorization") String authHeader);



    @POST("orders/customer-checkout")
    Call<PlaceOrderResponseModel> postSomething(@Header("Authorization") String authHeader , @Body RequestBody params);
}
