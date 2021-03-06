package com.io.choozo.model.responseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.todayDealsModel.TodayDataModel;

public class TodayDealsResponseModel{

	@SerializedName("data")
	private List<TodayDataModel> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<TodayDataModel> getData(){
		return data;
	}
	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}