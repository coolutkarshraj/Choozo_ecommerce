
package com.io.choozo.model.responseModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.Category;
import com.io.choozo.model.dataModel.Store;
import com.io.choozo.model.dataModel.productListDataModel.ProductList;

import java.util.List;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("arabicName")
    @Expose
    private String arabicName;
    @SerializedName("featuresAndDetails")
    @Expose
    private Object featuresAndDetails;
    @SerializedName("importantInformation")
    @Expose
    private Object importantInformation;
    @SerializedName("arabicDescription")
    @Expose
    private String arabicDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("gstPrice")
    @Expose
    private Integer gstPrice;
    @SerializedName("otherCharge")
    @Expose
    private String otherCharge;
    @SerializedName("offerName")
    @Expose
    private Object offerName;
    @SerializedName("offerPercentage")
    @Expose
    private String offerPercentage;
    @SerializedName("offerExpiryDate")
    @Expose
    private Object offerExpiryDate;
    @SerializedName("minQuantity")
    @Expose
    private Integer minQuantity;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("storeCategoryId")
    @Expose
    private Integer storeCategoryId;
    @SerializedName("storeSubCategoryId")
    @Expose
    private Object storeSubCategoryId;
    @SerializedName("storeId")
    @Expose
    private Integer storeId;
    @SerializedName("auctionStoreId")
    @Expose
    private Object auctionStoreId;
    @SerializedName("userId")
    @Expose
    private Object userId;
    @SerializedName("isFeatured")
    @Expose
    private Object isFeatured;
    @SerializedName("isTodayDealProduct")
    @Expose
    private Object isTodayDealProduct;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("isAuctionProduct")
    @Expose
    private Boolean isAuctionProduct;
    @SerializedName("startDate")
    @Expose
    private Object startDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("avatarPath")
    @Expose
    private String avatarPath;
    @SerializedName("avatarName")
    @Expose
    private String avatarName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("color")
    @Expose
    private List<Object> color = null;
    @SerializedName("size")
    @Expose
    private List<Object> size = null;
    @SerializedName("units")
    @Expose
    private List<Object> units = null;
    @SerializedName("subCategoryId")
    @Expose
    private Object subCategoryId;
    @SerializedName("store")
    @Expose
    private Store store;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("storeCategory")
    @Expose
    private StoreCategory storeCategory;
    @SerializedName("storeSubCategory")
    @Expose
    private Object storeSubCategory;
    @SerializedName("posters")
    @Expose
    private List<Poster> posters = null;
    @SerializedName("colors")
    @Expose
    private List<Object> colors = null;
    @SerializedName("sizes")
    @Expose
    private List<Object> sizes = null;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("isWishlist")
    @Expose
    private Boolean isWishlist;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public Object getFeaturesAndDetails() {
        return featuresAndDetails;
    }

    public void setFeaturesAndDetails(Object featuresAndDetails) {
        this.featuresAndDetails = featuresAndDetails;
    }

    public Object getImportantInformation() {
        return importantInformation;
    }

    public void setImportantInformation(Object importantInformation) {
        this.importantInformation = importantInformation;
    }

    public String getArabicDescription() {
        return arabicDescription;
    }

    public void setArabicDescription(String arabicDescription) {
        this.arabicDescription = arabicDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getGstPrice() {
        return gstPrice;
    }

    public void setGstPrice(Integer gstPrice) {
        this.gstPrice = gstPrice;
    }

    public String getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(String otherCharge) {
        this.otherCharge = otherCharge;
    }

    public Object getOfferName() {
        return offerName;
    }

    public void setOfferName(Object offerName) {
        this.offerName = offerName;
    }

    public String getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(String offerPercentage) {
        this.offerPercentage = offerPercentage;
    }

    public Object getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(Object offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public Object getStoreSubCategoryId() {
        return storeSubCategoryId;
    }

    public void setStoreSubCategoryId(Object storeSubCategoryId) {
        this.storeSubCategoryId = storeSubCategoryId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Object getAuctionStoreId() {
        return auctionStoreId;
    }

    public void setAuctionStoreId(Object auctionStoreId) {
        this.auctionStoreId = auctionStoreId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Object isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Object getIsTodayDealProduct() {
        return isTodayDealProduct;
    }

    public void setIsTodayDealProduct(Object isTodayDealProduct) {
        this.isTodayDealProduct = isTodayDealProduct;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsAuctionProduct() {
        return isAuctionProduct;
    }

    public void setIsAuctionProduct(Boolean isAuctionProduct) {
        this.isAuctionProduct = isAuctionProduct;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Object> getColor() {
        return color;
    }

    public void setColor(List<Object> color) {
        this.color = color;
    }

    public List<Object> getSize() {
        return size;
    }

    public void setSize(List<Object> size) {
        this.size = size;
    }

    public List<Object> getUnits() {
        return units;
    }

    public void setUnits(List<Object> units) {
        this.units = units;
    }

    public Object getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Object subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public StoreCategory getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(StoreCategory storeCategory) {
        this.storeCategory = storeCategory;
    }

    public Object getStoreSubCategory() {
        return storeSubCategory;
    }

    public void setStoreSubCategory(Object storeSubCategory) {
        this.storeSubCategory = storeSubCategory;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

    public List<Object> getColors() {
        return colors;
    }

    public void setColors(List<Object> colors) {
        this.colors = colors;
    }

    public List<Object> getSizes() {
        return sizes;
    }

    public void setSizes(List<Object> sizes) {
        this.sizes = sizes;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public Boolean getIsWishlist() {
        return isWishlist;
    }

    public void setIsWishlist(Boolean isWishlist) {
        this.isWishlist = isWishlist;
    }


}
