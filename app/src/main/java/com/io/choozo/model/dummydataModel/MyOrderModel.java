package com.io.choozo.model.dummydataModel;

public class MyOrderModel {

    String date;
    String orderid;
    String orderstatus;
    int color;

    public MyOrderModel(String date, String orderid, String orderstatus, int color) {
        this.date = date;
        this.orderid = orderid;
        this.orderstatus = orderstatus;
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
