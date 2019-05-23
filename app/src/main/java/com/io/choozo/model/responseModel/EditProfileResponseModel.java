
package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.EditProfileDataModel;

@SuppressWarnings("unused")
public class EditProfileResponseModel {

    @SerializedName("data")
    private EditProfileDataModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public EditProfileDataModel getData() {
        return mData;
    }

    public void setData(EditProfileDataModel data) {
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
