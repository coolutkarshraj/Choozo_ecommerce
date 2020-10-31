package com.io.choozo.model.responseModel.featured;

import com.google.gson.annotations.SerializedName;

public class Store{

	@SerializedName("description")
	private String description;

	@SerializedName("isOtpVerify")
	private Object isOtpVerify;

	@SerializedName("isActive")
	private boolean isActive;

	@SerializedName("addressId")
	private int addressId;

	@SerializedName("arabicName")
	private String arabicName;

	@SerializedName("availableForOnlineSale")
	private boolean availableForOnlineSale;

	@SerializedName("offerPercentage")
	private String offerPercentage;

	@SerializedName("lastLogged")
	private Object lastLogged;

	@SerializedName("phoneCode")
	private String phoneCode;

	@SerializedName("addedByUserId")
	private int addedByUserId;

	@SerializedName("arabicDescription")
	private String arabicDescription;

	@SerializedName("isApproved")
	private boolean isApproved;

	@SerializedName("availableForDelivery")
	private boolean availableForDelivery;

	@SerializedName("email")
	private String email;

	@SerializedName("offerName")
	private String offerName;

	@SerializedName("avatarPath")
	private String avatarPath;

	@SerializedName("otp")
	private Object otp;

	@SerializedName("deliveryPrice")
	private String deliveryPrice;

	@SerializedName("storeId")
	private int storeId;

	@SerializedName("avatarName")
	private String avatarName;

	@SerializedName("registrationToken")
	private String registrationToken;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("phone")
	private String phone;

	@SerializedName("registrationNumber")
	private String registrationNumber;

	@SerializedName("name")
	private String name;

	@SerializedName("offerExpiryDate")
	private String offerExpiryDate;

	@SerializedName("isPremium")
	private boolean isPremium;

	@SerializedName("categoryId")
	private int categoryId;

	@SerializedName("addedById")
	private int addedById;

	public String getDescription(){
		return description;
	}

	public Object getIsOtpVerify(){
		return isOtpVerify;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public int getAddressId(){
		return addressId;
	}

	public String getArabicName(){
		return arabicName;
	}

	public boolean isAvailableForOnlineSale(){
		return availableForOnlineSale;
	}

	public String getOfferPercentage(){
		return offerPercentage;
	}

	public Object getLastLogged(){
		return lastLogged;
	}

	public String getPhoneCode(){
		return phoneCode;
	}

	public int getAddedByUserId(){
		return addedByUserId;
	}

	public String getArabicDescription(){
		return arabicDescription;
	}

	public boolean isIsApproved(){
		return isApproved;
	}

	public boolean isAvailableForDelivery(){
		return availableForDelivery;
	}

	public String getEmail(){
		return email;
	}

	public String getOfferName(){
		return offerName;
	}

	public String getAvatarPath(){
		return avatarPath;
	}

	public Object getOtp(){
		return otp;
	}

	public String getDeliveryPrice(){
		return deliveryPrice;
	}

	public int getStoreId(){
		return storeId;
	}

	public String getAvatarName(){
		return avatarName;
	}

	public String getRegistrationToken(){
		return registrationToken;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getPhone(){
		return phone;
	}

	public String getRegistrationNumber(){
		return registrationNumber;
	}

	public String getName(){
		return name;
	}

	public String getOfferExpiryDate(){
		return offerExpiryDate;
	}

	public boolean isIsPremium(){
		return isPremium;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public int getAddedById(){
		return addedById;
	}
}