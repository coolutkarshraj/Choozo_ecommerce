package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;

public class DeliveryAddressesItem{

	@SerializedName("country")
	private String country;

	@SerializedName("streetTwo")
	private String streetTwo;

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("addressType")
	private String addressType;

	@SerializedName("locality")
	private String locality;

	@SerializedName("streetOne")
	private String streetOne;

	@SerializedName("phoneNum")
	private String phoneNum;

	@SerializedName("userId")
	private int userId;

	@SerializedName("addressId")
	private int addressId;

	@SerializedName("zipcode")
	private String zipcode;

	@SerializedName("isDefault")
	private int isDefault;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("district")
	private String district;

	@SerializedName("name")
	private String name;

	@SerializedName("state")
	private String state;

	@SerializedName("landmark")
	private String landmark;

	@SerializedName("guestId")
	private Object guestId;

	public String getCountry(){
		return country;
	}

	public String getStreetTwo(){
		return streetTwo;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getAddressType(){
		return addressType;
	}

	public String getLocality(){
		return locality;
	}

	public String getStreetOne(){
		return streetOne;
	}

	public String getPhoneNum(){
		return phoneNum;
	}

	public int getUserId(){
		return userId;
	}

	public int getAddressId(){
		return addressId;
	}

	public String getZipcode(){
		return zipcode;
	}

	public int getIsDefault(){
		return isDefault;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getDistrict(){
		return district;
	}

	public String getName(){
		return name;
	}

	public String getState(){
		return state;
	}

	public String getLandmark(){
		return landmark;
	}

	public Object getGuestId(){
		return guestId;
	}
}