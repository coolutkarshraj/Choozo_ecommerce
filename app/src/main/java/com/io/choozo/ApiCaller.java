package com.io.choozo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.io.choozo.model.ProductDetail;
import com.io.choozo.model.dataModel.CustomerRegisterResponseModel;
import com.io.choozo.model.responseModel.AddAddressResponseModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.io.choozo.model.responseModel.ChangePasswordResponseModel;
import com.io.choozo.model.responseModel.ContactUsResponseModel;
import com.io.choozo.model.responseModel.CountryListResponseModel;
import com.io.choozo.model.responseModel.DeleteAddressResponseModel;
import com.io.choozo.model.responseModel.DeleteProductWishlistResponseModel;
import com.io.choozo.model.responseModel.ForgotPasswordResponseModel;
import com.io.choozo.model.responseModel.GetAddressResponseModel;
import com.io.choozo.model.responseModel.GetBannerListResponseModel;
import com.io.choozo.model.responseModel.GetPageListResponseModel;
import com.io.choozo.model.responseModel.GetProductDataResponseModel;
import com.io.choozo.model.responseModel.GetProfileResponseModel;
import com.io.choozo.model.responseModel.GetWishlistResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.OurBrandsResponseModel;
import com.io.choozo.model.responseModel.PlaceOrderResponseModel;
import com.io.choozo.model.responseModel.ProductListResponseModel;
import com.io.choozo.model.responseModel.SearchResponseModel;
import com.io.choozo.model.responseModel.TodayDealsResponseModel;
import com.io.choozo.model.responseModel.UpdateAddResponseModel;
import com.io.choozo.model.responseModel.WishlistResponseModel;
import com.io.choozo.model.responseModel.address.AdressResponseModel;
import com.io.choozo.model.responseModel.address.DeleteAddressResponseModels;
import com.io.choozo.model.responseModel.address.EditAddressResponseModel;
import com.io.choozo.model.responseModel.district.GetDistrictResponseModel;
import com.io.choozo.model.responseModel.editProfiel.EditProfileResponseModel;
import com.io.choozo.model.responseModel.featured.FeaturedProductResponseModel;
import com.io.choozo.model.responseModel.wishlist.WishListAddRemoveResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.FilePart;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApiCaller {

    /* -----------------------------------------------------Registration api------------------------------------------------------*/

    public static void registerCustomer(Activity activity, String url, String name, String email, String phone, String password,
                                        String confirmpassword, final FutureCallback<CustomerRegisterResponseModel> apiCallBack) {

        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password", password);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        CustomerRegisterResponseModel customerRegisterResponseModel = gson.fromJson(result, CustomerRegisterResponseModel.class);
                        apiCallBack.onCompleted(e, customerRegisterResponseModel);
                    }
                });
    }

    /*-------------------------------------------------------- Login Api---------------------------------------------------------*/

    public static void loginCustomer(Activity activity, String url, String email, String password,
                                     final FutureCallback<LoginResponseModel> apiCallBack) {
        JsonObject json = new JsonObject();
        json.addProperty("username", email);
        json.addProperty("password", password);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        LoginResponseModel loginResponseModel = gson.fromJson(result, LoginResponseModel.class);
                        apiCallBack.onCompleted(e, loginResponseModel);
                    }
                });
    }


    /*-------------------------------------------------------- forgot password Api---------------------------------------------------------*/

    public static void forgotPassword(Activity activity, String url, String email,
                                      final FutureCallback<ForgotPasswordResponseModel> apiCallBack) {
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ForgotPasswordResponseModel forgotPasswordResponseModel = gson.fromJson(result, ForgotPasswordResponseModel.class);
                        apiCallBack.onCompleted(e, forgotPasswordResponseModel);
                    }
                });
    }
    /*-------------------------------------------------------- change password Api---------------------------------------------------------*/

    public static void changePassword(Activity activity, String url, String oldPassword, String newPassowrd, String token,
                                      final FutureCallback<ChangePasswordResponseModel> apiCallBack) {
        JsonObject json = new JsonObject();
        json.addProperty("oldPassword", oldPassword);
        json.addProperty("newPassword", newPassowrd);


        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ChangePasswordResponseModel changePasswordResponseModel = gson.fromJson(result, ChangePasswordResponseModel.class);
                        apiCallBack.onCompleted(e, changePasswordResponseModel);
                    }
                });
    }

    /*-------------------------------------------------get profile--------------------------------------------------------*/

    public static void getUserProfile(Activity activity, String url, String token,
                                      final FutureCallback<GetProfileResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetProfileResponseModel getProfileResponseModel = gson.fromJson(result, GetProfileResponseModel.class);
                        apiCallBack.onCompleted(e, getProfileResponseModel);
                    }
                });
    }

    /* -----------------------------------------------------edit profile api-------------------------------------------*/

    public static void editProfile(Activity activity, String url, String Name, String bio, String gender, String dateOfBirth,
                                   String strPhone, String token,  File file,
                                   final FutureCallback<EditProfileResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        List<Part> files = new ArrayList();
        if(file==null){

            Ion.with(activity)
                    .load(UrlLocator.getFinalUrl("user/update"))
                    .setHeader("Authorization", "Bearer " + token)
                    .setMultipartParameter("name", Name)
                    .setMultipartParameter("bio", bio)
                    .setMultipartParameter("gender", gender)
                    .setMultipartParameter("dateOfBirth", dateOfBirth)
                    .setMultipartParameter("phone", strPhone)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            EditProfileResponseModel editProfileDataModel = gson.fromJson(result, EditProfileResponseModel.class);
                            apiCallBack.onCompleted(e, editProfileDataModel);
                        }
                    });
        }else {

            files.add(new FilePart("avatar", file));
            Ion.with(activity)
                    .load(UrlLocator.getFinalUrl("user/update"))
                    .setHeader("Authorization", "Bearer " + token)
                    .setMultipartParameter("name", Name)
                    .addMultipartParts(files)
                    .setMultipartParameter("bio", bio)
                    .setMultipartParameter("gender", gender)
                    .setMultipartParameter("dateOfBirth", dateOfBirth)
                    .setMultipartParameter("phone", strPhone)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            EditProfileResponseModel editProfileDataModel = gson.fromJson(result, EditProfileResponseModel.class);
                            apiCallBack.onCompleted(e, editProfileDataModel);
                        }
                    });
        }

    }
 public static void editProfileSignup(Activity activity, String url, String Name, String bio, String gender, String dateOfBirth,
                                   String strPhone, String token,
                                   final FutureCallback<EditProfileResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .setMultipartParameter("name", Name)
                .setMultipartParameter("bio", bio)
                .setMultipartParameter("gender", gender)
                .setMultipartParameter("dateOfBirth", dateOfBirth)
                .setMultipartParameter("phone", strPhone)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        EditProfileResponseModel editProfileDataModel = gson.fromJson(result, EditProfileResponseModel.class);
                        apiCallBack.onCompleted(e, editProfileDataModel);
                    }
                });

    }

    /*------------------------------------------------------ contact us--------------------------------------------------------*/

    public static void getSettings(Activity activity, String url,
                                   final FutureCallback<ContactUsResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ContactUsResponseModel contactUsResponseModel = gson.fromJson(result, ContactUsResponseModel.class);
                        apiCallBack.onCompleted(e, contactUsResponseModel);
                    }
                });
    }
    /*------------------------------------------------- Category Api-----------------------------------------------------------*/

    public static void getCategoryList(Activity activity, String url,
                                       final FutureCallback<CategoryResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        CategoryResponseModel categoryResponseModel = gson.fromJson(result, CategoryResponseModel.class);
                        apiCallBack.onCompleted(e, categoryResponseModel);
                    }
                });


    }


    /*------------------------------------------------------ about -----------------------------------------------------------------*/

    public static void pageList(Activity activity, String url,
                                final FutureCallback<GetPageListResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetPageListResponseModel getPageListResponseModel = gson.fromJson(result, GetPageListResponseModel.class);
                        apiCallBack.onCompleted(e, getPageListResponseModel);
                    }
                });

    }

    /* ------------------------------------------------------Add address api-------------------------------------------------------*/


    public static void addAddress(Activity activity, String url, String name,String address,String city,String area,
                                  String pincode,String mobile, String addressType, int defalutAddress,
                                  String token, final FutureCallback<AdressResponseModel> apiCallBack) {

        final JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("address", address);
        json.addProperty("addresstype", addressType);
        json.addProperty("phonenum", mobile);
        json.addProperty("code", " ");
        json.addProperty("locality", " ");
        json.addProperty("city", city);
        json.addProperty("district", area);
        json.addProperty("state", " ");
        json.addProperty("country", " ");
        json.addProperty("landmark", " ");
        json.addProperty("zipcode", pincode);
        json.addProperty("streetOne", " ");
        json.addProperty("streetTwo", " ");
        json.addProperty("isDefault", defalutAddress);

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .setHeader("Content-Type", "application/json")
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        AdressResponseModel addAddressResponseModel = gson.fromJson(result, AdressResponseModel.class);
                        apiCallBack.onCompleted(e, addAddressResponseModel);
                    }
                });
    }

    public static void editAddress(Activity activity, String url,int addressId, String name,String address,String city,String area,
                                  String pincode,String mobile, String addressType, int defalutAddress,
                                  String token, final FutureCallback<EditAddressResponseModel> apiCallBack) {

        final JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("addressId", addressId);
        json.addProperty("address", address);
        json.addProperty("addresstype", addressType);
        json.addProperty("phonenum", Long.valueOf(mobile));
        json.addProperty("code", " ");
        json.addProperty("locality", " ");
        json.addProperty("city", city);
        json.addProperty("district", area);
        json.addProperty("state", " ");
        json.addProperty("country", " ");
        json.addProperty("landmark", " ");
        json.addProperty("zipcode", pincode);
        json.addProperty("streetOne", " ");
        json.addProperty("streetTwo", " ");
        json.addProperty("isDefault", defalutAddress);

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .setHeader("Content-Type", "application/json")
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        EditAddressResponseModel addAddressResponseModel = gson.fromJson(result, EditAddressResponseModel.class);
                        apiCallBack.onCompleted(e, addAddressResponseModel);
                    }
                });
    }

    /*------------------------------------------------------ get Customer Address list----------------------------------------------*/

    public static void getCustomerAddress(Activity activity, String url, String token,
                                          final FutureCallback<GetAddressResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetAddressResponseModel getAddressResponseModel = gson.fromJson(result, GetAddressResponseModel.class);
                        apiCallBack.onCompleted(e, getAddressResponseModel);
                    }
                });
    }

    /*----------------------------------------------------- Update address api---------------------------------------------*/


    public static void updateAddress(Context activity, String url, int customerId, String address1, String address2, String city, String state,
                                     String pincode, String addressType, String token, final FutureCallback<UpdateAddResponseModel> apiCallBack) {

        final JsonObject json = new JsonObject();
        json.addProperty("customerId", customerId);
        json.addProperty("address1", address1);
        json.addProperty("address2", address2);
        json.addProperty("city", city);
        json.addProperty("state", state);
        json.addProperty("postcode", pincode);
        json.addProperty("addressType", addressType);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load("PUT", UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        UpdateAddResponseModel updateAddResponseModel = gson.fromJson(result, UpdateAddResponseModel.class);
                        apiCallBack.onCompleted(e, updateAddResponseModel);
                    }
                });
    }

    /* ---------------------------------------------Delete address api--------------------------------------------------------------*/

    public static void deleteAddress(Context activity, String url,
                                     String token, final FutureCallback<DeleteAddressResponseModels> apiCallBack) {


        final Gson gson = new Gson();
        Ion.with(activity)
                .load( UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        DeleteAddressResponseModels updateAddResponseModel = gson.fromJson(result, DeleteAddressResponseModels.class);
                        apiCallBack.onCompleted(e, updateAddResponseModel);
                    }
                });
    }


    /*-------------------------------------- get Product List According to categorty--------------------------------------------*/

    public static void productList(Context activity, String url, final FutureCallback<ProductListResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .addHeader("skipauth","true")
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ProductListResponseModel addAddressResponseModel = gson.fromJson(result, ProductListResponseModel.class);
                        apiCallBack.onCompleted(e, addAddressResponseModel);
                    }
                });
    }

    /*----------------------------------------------------------- get Banner api----------------------------------------------------*/

    public static void getBanner(Activity activity, String url,
                                 final FutureCallback<GetBannerListResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetBannerListResponseModel getBannerListResponseModel = gson.fromJson(result, GetBannerListResponseModel.class);
                        apiCallBack.onCompleted(e, getBannerListResponseModel);
                    }
                });
    }


    /* --------------------------------------------------------get product Detail--------------------------------------------------*/

    public static void getproductDetail(Activity activity, String url,
                                        final FutureCallback<GetProductDataResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetProductDataResponseModel productDetailResponseModel = gson.fromJson(result, GetProductDataResponseModel.class);
                        apiCallBack.onCompleted(e, productDetailResponseModel);
                    }
                });
    }

    /*----------------------------------------------------- wishlist Api -------------------------------------------------------------*/

    public static void wishlistadd(Context activity, String url, int productid, String token,
                                   final FutureCallback<WishListAddRemoveResponseModel> apiCallBack) {



        final Gson gson = new Gson();
        Ion.with(activity)
                .load("POST", UrlLocator.getFinalUrl(url)+productid)
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        WishListAddRemoveResponseModel wishlistResponseModel = gson.fromJson(result, WishListAddRemoveResponseModel.class);
                        apiCallBack.onCompleted(e, wishlistResponseModel);
                    }
                });
    }

    /*----------------------------------------------------- delete product from wishlist ------------------------------------------------*/

    public static void wishlistDelete(Activity activity, String url, String token,
                                      final FutureCallback<DeleteProductWishlistResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load("DELETE", UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        DeleteProductWishlistResponseModel deleteProductWishlistResponseModel = gson.fromJson(result, DeleteProductWishlistResponseModel.class);
                        apiCallBack.onCompleted(e, deleteProductWishlistResponseModel);
                    }
                });
    }

    /* ------------------------------------------------------- get wishlist product list ---------------------------------------------*/

    public static void getWishlistList(Activity activity, String url, String token,
                                       final FutureCallback<GetWishlistResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetWishlistResponseModel getWishlistResponseModel = gson.fromJson(result, GetWishlistResponseModel.class);
                        apiCallBack.onCompleted(e, getWishlistResponseModel);
                    }
                });
    }


    /*------------------------------------------------------ featured product list get -------------------------------------------------*/

    public static void getFeaturedProduct(Activity activity, String url,
                                          final FutureCallback<com.io.choozo.model.responseModel.featured.FeaturedProductResponseModel> apiCallback) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))

                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        com.io.choozo.model.responseModel.featured.FeaturedProductResponseModel featuredProductResponseModel = gson.fromJson(result, FeaturedProductResponseModel.class);
                        apiCallback.onCompleted(e, featuredProductResponseModel);
                    }
                });
    }

    /* ------------------------------------------------------ Today Deals list get ------------------------------------------------------*/

    public static void getTodayDeals(Activity activity, String url,String token,
                                     final FutureCallback<TodayDealsResponseModel> apiCallBack) {

String key;
String value;
        if(token==""){
         key = "skipauth";
         value = "true";
        }else{
            key = "Authorization";
            value ="Bearer "+token;
        }

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader(key, value)
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        TodayDealsResponseModel todayDealsResponseModel = gson.fromJson(result, TodayDealsResponseModel.class);
                        apiCallBack.onCompleted(e, todayDealsResponseModel);
                    }
                });
    }

    /*------------------------------------------------------- our brands Api------------------------------------------------------------*/

    public static void getOurBarnds(Activity activity, String url,
                                    final FutureCallback<OurBrandsResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        OurBrandsResponseModel ourBrandsResponseModel = gson.fromJson(result, OurBrandsResponseModel.class);
                        apiCallBack.onCompleted(e, ourBrandsResponseModel);
                    }
                });
    }


    /* ----------------------------------------------------this work is for just test --------------------------------------------------*/

    /*-------------------------------------------------- Checkout(Proced order) --------------------------------------------------------)*/

    public static void procedorder(Activity activity, String url, String productDetails, String shippingFirstName, String shippingLastName,
                                   String shippingCompany, String shippingAddress_1, String shippingAddress_2, String shippingCity,
                                   String shippingPostCode, String shippingCountry, String shippingZone, String shippingAddressFormat,
                                   String phoneNumber, String emailId, String token, final FutureCallback<PlaceOrderResponseModel> apiCallBack) {

        final JsonObject json = new JsonObject();
        json.addProperty("productDetails", productDetails);
        json.addProperty("shippingFirstName", shippingFirstName);
        json.addProperty("shippingLastName", shippingLastName);
        json.addProperty("shippingCompany", shippingCompany);
        json.addProperty("shippingAddress_1", shippingAddress_1);
        json.addProperty("shippingAddress_2", shippingAddress_2);
        json.addProperty("shippingCity", shippingCity);
        json.addProperty("shippingPostCode", shippingPostCode);
        json.addProperty("shippingCountry", shippingCountry);
        json.addProperty("shippingZone", shippingZone);
        json.addProperty("shippingAddressFormat", shippingAddressFormat);
        json.addProperty("phoneNumber", phoneNumber);
        json.addProperty("emailId", emailId);
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache().setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        PlaceOrderResponseModel placeOrderResponseModel = gson.fromJson(result, PlaceOrderResponseModel.class);
                        apiCallBack.onCompleted(e, placeOrderResponseModel);
                    }
                });

    }


    public static void registerUser(final ProductDetail registrationModel,
                                    final Activity activity, String url, String token,
                                    final FutureCallback<PlaceOrderResponseModel> apiCallBack) {

        final Gson gson = new Gson();
        String requestString = gson.toJson(registrationModel);
        JsonObject json = gson.fromJson(requestString, JsonObject.class);
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        PlaceOrderResponseModel registrationModel = gson.fromJson(result, PlaceOrderResponseModel.class);
                        apiCallBack.onCompleted(e, registrationModel);
                    }
                });
    }

    public static void proccedOrder(Activity activity, String url, String token, String Data,
                                    final FutureCallback<PlaceOrderResponseModel> apiCallBack) {


        final Gson gson = new Gson();
        Ion.with(activity)
                .load("POST", UrlLocator.getFinalUrl(url))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer " + token)
                .noCache()
                .setStringBody(Data)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        PlaceOrderResponseModel placeOrderResponseModel = gson.fromJson(result, PlaceOrderResponseModel.class);
                        apiCallBack.onCompleted(e, placeOrderResponseModel);
                    }
                });
    }



    /*------------------------------------------------------- Country List Api-------------------------------------------------------*/

    public static void getcountryList(Activity activity, String url,
                                      final FutureCallback<CountryListResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        CountryListResponseModel countryListResponseModel = gson.fromJson(result, CountryListResponseModel.class);
                        apiCallBack.onCompleted(e, countryListResponseModel);
                    }
                });
    }

    /*------------------------------------------------------- serach product api --------------------------------------------------*/

    public static void getsearchData(Activity activity, String url,
                                     final FutureCallback<SearchResponseModel> apiCallBack){

        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        SearchResponseModel searchResponseModel = gson.fromJson(result, SearchResponseModel.class);
                        apiCallBack.onCompleted(e, searchResponseModel);
                    }
                });
    }


    public static void getdistic(Activity activity, String url,
                                 final FutureCallback<GetDistrictResponseModel> apiCallBack) {
        final Gson gson = new Gson();
        Ion.with(activity)
                .load(UrlLocator.getFinalUrl(url))
                .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        GetDistrictResponseModel dilveryAddressDataModel = gson.fromJson(result, GetDistrictResponseModel.class);
                        apiCallBack.onCompleted(e, dilveryAddressDataModel);
                    }
                });

    }

    public static void showAlertDialog(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setTitle(message);
        dialog.show();
    }


}
