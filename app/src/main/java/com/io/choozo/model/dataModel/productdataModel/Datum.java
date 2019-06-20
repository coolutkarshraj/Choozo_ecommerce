
package com.io.choozo.model.dataModel.productdataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;



@SuppressWarnings("unused")
public class Datum {

    @SerializedName("Category")
    private List<Category> mCategory;
    @SerializedName("condition")
    private Long mCondition;
    @SerializedName("dateAvailable")
    private String mDateAvailable;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("isActive")
    private Long mIsActive;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("manufacturerId")
    private Long mManufacturerId;
    @SerializedName("metaTagTitle")
    private String mMetaTagTitle;
    @SerializedName("minimumQuantity")
    private Long mMinimumQuantity;
    @SerializedName("name")
    private String mName;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("pricerefer")
    private String mPricerefer;
    @SerializedName("productId")
    private Long mProductId;
    @SerializedName("productImage")
    private List<ProductImage> mProductImage;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("rating")
    private Long mRating;
    @SerializedName("relatedProductDetail")
    private List<RelatedProductDetail> mRelatedProductDetail;
    @SerializedName("shipping")
    private Long mShipping;
    @SerializedName("sku")
    private String mSku;
    @SerializedName("sortOrder")
    private Long mSortOrder;
    @SerializedName("stockStatusId")
    private Long mStockStatusId;
    @SerializedName("subtractStock")
    private Long mSubtractStock;
    @SerializedName("upc")
    private String mUpc;
    @SerializedName("wishListStatus")
    private Long mWishListStatus;

    public List<Category> getCategory() {
        return mCategory;
    }

    public void setCategory(List<Category> category) {
        mCategory = category;
    }

    public Long getCondition() {
        return mCondition;
    }

    public void setCondition(Long condition) {
        mCondition = condition;
    }

    public String getDateAvailable() {
        return mDateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        mDateAvailable = dateAvailable;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getFlag() {
        return mFlag;
    }

    public void setFlag(String flag) {
        mFlag = flag;
    }

    public Long getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Long isActive) {
        mIsActive = isActive;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public Long getManufacturerId() {
        return mManufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        mManufacturerId = manufacturerId;
    }

    public String getMetaTagTitle() {
        return mMetaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        mMetaTagTitle = metaTagTitle;
    }

    public Long getMinimumQuantity() {
        return mMinimumQuantity;
    }

    public void setMinimumQuantity(Long minimumQuantity) {
        mMinimumQuantity = minimumQuantity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getPricerefer() {
        return mPricerefer;
    }

    public void setPricerefer(String pricerefer) {
        mPricerefer = pricerefer;
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

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public Long getRating() {
        return mRating;
    }

    public void setRating(Long rating) {
        mRating = rating;
    }

    public List<RelatedProductDetail> getRelatedProductDetail() {
        return mRelatedProductDetail;
    }

    public void setRelatedProductDetail(List<RelatedProductDetail> relatedProductDetail) {
        mRelatedProductDetail = relatedProductDetail;
    }

    public Long getShipping() {
        return mShipping;
    }

    public void setShipping(Long shipping) {
        mShipping = shipping;
    }

    public String getSku() {
        return mSku;
    }

    public void setSku(String sku) {
        mSku = sku;
    }

    public Long getSortOrder() {
        return mSortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        mSortOrder = sortOrder;
    }

    public Long getStockStatusId() {
        return mStockStatusId;
    }

    public void setStockStatusId(Long stockStatusId) {
        mStockStatusId = stockStatusId;
    }

    public Long getSubtractStock() {
        return mSubtractStock;
    }

    public void setSubtractStock(Long subtractStock) {
        mSubtractStock = subtractStock;
    }

    public String getUpc() {
        return mUpc;
    }

    public void setUpc(String upc) {
        mUpc = upc;
    }

    public Long getWishListStatus() {
        return mWishListStatus;
    }

    public void setWishListStatus(Long wishListStatus) {
        mWishListStatus = wishListStatus;
    }

}
