package com.io.choozo.model.dataModel.SearchResponseDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchProductData {

    @SerializedName("maximumProductPrice")
    @Expose
    private String maximumProductPrice;
    @SerializedName("productList")
    @Expose
    private List<SearchProductList> productList = null;

    public String getMaximumProductPrice() {
        return maximumProductPrice;
    }

    public void setMaximumProductPrice(String maximumProductPrice) {
        this.maximumProductPrice = maximumProductPrice;
    }

    public List<SearchProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<SearchProductList> productList) {
        this.productList = productList;
    }
}
