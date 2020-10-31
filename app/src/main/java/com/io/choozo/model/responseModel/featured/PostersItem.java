package com.io.choozo.model.responseModel.featured;

import com.google.gson.annotations.SerializedName;

public class PostersItem{

	@SerializedName("avatarName")
	private String avatarName;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("productId")
	private int productId;

	@SerializedName("avatarPath")
	private String avatarPath;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	@SerializedName("posterId")
	private int posterId;

	public String getAvatarName(){
		return avatarName;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public int getProductId(){
		return productId;
	}

	public String getAvatarPath(){
		return avatarPath;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}

	public int getPosterId(){
		return posterId;
	}
}