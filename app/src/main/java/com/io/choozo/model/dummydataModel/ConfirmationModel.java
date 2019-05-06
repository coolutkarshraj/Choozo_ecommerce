package com.io.choozo.model.dummydataModel;

public class ConfirmationModel {
    Integer image;
    String dressName;
    String size;
    String color;
    String Mrp;
    String CutMrp;
    String Quantity;

    public ConfirmationModel(Integer image, String dressName, String size, String color, String mrp, String cutMrp, String quantity) {
        this.image = image;
        this.dressName = dressName;
        this.size = size;
        this.color = color;
        Mrp = mrp;
        CutMrp = cutMrp;
        Quantity = quantity;
    }




    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getDressName() {
        return dressName;
    }

    public void setDressName(String dressName) {
        this.dressName = dressName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMrp() {
        return Mrp;
    }

    public void setMrp(String mrp) {
        Mrp = mrp;
    }

    public String getCutMrp() {
        return CutMrp;
    }

    public void setCutMrp(String cutMrp) {
        CutMrp = cutMrp;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
