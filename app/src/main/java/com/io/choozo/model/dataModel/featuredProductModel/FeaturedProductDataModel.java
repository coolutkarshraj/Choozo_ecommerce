package com.io.choozo.model.dataModel.featuredProductModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturedProductDataModel {

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
    @SerializedName("metaTagTitle")
    @Expose
    private String metaTagTitle;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("Images")
    @Expose
    private FeaturedProductImages images;

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

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public FeaturedProductImages getImages() {
        return images;
    }

    public void setImages(FeaturedProductImages images) {
        this.images = images;
    }

}
