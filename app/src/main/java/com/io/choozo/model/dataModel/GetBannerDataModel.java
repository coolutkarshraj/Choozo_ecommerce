package com.io.choozo.model.dataModel;

import com.google.gson.annotations.SerializedName;

public class GetBannerDataModel {
    @SerializedName("bannerId")
    private Long mBannerId;
    @SerializedName("content")
    private String mContent;
    @SerializedName("image")
    private String mImage;
    @SerializedName("imagePath")
    private String mImagePath;
    @SerializedName("link")
    private String mLink;
    @SerializedName("position")
    private Long mPosition;
    @SerializedName("title")
    private String mTitle;

    public Long getBannerId() {
        return mBannerId;
    }

    public void setBannerId(Long bannerId) {
        mBannerId = bannerId;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public Long getPosition() {
        return mPosition;
    }

    public void setPosition(Long position) {
        mPosition = position;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
