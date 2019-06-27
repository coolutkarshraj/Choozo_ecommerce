package com.io.choozo.model.dataModel.getWishlistDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistProductDataModel {
    @SerializedName("wishlistProductId")
    @Expose
    private Integer wishlistProductId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("product")
    @Expose
    private WishlistProductDetailDataModel product;
    @SerializedName("productImage")
    @Expose
    private WishlistProductImage productImage;

    public Integer getWishlistProductId() {
        return wishlistProductId;
    }

    public void setWishlistProductId(Integer wishlistProductId) {
        this.wishlistProductId = wishlistProductId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public WishlistProductDetailDataModel getProduct() {
        return product;
    }

    public void setProduct(WishlistProductDetailDataModel product) {
        this.product = product;
    }

    public WishlistProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(WishlistProductImage productImage) {
        this.productImage = productImage;
    }
}
