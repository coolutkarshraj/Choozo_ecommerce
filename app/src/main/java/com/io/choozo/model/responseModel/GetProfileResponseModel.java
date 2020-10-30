package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.GetProfileDataModel;

public class GetProfileResponseModel{

	@SerializedName("data")
	private GetProfileDataModel data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public GetProfileDataModel getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}