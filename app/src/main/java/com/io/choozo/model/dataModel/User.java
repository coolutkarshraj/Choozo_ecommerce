
package com.io.choozo.model.dataModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class User {

    @SerializedName("avatar")
    private Object mAvatar;
    @SerializedName("avatarPath")
    private Object mAvatarPath;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;

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

}
