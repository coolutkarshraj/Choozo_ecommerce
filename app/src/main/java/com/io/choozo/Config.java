package com.io.choozo;

import android.support.v4.view.ViewPager;

import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;

import java.util.List;

public class Config {

    public static String Base_url = "admin.dincharyamart.com";
    public static String imageUrl = "https://admin.dincharyamart.com/api/media/render?path=";
    public static String noImage = "https://sisterhoodofstyle.com/wp-content/uploads/2018/02/no-image-1.jpg";
    public static List<ChildDataModel> childDataModel;
    public static List<SubChildDataModel> subChildDataModels;
    public static String CartCount;
    public static String getProfileApi;
    public static CategoryResponseModel categoryResponseModel;
    public static int categoryClickId;
    public static String productId;
    public static String toolbarName;
    public static int subCategoryClickId;
    public static String CartData ="";
    public static ViewPager viewPager;
    public static String paymentAmount;
    public static String shipFirstName;
    public static String shipLastName;
    public static String shipEmail;
    public static String shipphone;
    public static String shipAddress;
    public static String shipCountry;
    public static String shipCity;
    public static String shipState;
    public static String shipPinCode;
    public static float shipdeleveryCharge;

    public static String C_Name;
    public static String productdescription;
    public static String somethingWrong ="Something went wrong";

    public static class Url{
        public final static String  categoryList = "StoreCategory/store-category-list/store/-1";
        public final static String getBanner ="advert/list";
        public final static String registerCustomer ="user/signup";
        public final static String loginCustomer ="user/login";
        public final static String forgotPassword ="user/forgot-password";
        public final static String changePassword ="user/change-password";
        public final static String getUserData ="user/detail/";
        public final static String editProfile ="user/update";
        public final static String disticGet ="order/district/-1";
        public final static String getSetting ="settings/get-settings";
        public final static String getPageList ="pages/pagelist?limit=&offset=0&keyword=";
        public final static String addCustomerAddress ="order/add-address";
        public final static String getCustomerAddress ="address/addresslist";
        public final static String updateAddress ="order/edit-address";
        public final static String deleteAddress ="order/delete-address/";
        public final static String productlist ="product/list/store";
        public final static String imageResize ="media/image-resize?";
        public final static String productdetail ="product-store/productdetail/";
        public final static String wishlistdata ="product/add-remove-wishlist/?id=";
        public final static String deleteproductfromWishList ="customer/wishlist-product-delete";
        public final static String getWishlistProduct ="customer/wishlist-product-list";
        public final static String getfeaturedProduct ="store/premium-store-list";
        public final static String getTodayDeals ="product/todaydeal-product-list";
        public final static String getbrandsDetail ="manufacturers/manufacturerlist?";
        public final static String checkoutendPoint ="orders/customer-checkout";
        public final static String countryList ="list/country-list?limit=&offset=0&keyword=";
        public final static String searchProduct ="list/productlist?limit=&offset=0&manufacturerId=&categoryId=&keyword=&price=1&priceFrom=&priceTo=";



    }
}
