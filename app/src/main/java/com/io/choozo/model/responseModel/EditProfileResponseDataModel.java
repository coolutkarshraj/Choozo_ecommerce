package com.io.choozo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditProfileResponseDataModel {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("avatarPath")
    @Expose
    private String avatarPath;
    @SerializedName("avatarName")
    @Expose
    private String avatarName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("isOtpVerify")
    @Expose
    private String isOtpVerify;
    @SerializedName("lastLogged")
    @Expose
    private String lastLogged;
    @SerializedName("permissions")
    @Expose
    private List<Integer> permissions = null;
    @SerializedName("userAccountType")
    @Expose
    private String userAccountType;
    @SerializedName("isBusinessRequest")
    @Expose
    private Integer isBusinessRequest;
    @SerializedName("requestRejectReason")
    @Expose
    private Object requestRejectReason;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("registrationToken")
    @Expose
    private String registrationToken;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getIsOtpVerify() {
        return isOtpVerify;
    }

    public void setIsOtpVerify(String isOtpVerify) {
        this.isOtpVerify = isOtpVerify;
    }

    public String getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(String lastLogged) {
        this.lastLogged = lastLogged;
    }

    public List<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Integer> permissions) {
        this.permissions = permissions;
    }

    public String getUserAccountType() {
        return userAccountType;
    }

    public void setUserAccountType(String userAccountType) {
        this.userAccountType = userAccountType;
    }

    public Integer getIsBusinessRequest() {
        return isBusinessRequest;
    }

    public void setIsBusinessRequest(Integer isBusinessRequest) {
        this.isBusinessRequest = isBusinessRequest;
    }

    public Object getRequestRejectReason() {
        return requestRejectReason;
    }

    public void setRequestRejectReason(Object requestRejectReason) {
        this.requestRejectReason = requestRejectReason;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

}
