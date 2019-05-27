
package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.AddAddressDataModel;

@SuppressWarnings("unused")
public class AddAddressResponseModel {

    @SerializedName("data")
    private AddAddressDataModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public AddAddressDataModel getData() {
        return mData;
    }

    public void setData(AddAddressDataModel data) {
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
