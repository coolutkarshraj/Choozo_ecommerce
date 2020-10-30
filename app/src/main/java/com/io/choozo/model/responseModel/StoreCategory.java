package com.io.choozo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreCategory {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("storeCategoryId")
    @Expose
    private Integer storeCategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatarName")
    @Expose
    private String avatarName;
    @SerializedName("avatarPath")
    @Expose
    private String avatarPath;
    @SerializedName("arabicName")
    @Expose
    private String arabicName;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
