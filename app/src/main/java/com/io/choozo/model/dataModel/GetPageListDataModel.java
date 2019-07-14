
package com.io.choozo.model.dataModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetPageListDataModel {

    @SerializedName("content")
    private String mContent;
    @SerializedName("isActive")
    private Long mIsActive;
    @SerializedName("metaTagContent")
    private String mMetaTagContent;
    @SerializedName("metaTagKeyword")
    private String mMetaTagKeyword;
    @SerializedName("metaTagTitle")
    private String mMetaTagTitle;
    @SerializedName("pageId")
    private Long mPageId;
    @SerializedName("title")
    private String mTitle;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Long getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Long isActive) {
        mIsActive = isActive;
    }

    public String getMetaTagContent() {
        return mMetaTagContent;
    }

    public void setMetaTagContent(String metaTagContent) {
        mMetaTagContent = metaTagContent;
    }

    public String getMetaTagKeyword() {
        return mMetaTagKeyword;
    }

    public void setMetaTagKeyword(String metaTagKeyword) {
        mMetaTagKeyword = metaTagKeyword;
    }

    public String getMetaTagTitle() {
        return mMetaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        mMetaTagTitle = metaTagTitle;
    }

    public Long getPageId() {
        return mPageId;
    }

    public void setPageId(Long pageId) {
        mPageId = pageId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
