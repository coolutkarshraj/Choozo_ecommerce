package com.io.choozo;

import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;

import java.util.List;

public class Config {

    public static String Base_url = "13.235.17.203";
    public static List<ChildDataModel> childDataModel;
    public static List<SubChildDataModel> subChildDataModels;
    public static String CartCount;
    public static String getProfileApi;
    public static CategoryResponseModel categoryResponseModel;

    public static class Url{
        public final static String  categoryList = "list/category-list?limit=&offset=0&keyword=&sortOrder=";
        public final static String getBanner ="list/banner-list?limit=10&offset=0";
        public final static String registerCustomer ="customer/register";
        public final static String loginCustomer ="customer/login";
        public final static String forgotPassword ="customer/forgot-password";
        public final static String changePassword ="customer/change-password";
        public final static String getUserData ="customer/get-profile";
        public final static String editProfile ="customer/edit-profile";
        public final static String getSetting ="settings/get-settings";
        public final static String getPageList ="pages/pagelist?limit=&offset=0&keyword=";
    }
}
