package com.io.choozo.model.dataModel.SearchResponseDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchProductImages {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("containerName")
    @Expose
    private String containerName;
    @SerializedName("defaultImage")
    @Expose
    private Integer defaultImage;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public Integer getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Integer defaultImage) {
        this.defaultImage = defaultImage;
    }

}
