package com.io.choozo.model.responseModel.district;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetDistrictResponseModel{

	@SerializedName("data")
	private List<GetDistrictDataModel> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<GetDistrictDataModel> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}