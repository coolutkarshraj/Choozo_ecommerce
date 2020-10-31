package com.io.choozo.model.responseModel.district;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDistrictDataModel {

    @SerializedName("arabicName")
    private String arabicName;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("districtId")
    private int districtId;

    @SerializedName("cities")
    private List<CitiesItem> cities;

    @SerializedName("modifiedDate")
    private Object modifiedDate;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Object id;

    public String getArabicName(){
        return arabicName;
    }

    public String getCreatedDate(){
        return createdDate;
    }

    public int getDistrictId(){
        return districtId;
    }

    public List<CitiesItem> getCities(){
        return cities;
    }

    public Object getModifiedDate(){
        return modifiedDate;
    }

    public String getName(){
        return name;
    }

    public Object getId(){
        return id;
    }
}
