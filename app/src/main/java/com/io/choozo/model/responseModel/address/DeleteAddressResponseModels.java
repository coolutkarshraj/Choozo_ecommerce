package com.io.choozo.model.responseModel.address;

import com.google.gson.annotations.SerializedName;

public class DeleteAddressResponseModels{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}