
package com.io.choozo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class CustomerRegistrationDataModel {


    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("avatarPath")
    @Expose
    private Object avatarPath;
    @SerializedName("avatarName")
    @Expose
    private Object avatarName;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("dateOfBirth")
    @Expose
    private Object dateOfBirth;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("isOtpVerify")
    @Expose
    private Object isOtpVerify;
    @SerializedName("lastLogged")
    @Expose
    private Object lastLogged;
    @SerializedName("requestRejectReason")
    @Expose
    private Object requestRejectReason;
    @SerializedName("otp")
    @Expose
    private Object otp;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("permissions")
    @Expose
    private List<Integer> permissions = null;
    @SerializedName("userAccountType")
    @Expose
    private String userAccountType;
    @SerializedName("isBusinessRequest")
    @Expose
    private Integer isBusinessRequest;
    @SerializedName("registrationToken")
    @Expose
    private String registrationToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(Object avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Object getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(Object avatarName) {
        this.avatarName = avatarName;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Object getIsOtpVerify() {
        return isOtpVerify;
    }

    public void setIsOtpVerify(Object isOtpVerify) {
        this.isOtpVerify = isOtpVerify;
    }

    public Object getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(Object lastLogged) {
        this.lastLogged = lastLogged;
    }

    public Object getRequestRejectReason() {
        return requestRejectReason;
    }

    public void setRequestRejectReason(Object requestRejectReason) {
        this.requestRejectReason = requestRejectReason;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }
}
