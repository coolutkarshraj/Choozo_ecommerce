
package com.io.choozo.model.dataModel.productdataModel;

import com.google.gson.annotations.SerializedName;



@SuppressWarnings("unused")
public class ProductImage {

    @SerializedName("containerName")
    private String mContainerName;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("defaultImage")
    private Long mDefaultImage;
    @SerializedName("image")
    private String mImage;
    @SerializedName("productId")
    private Long mProductId;
    @SerializedName("productImageId")
    private Long mProductImageId;

    public String getContainerName() {
        return mContainerName;
    }

    public void setContainerName(String containerName) {
        mContainerName = containerName;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public Long getDefaultImage() {
        return mDefaultImage;
    }

    public void setDefaultImage(Long defaultImage) {
        mDefaultImage = defaultImage;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public Long getProductImageId() {
        return mProductImageId;
    }

    public void setProductImageId(Long productImageId) {
        mProductImageId = productImageId;
    }

}
