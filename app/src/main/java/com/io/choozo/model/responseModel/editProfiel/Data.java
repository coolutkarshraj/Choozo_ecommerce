package com.io.choozo.model.responseModel.editProfiel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("isBusinessRequest")
	private int isBusinessRequest;

	@SerializedName("address")
	private Object address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("avatarPath")
	private String avatarPath;

	@SerializedName("bio")
	private String bio;

	@SerializedName("dateOfBirth")
	private String dateOfBirth;

	@SerializedName("otp")
	private Object otp;

	@SerializedName("isOtpVerify")
	private Object isOtpVerify;

	@SerializedName("isActive")
	private boolean isActive;

	@SerializedName("userId")
	private int userId;

	@SerializedName("avatarName")
	private String avatarName;

	@SerializedName("registrationToken")
	private String registrationToken;

	@SerializedName("requestRejectReason")
	private Object requestRejectReason;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("phone")
	private String phone;

	@SerializedName("permissions")
	private List<Integer> permissions;

	@SerializedName("name")
	private String name;

	@SerializedName("lastLogged")
	private String lastLogged;

	@SerializedName("userAccountType")
	private String userAccountType;

	@SerializedName("email")
	private String email;

	public int getIsBusinessRequest(){
		return isBusinessRequest;
	}

	public Object getAddress(){
		return address;
	}

	public String getGender(){
		return gender;
	}

	public String getAvatarPath(){
		return avatarPath;
	}

	public String getBio(){
		return bio;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
	}

	public Object getOtp(){
		return otp;
	}

	public Object getIsOtpVerify(){
		return isOtpVerify;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public int getUserId(){
		return userId;
	}

	public String getAvatarName(){
		return avatarName;
	}

	public String getRegistrationToken(){
		return registrationToken;
	}

	public Object getRequestRejectReason(){
		return requestRejectReason;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getPhone(){
		return phone;
	}

	public List<Integer> getPermissions(){
		return permissions;
	}

	public String getName(){
		return name;
	}

	public String getLastLogged(){
		return lastLogged;
	}

	public String getUserAccountType(){
		return userAccountType;
	}

	public String getEmail(){
		return email;
	}
}