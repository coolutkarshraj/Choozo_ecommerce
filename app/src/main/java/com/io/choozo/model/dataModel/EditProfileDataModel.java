
package com.io.choozo.model.dataModel;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class EditProfileDataModel {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("avatar")
    private Object mAvatar;
    @SerializedName("avatarPath")
    private Object mAvatarPath;
    @SerializedName("countryId")
    private String mCountryId;
    @SerializedName("customerId")
    private Long mCustomerId;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("username")
    private String mUsername;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Object getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Object avatar) {
        mAvatar = avatar;
    }

    public Object getAvatarPath() {
        return mAvatarPath;
    }

    public void setAvatarPath(Object avatarPath) {
        mAvatarPath = avatarPath;
    }

    public String getCountryId() {
        return mCountryId;
    }

    public void setCountryId(String countryId) {
        mCountryId = countryId;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
