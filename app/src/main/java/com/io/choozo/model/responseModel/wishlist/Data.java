package com.io.choozo.model.responseModel.wishlist;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("wishListId")
	private int wishListId;

	@SerializedName("productId")
	private int productId;

	@SerializedName("userId")
	private int userId;

	public int getWishListId(){
		return wishListId;
	}

	public int getProductId(){
		return productId;
	}

	public int getUserId(){
		return userId;
	}
}