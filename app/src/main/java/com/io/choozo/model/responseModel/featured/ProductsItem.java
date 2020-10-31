package com.io.choozo.model.responseModel.featured;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("color")
	private List<Object> color;

	@SerializedName("endDate")
	private Object endDate;

	@SerializedName("isAuctionProduct")
	private boolean isAuctionProduct;

	@SerializedName("description")
	private String description;

	@SerializedName("units")
	private List<Object> units;

	@SerializedName("isActive")
	private boolean isActive;

	@SerializedName("type")
	private String type;

	@SerializedName("colors")
	private List<Object> colors;

	@SerializedName("arabicName")
	private String arabicName;

	@SerializedName("minQuantity")
	private int minQuantity;

	@SerializedName("sizes")
	private List<Object> sizes;

	@SerializedName("offerPercentage")
	private String offerPercentage;

	@SerializedName("price")
	private String price;

	@SerializedName("arabicDescription")
	private String arabicDescription;

	@SerializedName("sku")
	private String sku;

	@SerializedName("isFeatured")
	private Object isFeatured;

	@SerializedName("brand")
	private String brand;

	@SerializedName("offerName")
	private Object offerName;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("avatarPath")
	private Object avatarPath;

	@SerializedName("posters")
	private List<PostersItem> posters;

	@SerializedName("subCategoryId")
	private Object subCategoryId;

	@SerializedName("store")
	private Store store;

	@SerializedName("vendorName")
	private String vendorName;

	@SerializedName("storeId")
	private int storeId;

	@SerializedName("auctionStoreId")
	private Object auctionStoreId;

	@SerializedName("userId")
	private Object userId;

	@SerializedName("avatarName")
	private Object avatarName;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("otherCharge")
	private String otherCharge;

	@SerializedName("size")
	private List<Object> size;

	@SerializedName("isWishlist")
	private boolean isWishlist;

	@SerializedName("gstPrice")
	private int gstPrice;

	@SerializedName("storeCategoryId")
	private int storeCategoryId;

	@SerializedName("importantInformation")
	private Object importantInformation;

	@SerializedName("name")
	private String name;

	@SerializedName("featuresAndDetails")
	private Object featuresAndDetails;

	@SerializedName("offerExpiryDate")
	private Object offerExpiryDate;

	@SerializedName("attributes")
	private List<AttributesItem> attributes;

	@SerializedName("isTodayDealProduct")
	private Object isTodayDealProduct;

	@SerializedName("categoryId")
	private int categoryId;

	@SerializedName("startDate")
	private Object startDate;

	@SerializedName("storeSubCategoryId")
	private int storeSubCategoryId;

	public List<Object> getColor(){
		return color;
	}

	public Object getEndDate(){
		return endDate;
	}

	public boolean isIsAuctionProduct(){
		return isAuctionProduct;
	}

	public String getDescription(){
		return description;
	}

	public List<Object> getUnits(){
		return units;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public String getType(){
		return type;
	}

	public List<Object> getColors(){
		return colors;
	}

	public String getArabicName(){
		return arabicName;
	}

	public int getMinQuantity(){
		return minQuantity;
	}

	public List<Object> getSizes(){
		return sizes;
	}

	public String getOfferPercentage(){
		return offerPercentage;
	}

	public String getPrice(){
		return price;
	}

	public String getArabicDescription(){
		return arabicDescription;
	}

	public String getSku(){
		return sku;
	}

	public Object getIsFeatured(){
		return isFeatured;
	}

	public String getBrand(){
		return brand;
	}

	public Object getOfferName(){
		return offerName;
	}

	public int getQuantity(){
		return quantity;
	}

	public int getProductId(){
		return productId;
	}

	public Object getAvatarPath(){
		return avatarPath;
	}

	public List<PostersItem> getPosters(){
		return posters;
	}

	public Object getSubCategoryId(){
		return subCategoryId;
	}

	public Store getStore(){
		return store;
	}

	public String getVendorName(){
		return vendorName;
	}

	public int getStoreId(){
		return storeId;
	}

	public Object getAuctionStoreId(){
		return auctionStoreId;
	}

	public Object getUserId(){
		return userId;
	}

	public Object getAvatarName(){
		return avatarName;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getOtherCharge(){
		return otherCharge;
	}

	public List<Object> getSize(){
		return size;
	}

	public boolean isIsWishlist(){
		return isWishlist;
	}

	public int getGstPrice(){
		return gstPrice;
	}

	public int getStoreCategoryId(){
		return storeCategoryId;
	}

	public Object getImportantInformation(){
		return importantInformation;
	}

	public String getName(){
		return name;
	}

	public Object getFeaturesAndDetails(){
		return featuresAndDetails;
	}

	public Object getOfferExpiryDate(){
		return offerExpiryDate;
	}

	public List<AttributesItem> getAttributes(){
		return attributes;
	}

	public Object getIsTodayDealProduct(){
		return isTodayDealProduct;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public Object getStartDate(){
		return startDate;
	}

	public int getStoreSubCategoryId(){
		return storeSubCategoryId;
	}
}