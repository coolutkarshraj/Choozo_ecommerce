package com.io.choozo.model.dataModel.getWishlistDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistProductImage {


    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("modifiedBy")
    @Expose
    private Object modifiedBy;
    @SerializedName("modifiedDate")
    @Expose
    private Object modifiedDate;
    @SerializedName("productImageId")
    @Expose
    private Integer productImageId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("containerName")
    @Expose
    private String containerName;
    @SerializedName("sortOrder")
    @Expose
    private Object sortOrder;
    @SerializedName("defaultImage")
    @Expose
    private Integer defaultImage;
    @SerializedName("isActive")
    @Expose
    private Object isActive;

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Object getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

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

    public Object getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Object sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Integer defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }
}
