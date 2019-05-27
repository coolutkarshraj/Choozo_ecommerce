
package com.io.choozo.model.responseModel;


import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.GetProfileDataModel;

@SuppressWarnings("unused")
public class GetProfileResponseModel {

    @SerializedName("data")
    private GetProfileDataModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public GetProfileDataModel getData() {
        return mData;
    }

    public void setData(GetProfileDataModel data) {
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
