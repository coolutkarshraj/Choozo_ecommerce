
package com.io.choozo.model.responseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.GetPageListDataModel;

@SuppressWarnings("unused")
public class GetPageListResponseModel {

    @SerializedName("data")
    private List<GetPageListDataModel> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public List<GetPageListDataModel> getData() {
        return mData;
    }

    public void setData(List<GetPageListDataModel> data) {
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
