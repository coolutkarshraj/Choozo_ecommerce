
package com.io.choozo.model.dataModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetAddressDataModel {

    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("addressId")
    private int mAddressId;
    @SerializedName("addressType")
    private int mAddressType;
    @SerializedName("city")
    private String mCity;
    @SerializedName("company")
    private Object mCompany;
    @SerializedName("countryId")
    private Object mCountryId;
    @SerializedName("createdBy")
    private Object mCreatedBy;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("customerId")
    private Long mCustomerId;
    @SerializedName("emailId")
    private Object mEmailId;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("isActive")
    private Object mIsActive;
    @SerializedName("lastName")
    private Object mLastName;
    @SerializedName("modifiedBy")
    private Object mModifiedBy;
    @SerializedName("modifiedDate")
    private Object mModifiedDate;
    @SerializedName("phoneNo")
    private Object mPhoneNo;
    @SerializedName("postcode")
    private String mPostcode;
    @SerializedName("state")
    private String mState;
    @SerializedName("zoneId")
    private Object mZoneId;

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String address1) {
        mAddress1 = address1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String address2) {
        mAddress2 = address2;
    }

    public int getAddressId() {
        return mAddressId;
    }

    public void setAddressId(int addressId) {
        mAddressId = addressId;
    }

    public int getAddressType() {
        return mAddressType;
    }

    public void setAddressType(int addressType) {
        mAddressType = addressType;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public Object getCompany() {
        return mCompany;
    }

    public void setCompany(Object company) {
        mCompany = company;
    }

    public Object getCountryId() {
        return mCountryId;
    }

    public void setCountryId(Object countryId) {
        mCountryId = countryId;
    }

    public Object getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Object createdBy) {
        mCreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public Object getEmailId() {
        return mEmailId;
    }

    public void setEmailId(Object emailId) {
        mEmailId = emailId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Object getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Object isActive) {
        mIsActive = isActive;
    }

    public Object getLastName() {
        return mLastName;
    }

    public void setLastName(Object lastName) {
        mLastName = lastName;
    }

    public Object getModifiedBy() {
        return mModifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        mModifiedBy = modifiedBy;
    }

    public Object getModifiedDate() {
        return mModifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    public Object getPhoneNo() {
        return mPhoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        mPhoneNo = phoneNo;
    }

    public String getPostcode() {
        return mPostcode;
    }

    public void setPostcode(String postcode) {
        mPostcode = postcode;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public Object getZoneId() {
        return mZoneId;
    }

    public void setZoneId(Object zoneId) {
        mZoneId = zoneId;
    }

}
