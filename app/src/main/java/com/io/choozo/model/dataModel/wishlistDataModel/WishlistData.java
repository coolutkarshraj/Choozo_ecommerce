package com.io.choozo.model.dataModel.wishlistDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistData {

    @SerializedName("wishlistProductId")
    @Expose
    private Integer wishlistProductId;
    @SerializedName("product")
    @Expose
    private WishlistProductData product;
    @SerializedName("productImage")
    @Expose
    private WishlistProductData productImage;

    public Integer getWishlistProductId() {
        return wishlistProductId;
    }

    public void setWishlistProductId(Integer wishlistProductId) {
        this.wishlistProductId = wishlistProductId;
    }

    public WishlistProductData getProduct() {
        return product;
    }

    public void setProduct(WishlistProductData product) {
        this.product = product;
    }

    public WishlistProductData getProductImage() {
        return productImage;
    }

    public void setProductImage(WishlistProductData productImage) {
        this.productImage = productImage;
    }
}
