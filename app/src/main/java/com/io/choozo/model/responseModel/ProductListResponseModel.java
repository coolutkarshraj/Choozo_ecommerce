
package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.Data;

@SuppressWarnings("unused")
public class ProductListResponseModel {

    @SerializedName("data")
    private Data mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
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
