
package com.io.choozo.model.dataModel.productListDataModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ProductList {

    @SerializedName("amount")
    private Object mAmount;
    @SerializedName("Category")
    private List<Category> mCategory;
    @SerializedName("condition")
    private Long mCondition;
    @SerializedName("dateAvailable")
    private String mDateAvailable;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("discount")
    private Object mDiscount;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("Images")
    private Images mImages;
    @SerializedName("metaTagDescription")
    private Object mMetaTagDescription;
    @SerializedName("metaTagKeyword")
    private Object mMetaTagKeyword;
    @SerializedName("metaTagTitle")
    private String mMetaTagTitle;
    @SerializedName("name")
    private String mName;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("pricerefer")
    private String mPricerefer;
    @SerializedName("productId")
    private int mProductId;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("rating")
    private Object mRating;
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
    @SerializedName("wishListStatus")
    private Long mWishListStatus;

    public Object getAmount() {
        return mAmount;
    }

    public void setAmount(Object amount) {
        mAmount = amount;
    }

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

    public Object getDiscount() {
        return mDiscount;
    }

    public void setDiscount(Object discount) {
        mDiscount = discount;
    }

    public String getFlag() {
        return mFlag;
    }

    public void setFlag(String flag) {
        mFlag = flag;
    }

    public Images getImages() {
        return mImages;
    }

    public void setImages(Images images) {
        mImages = images;
    }

    public Object getMetaTagDescription() {
        return mMetaTagDescription;
    }

    public void setMetaTagDescription(Object metaTagDescription) {
        mMetaTagDescription = metaTagDescription;
    }

    public Object getMetaTagKeyword() {
        return mMetaTagKeyword;
    }

    public void setMetaTagKeyword(Object metaTagKeyword) {
        mMetaTagKeyword = metaTagKeyword;
    }

    public String getMetaTagTitle() {
        return mMetaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        mMetaTagTitle = metaTagTitle;
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

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public Object getRating() {
        return mRating;
    }

    public void setRating(Object rating) {
        mRating = rating;
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

    public Long getWishListStatus() {
        return mWishListStatus;
    }

    public void setWishListStatus(Long wishListStatus) {
        mWishListStatus = wishListStatus;
    }

}
