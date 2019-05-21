
package com.io.choozo.model.responseModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class CustomerRegistrationDataModel {

    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("ip")
    private String mIp;
    @SerializedName("username")
    private String mUsername;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
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

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
