package com.io.choozo.model.responseModel.address;

import com.google.gson.annotations.SerializedName;

public class AdressResponseModel{

	@SerializedName("data")
	private int data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}