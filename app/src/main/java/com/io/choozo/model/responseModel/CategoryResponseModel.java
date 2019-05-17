
package com.io.choozo.model.responseModel;

import com.io.choozo.model.dataModel.CategoryDataModel;

import java.util.List;



@SuppressWarnings("unused")
public class CategoryResponseModel {

    private Integer status;
    private String message;
    private List<CategoryDataModel> data = null;

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

    public List<CategoryDataModel> getData() {
        return data;
    }

    public void setData(List<CategoryDataModel> data) {
        this.data = data;
    }
}
