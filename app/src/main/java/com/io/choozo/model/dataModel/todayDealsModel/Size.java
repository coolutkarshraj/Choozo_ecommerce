package com.io.choozo.model.dataModel.todayDealsModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Size{

	@SerializedName("createdDate")
	@Expose
	private String createdDate;
	@SerializedName("sizeId")
	@Expose
	private Integer sizeId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("categoryId")
	@Expose
	private Integer categoryId;
	@SerializedName("storeType")
	@Expose
	private Integer storeType;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("storeCategoryId")
	@Expose
	private List<Integer> storeCategoryId = null;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Integer> getStoreCategoryId() {
		return storeCategoryId;
	}

	public void setStoreCategoryId(List<Integer> storeCategoryId) {
		this.storeCategoryId = storeCategoryId;
	}
}