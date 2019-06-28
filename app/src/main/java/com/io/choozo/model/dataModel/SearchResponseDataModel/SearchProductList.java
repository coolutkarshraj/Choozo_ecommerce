package com.io.choozo.model.dataModel.SearchResponseDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchProductList {

    @Expose
    private Integer productId;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("subtractStock")
    @Expose
    private Integer subtractStock;
    @SerializedName("stockStatusId")
    @Expose
    private Integer stockStatusId;
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
    @SerializedName("condition")
    @Expose
    private Integer condition;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("wishListStatus")
    @Expose
    private Integer wishListStatus;
    @SerializedName("Images")
    @Expose
    private SearchProductImages images;
    @SerializedName("Category")
    @Expose
    private List<SearchProductCategory> category = null;
    @SerializedName("pricerefer")
    @Expose
    private String pricerefer;
    @SerializedName("flag")
    @Expose
    private String flag;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public SearchProductImages getImages() {
        return images;
    }

    public void setImages(SearchProductImages images) {
        this.images = images;
    }

    public List<SearchProductCategory> getCategory() {
        return category;
    }

    public void setCategory(List<SearchProductCategory> category) {
        this.category = category;
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
