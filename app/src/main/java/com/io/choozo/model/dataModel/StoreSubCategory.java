package com.io.choozo.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreSubCategory {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("storeSubCategoryId")
    @Expose
    private Integer storeSubCategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("arabicName")
    @Expose
    private String arabicName;
    @SerializedName("storeCategoryId")
    @Expose
    private Integer storeCategoryId;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStoreSubCategoryId() {
        return storeSubCategoryId;
    }

    public void setStoreSubCategoryId(Integer storeSubCategoryId) {
        this.storeSubCategoryId = storeSubCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
