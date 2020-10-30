
package com.io.choozo.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.responseModel.CustomerRegistrationDataModel;

public class CustomerRegisterResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private CustomerRegistrationDataModel data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerRegistrationDataModel getData() {
        return data;
    }

    public void setData(CustomerRegistrationDataModel data) {
        this.data = data;
    }

}
