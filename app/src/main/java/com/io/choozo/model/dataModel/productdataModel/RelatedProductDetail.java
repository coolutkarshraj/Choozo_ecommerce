
package com.io.choozo.model.dataModel.productdataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;



@SuppressWarnings("unused")
public class RelatedProductDetail {

    @SerializedName("name")
    private String mName;
    @SerializedName("productId")
    private Long mProductId;
    @SerializedName("productImage")
    private List<ProductImage> mProductImage;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public List<ProductImage> getProductImage() {
        return mProductImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        mProductImage = productImage;
    }

}
