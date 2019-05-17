package com.io.choozo.model.dataModel;

public class SubChildDataModel {

    private Integer categoryId;

    private String name;

    private String image;

    private Object imagePath;

    private Integer parentInt;

    private Integer sortOrder;

    private String metaTagTitle;

    private String metaTagDescription;

    private String metaTagKeyword;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getParentInt() {
        return parentInt;
    }

    public void setParentInt(Integer parentInt) {
        this.parentInt = parentInt;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeyword() {
        return metaTagKeyword;
    }

    public void setMetaTagKeyword(String metaTagKeyword) {
        this.metaTagKeyword = metaTagKeyword;
    }


}
