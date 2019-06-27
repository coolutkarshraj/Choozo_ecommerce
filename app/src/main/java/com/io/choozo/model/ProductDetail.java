package com.io.choozo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail {

    @SerializedName("shippingFirstName")
    @Expose
    private String shippingFirstName;
    @SerializedName("shippingLastName")
    @Expose
    private String shippingLastName;
    @SerializedName("shippingCompany")
    @Expose
    private String shippingCompany;
    @SerializedName("shippingAddress_1")
    @Expose
    private String shippingAddress1;
    @SerializedName("shippingAddress_2")
    @Expose
    private String shippingAddress2;
    @SerializedName("shippingCity")
    @Expose
    private String shippingCity;
    @SerializedName("shippingPostCode")
    @Expose
    private String shippingPostCode;
    @SerializedName("shippingCountry")
    @Expose
    private String shippingCountry;
    @SerializedName("shippingZone")
    @Expose
    private String shippingZone;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("shippingAddressFormat")
    @Expose
    private String shippingAddressFormat;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("productDetails")
    @Expose
    private ArrayList<ProductDetailData> productDetails = null;

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostCode() {
        return shippingPostCode;
    }

    public void setShippingPostCode(String shippingPostCode) {
        this.shippingPostCode = shippingPostCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<ProductDetailData> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ArrayList<ProductDetailData> productDetails) {
        this.productDetails = productDetails;
    }
}
