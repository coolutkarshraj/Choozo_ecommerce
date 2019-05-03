package com.io.choozo.model.dummydataModel;

public class ShopCategory {

    String categoryImageUrl;
    String  categoryName;

    public ShopCategory(String categoryImageUrl, String categoryName) {
        this.categoryImageUrl = categoryImageUrl;
        this.categoryName = categoryName;
    }

    public ShopCategory(){

    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
