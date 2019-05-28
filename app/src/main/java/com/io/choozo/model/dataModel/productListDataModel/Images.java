
package com.io.choozo.model.dataModel.productListDataModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Images {

    @SerializedName("containerName")
    private String mContainerName;
    @SerializedName("defaultImage")
    private Long mDefaultImage;
    @SerializedName("image")
    private String mImage;
    @SerializedName("productId")
    private Long mProductId;

    public String getContainerName() {
        return mContainerName;
    }

    public void setContainerName(String containerName) {
        mContainerName = containerName;
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

}
