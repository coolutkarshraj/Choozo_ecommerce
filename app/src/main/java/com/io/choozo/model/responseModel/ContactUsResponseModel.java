
package com.io.choozo.model.responseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.ContactUsDataModel;

@SuppressWarnings("unused")
public class ContactUsResponseModel {

    @SerializedName("data")
    private List<ContactUsDataModel> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public List<ContactUsDataModel> getData() {
        return mData;
    }

    public void setData(List<ContactUsDataModel> data) {
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
