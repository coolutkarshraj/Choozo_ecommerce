package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponseModel{

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