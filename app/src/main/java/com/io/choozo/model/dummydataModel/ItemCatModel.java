package com.io.choozo.model.dummydataModel;

public class ItemCatModel {

   Integer image;
   String productName;
   String productMRP;
   String productCutPrice;

    public ItemCatModel(Integer image, String productName, String productMRP, String productCutPrice) {

        this.image = image;
        this.productName = productName;
        this.productMRP = productMRP;
        this.productCutPrice = productCutPrice;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMRP() {
        return productMRP;
    }

    public void setProductMRP(String productMRP) {
        this.productMRP = productMRP;
    }

    public String getProductCutPrice() {
        return productCutPrice;
    }

    public void setProductCutPrice(String productCutPrice) {
        this.productCutPrice = productCutPrice;
    }
}
