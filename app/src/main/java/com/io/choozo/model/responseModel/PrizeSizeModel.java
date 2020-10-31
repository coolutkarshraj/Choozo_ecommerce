package com.io.choozo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.todayDealsModel.Size;

import java.util.List;

public class PrizeSizeModel {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("attributeId")
    @Expose
    private Integer attributeId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("colorId")
    @Expose
    private Object colorId;
    @SerializedName("sizeId")
    @Expose
    private Integer sizeId;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("colors")
    @Expose
    private List<Object> colors = null;
    @SerializedName("size")
    @Expose
    private Size size;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Object getColorId() {
        return colorId;
    }

    public void setColorId(Object colorId) {
        this.colorId = colorId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public List<Object> getColors() {
        return colors;
    }

    public void setColors(List<Object> colors) {
        this.colors = colors;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
