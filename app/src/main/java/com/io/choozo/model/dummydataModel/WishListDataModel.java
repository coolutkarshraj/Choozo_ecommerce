package com.io.choozo.model.dummydataModel;

public class WishListDataModel {

    Integer image;
    String d_name;
    String d_amount;
    String d_amt_cut;
    String d_in_stock;
    int color;

    public WishListDataModel(Integer image, String d_name, String d_amount, String d_amt_cut, String d_in_stock, int color) {
        this.image = image;
        this.d_name = d_name;
        this.d_amount = d_amount;
        this.d_amt_cut = d_amt_cut;
        this.d_in_stock = d_in_stock;
        this.color = color;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_amount() {
        return d_amount;
    }

    public void setD_amount(String d_amount) {
        this.d_amount = d_amount;
    }

    public String getD_amt_cut() {
        return d_amt_cut;
    }

    public void setD_amt_cut(String d_amt_cut) {
        this.d_amt_cut = d_amt_cut;
    }

    public String getD_in_stock() {
        return d_in_stock;
    }

    public void setD_in_stock(String d_in_stock) {
        this.d_in_stock = d_in_stock;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
