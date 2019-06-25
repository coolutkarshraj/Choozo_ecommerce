package com.io.choozo.model.dataModel.getWishlistDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistProductDetailDataModel {
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
    private String modifiedDate;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("minimumQuantity")
    @Expose
    private Integer minimumQuantity;
    @SerializedName("subtractStock")
    @Expose
    private Integer subtractStock;
    @SerializedName("stockStatusId")
    @Expose
    private Integer stockStatusId;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("imagePath")
    @Expose
    private Object imagePath;
    @SerializedName("manufacturerId")
    @Expose
    private Integer manufacturerId;
    @SerializedName("shipping")
    @Expose
    private Integer shipping;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("dateAvailable")
    @Expose
    private String dateAvailable;
    @SerializedName("sortOrder")
    @Expose
    private Integer sortOrder;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private Object amount;
    @SerializedName("metaTagTitle")
    @Expose
    private String metaTagTitle;
    @SerializedName("metaTagDescription")
    @Expose
    private Object metaTagDescription;
    @SerializedName("metaTagKeyword")
    @Expose
    private Object metaTagKeyword;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("deleteFlag")
    @Expose
    private Integer deleteFlag;
    @SerializedName("isFeatured")
    @Expose
    private Integer isFeatured;
    @SerializedName("todayDeals")
    @Expose
    private Integer todayDeals;
    @SerializedName("condition")
    @Expose
    private Integer condition;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("wishListStatus")
    @Expose
    private Integer wishListStatus;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("pricerefer")
    @Expose
    private String pricerefer;
    @SerializedName("flag")
    @Expose
    private String flag;

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

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Integer getSubtractStock() {
        return subtractStock;
    }

    public void setSubtractStock(Integer subtractStock) {
        this.subtractStock = subtractStock;
    }

    public Integer getStockStatusId() {
        return stockStatusId;
    }

    public void setStockStatusId(Integer stockStatusId) {
        this.stockStatusId = stockStatusId;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public Object getMetaTagDescription() {
        return metaTagDescription;
    }

    public void setMetaTagDescription(Object metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public Object getMetaTagKeyword() {
        return metaTagKeyword;
    }

    public void setMetaTagKeyword(Object metaTagKeyword) {
        this.metaTagKeyword = metaTagKeyword;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getTodayDeals() {
        return todayDeals;
    }

    public void setTodayDeals(Integer todayDeals) {
        this.todayDeals = todayDeals;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Integer getWishListStatus() {
        return wishListStatus;
    }

    public void setWishListStatus(Integer wishListStatus) {
        this.wishListStatus = wishListStatus;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getPricerefer() {
        return pricerefer;
    }

    public void setPricerefer(String pricerefer) {
        this.pricerefer = pricerefer;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
