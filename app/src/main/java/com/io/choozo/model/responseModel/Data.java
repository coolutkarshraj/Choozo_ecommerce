
package com.io.choozo.model.responseModel;


import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.productListDataModel.ProductList;

import java.util.List;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("maximumProductPrice")
    private String mMaximumProductPrice;
    @SerializedName("productList")
    private List<ProductList> mProductList;

    public String getMaximumProductPrice() {
        return mMaximumProductPrice;
    }

    public void setMaximumProductPrice(String maximumProductPrice) {
        mMaximumProductPrice = maximumProductPrice;
    }

    public List<ProductList> getProductList() {
        return mProductList;
    }

    public void setProductList(List<ProductList> productList) {
        mProductList = productList;
    }

}
