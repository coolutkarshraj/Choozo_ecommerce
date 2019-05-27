package com.io.choozo;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.io.choozo.model.dataModel.CustomerRegisterResponseModel;
import com.io.choozo.model.dataModel.EditProfileDataModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.io.choozo.model.responseModel.ChangePasswordResponseModel;
import com.io.choozo.model.responseModel.EditProfileResponseModel;
import com.io.choozo.model.responseModel.ForgotPasswordResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
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


    /*-------------------------------------------------------- forgot password Api---------------------------------------------------------*/

    public static void forgotPassword(Activity activity,String url,String email ,
                                     final FutureCallback<ForgotPasswordResponseModel> apiCallBack){
        JsonObject json = new JsonObject();
        json.addProperty("emailId",email);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ForgotPasswordResponseModel forgotPasswordResponseModel = gson.fromJson(result,ForgotPasswordResponseModel.class);
                        apiCallBack.onCompleted(e,forgotPasswordResponseModel);
                    }
                });
    }
    /*-------------------------------------------------------- change password Api---------------------------------------------------------*/

    public static void changePassword(Activity activity,String url,String oldPassword , String newPassowrd,String token,
                                      final FutureCallback<ChangePasswordResponseModel> apiCallBack){
        JsonObject json = new JsonObject();
        json.addProperty("oldPassword",oldPassword);
        json.addProperty("newPassword",newPassowrd);


        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization","Bearer "+token)
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ChangePasswordResponseModel changePasswordResponseModel = gson.fromJson(result,ChangePasswordResponseModel.class);
                        apiCallBack.onCompleted(e,changePasswordResponseModel);
                    }
                });
    }

    /*-------------------------------------------------get profile--------------------------------------------------------*/

    public static void  getUserProfile(Activity  activity , String url , String token ,
                                       final FutureCallback<GetProfileResponseModel> apiCallBack){
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization","Bearer "+token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetProfileResponseModel getProfileResponseModel = gson.fromJson(result,GetProfileResponseModel.class);
                        apiCallBack.onCompleted(e,getProfileResponseModel);
                    }
                });
    }

    /* -----------------------------------------------------edit profile api-------------------------------------------*/

    public static void editProfile(Activity activity,String url,String firstName,String lastName,String email,String Address,
                                   String countryId,String pinCode,String mobile,String token,String image,
                                   final FutureCallback<EditProfileResponseModel> apiCallBack)
    {
        final JsonObject json = new JsonObject();
        json.addProperty("firstName",firstName);
        json.addProperty("lastName",lastName);
        json.addProperty("emailId",email);
        json.addProperty("address",Address);
        json.addProperty("countryId",countryId);
        json.addProperty("pincode",pinCode);
        json.addProperty("phoneNumber",mobile);
        json.addProperty("image",image);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization","Bearer "+token)
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        EditProfileResponseModel editProfileDataModel = gson.fromJson(result,EditProfileResponseModel.class);
                        apiCallBack.onCompleted(e,editProfileDataModel);
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
