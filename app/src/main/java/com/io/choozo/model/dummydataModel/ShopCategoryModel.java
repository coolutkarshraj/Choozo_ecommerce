package com.io.choozo.model.dummydataModel;

public class ShopCategoryModel {

    Integer categoryImageUrl;
    String  categoryName;

    public ShopCategoryModel(Integer categoryImageUrl, String categoryName) {
        this.categoryImageUrl = categoryImageUrl;
        this.categoryName = categoryName;
    }

    public Integer getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(Integer categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
