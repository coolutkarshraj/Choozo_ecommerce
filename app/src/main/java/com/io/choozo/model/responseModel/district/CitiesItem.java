package com.io.choozo.model.responseModel.district;

import com.google.gson.annotations.SerializedName;

public class CitiesItem{

	@SerializedName("arabicName")
	private String arabicName;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("districtId")
	private int districtId;

	@SerializedName("modifiedDate")
	private Object modifiedDate;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Object id;

	@SerializedName("cityId")
	private int cityId;

	public String getArabicName(){
		return arabicName;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public int getDistrictId(){
		return districtId;
	}

	public Object getModifiedDate(){
		return modifiedDate;
	}

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}

	public int getCityId(){
		return cityId;
	}
}