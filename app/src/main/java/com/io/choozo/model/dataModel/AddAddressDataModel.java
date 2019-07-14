
package com.io.choozo.model.dataModel;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AddAddressDataModel {

    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("addressId")
    private Long mAddressId;
    @SerializedName("addressType")
    private String mAddressType;
    @SerializedName("city")
    private String mCity;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("customerId")
    private String mCustomerId;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("lastName")
    private Object mLastName;
    @SerializedName("postcode")
    private String mPostcode;
    @SerializedName("state")
    private String mState;

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

    public Long getAddressId() {
        return mAddressId;
    }

    public void setAddressId(Long addressId) {
        mAddressId = addressId;
    }

    public String getAddressType() {
        return mAddressType;
    }

    public void setAddressType(String addressType) {
        mAddressType = addressType;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Object getLastName() {
        return mLastName;
    }

    public void setLastName(Object lastName) {
        mLastName = lastName;
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

}
