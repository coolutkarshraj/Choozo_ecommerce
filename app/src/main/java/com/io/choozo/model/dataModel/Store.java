package com.io.choozo.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("storeId")
    @Expose
    private Integer storeId;
    @SerializedName("avatarPath")
    @Expose
    private String avatarPath;
    @SerializedName("avatarName")
    @Expose
    private String avatarName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("arabicName")
    @Expose
    private String arabicName;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("arabicDescription")
    @Expose
    private String arabicDescription;
    @SerializedName("isPremium")
    @Expose
    private Boolean isPremium;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("phoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("otp")
    @Expose
    private Object otp;
    @SerializedName("isOtpVerify")
    @Expose
    private Object isOtpVerify;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("addedById")
    @Expose
    private Integer addedById;
    @SerializedName("addedByUserId")
    @Expose
    private Integer addedByUserId;
    @SerializedName("registrationToken")
    @Expose
    private String registrationToken;
    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("lastLogged")
    @Expose
    private Object lastLogged;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isApproved")
    @Expose
    private Boolean isApproved;
    @SerializedName("availableForDelivery")
    @Expose
    private Boolean availableForDelivery;
    @SerializedName("availableForOnlineSale")
    @Expose
    private Boolean availableForOnlineSale;
    @SerializedName("deliveryPrice")
    @Expose
    private String deliveryPrice;
    @SerializedName("offerName")
    @Expose
    private String offerName;
    @SerializedName("offerPercentage")
    @Expose
    private String offerPercentage;
    @SerializedName("offerExpiryDate")
    @Expose
    private String offerExpiryDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArabicDescription() {
        return arabicDescription;
    }

    public void setArabicDescription(String arabicDescription) {
        this.arabicDescription = arabicDescription;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

    public Object getIsOtpVerify() {
        return isOtpVerify;
    }

    public void setIsOtpVerify(Object isOtpVerify) {
        this.isOtpVerify = isOtpVerify;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAddedById() {
        return addedById;
    }

    public void setAddedById(Integer addedById) {
        this.addedById = addedById;
    }

    public Integer getAddedByUserId() {
        return addedByUserId;
    }

    public void setAddedByUserId(Integer addedByUserId) {
        this.addedByUserId = addedByUserId;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Object getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(Object lastLogged) {
        this.lastLogged = lastLogged;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public Boolean getAvailableForOnlineSale() {
        return availableForOnlineSale;
    }

    public void setAvailableForOnlineSale(Boolean availableForOnlineSale) {
        this.availableForOnlineSale = availableForOnlineSale;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(String offerPercentage) {
        this.offerPercentage = offerPercentage;
    }

    public String getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(String offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

}
