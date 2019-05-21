package com.io.choozo;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.io.choozo.model.dataModel.CustomerRegisterResponseModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ApiCaller {

  /* -----------------------------------------------------Registration api------------------------------------------------------*/

   public static void registerCustomer(Activity activity,String url,String name,String email,String phone,String password,
                                       String confirmpassword,final FutureCallback<CustomerRegisterResponseModel> apiCallBack){

           JsonObject json = new JsonObject();
           json.addProperty("name",name);
           json.addProperty("emailId",email);
           json.addProperty("phoneNumber",phone);
           json.addProperty("password",password);
           json.addProperty("confirmPassword",confirmpassword);
           final Gson gson = new Gson();
           Ion.with(activity)
                   .load(UrlLocator.getFinalUrl(url))
                   .noCache().setJsonObjectBody(json)
                   .asJsonObject()
                   .setCallback(new FutureCallback<JsonObject>() {
                       @Override
                       public void onCompleted(Exception e, JsonObject result) {
                        CustomerRegisterResponseModel customerRegisterResponseModel =gson.fromJson(result,CustomerRegisterResponseModel.class);
                        apiCallBack.onCompleted(e,customerRegisterResponseModel);
                       }
                   });
   }

   /*-------------------------------------------------------- Login Api---------------------------------------------------------*/

    public static void loginCustomer(Activity activity,String url,String email ,String password,
                                     final FutureCallback<LoginResponseModel> apiCallBack){
        JsonObject json = new JsonObject();
        json.addProperty("emailId",email);
        json.addProperty("password",password);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                      LoginResponseModel loginResponseModel = gson.fromJson(result,LoginResponseModel.class);
                      apiCallBack.onCompleted(e,loginResponseModel);
                   }
                });
    }

    /*------------------------------------------------- Category Api-----------------------------------------------------------*/

    public static void getCategoryList(Activity activity, String url,
                                       final FutureCallback<CategoryResponseModel> apiCallBack){
         final Gson gson = new Gson();
         Ion.with(activity)
            .load(UrlLocator.getFinalUrl(url))
            .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
        @Override
        public void onCompleted(Exception e, JsonObject result) {
            CategoryResponseModel categoryResponseModel =gson.fromJson(result,CategoryResponseModel.class);
            apiCallBack.onCompleted(e,categoryResponseModel);
        }
    });
}

    /*-------------------------------------------- Banner Image Get-----------------------------------------------------------*/
}
