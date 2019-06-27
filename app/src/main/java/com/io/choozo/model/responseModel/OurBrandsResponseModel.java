package com.io.choozo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.OurBrandsDataModel;

import java.util.List;

public class OurBrandsResponseModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<OurBrandsDataModel> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OurBrandsDataModel> getData() {
        return data;
    }

    public void setData(List<OurBrandsDataModel> data) {
        this.data = data;
    }

}
