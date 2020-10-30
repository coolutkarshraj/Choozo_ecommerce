package com.io.choozo.model.dataModel.todayDealsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Size{

	@SerializedName("sizeId")
	private int sizeId;

	@SerializedName("storeType")
	private int storeType;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("storeCategoryId")
	private List<Integer> storeCategoryId;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("categoryId")
	private int categoryId;

	public int getSizeId(){
		return sizeId;
	}

	public int getStoreType(){
		return storeType;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public List<Integer> getStoreCategoryId(){
		return storeCategoryId;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getCategoryId(){
		return categoryId;
	}
}