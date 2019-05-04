package com.io.choozo;

import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;

import java.util.List;

public class Config {

    public static String Base_url = "13.233.195.231";
    public static List<ChildDataModel> childDataModel;
    public static List<SubChildDataModel> subChildDataModels;

    public static class Url{
        public final static String  categoryList = "list/category-list?limit=&offset=0&keyword=&sortOrder=";
        public final static String getBanner ="list/banner-list?limit=10&offset=0";
    }
}
