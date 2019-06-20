
package com.io.choozo.model.dataModel.productdataModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Category {

    @SerializedName("categoryId")
    private Long mCategoryId;
    @SerializedName("categoryName")
    private String mCategoryName;
    @SerializedName("productId")
    private Long mProductId;

    public Long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(Long categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

}
