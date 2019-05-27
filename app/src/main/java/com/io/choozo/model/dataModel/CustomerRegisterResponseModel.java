
package com.io.choozo.model.dataModel;

import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.responseModel.CustomerRegistrationDataModel;

public class CustomerRegisterResponseModel {

    @SerializedName("data")
    private CustomerRegistrationDataModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public CustomerRegistrationDataModel getData() {
        return mData;
    }

    public void setData(CustomerRegistrationDataModel data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
