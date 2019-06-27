package com.io.choozo.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderDataModel {

    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("shippingFirstname")
    @Expose
    private String shippingFirstname;
    @SerializedName("shippingLastname")
    @Expose
    private String shippingLastname;
    @SerializedName("shippingAddress1")
    @Expose
    private String shippingAddress1;
    @SerializedName("shippingAddress2")
    @Expose
    private String shippingAddress2;
    @SerializedName("shippingCompany")
    @Expose
    private String shippingCompany;
    @SerializedName("shippingCity")
    @Expose
    private String shippingCity;
    @SerializedName("shippingCountry")
    @Expose
    private String shippingCountry;
    @SerializedName("shippingZone")
    @Expose
    private String shippingZone;
    @SerializedName("shippingPostcode")
    @Expose
    private String shippingPostcode;
    @SerializedName("shippingAddressFormat")
    @Expose
    private String shippingAddressFormat;
    @SerializedName("paymentFirstname")
    @Expose
    private String paymentFirstname;
    @SerializedName("paymentLastname")
    @Expose
    private String paymentLastname;
    @SerializedName("paymentAddress1")
    @Expose
    private String paymentAddress1;
    @SerializedName("paymentAddress2")
    @Expose
    private String paymentAddress2;
    @SerializedName("paymentCompany")
    @Expose
    private String paymentCompany;
    @SerializedName("paymentCity")
    @Expose
    private String paymentCity;
    @SerializedName("paymentCountry")
    @Expose
    private String paymentCountry;
    @SerializedName("paymentZone")
    @Expose
    private String paymentZone;
    @SerializedName("paymentPostcode")
    @Expose
    private String paymentPostcode;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("orderStatusId")
    @Expose
    private Integer orderStatusId;
    @SerializedName("invoicePrefix")
    @Expose
    private String invoicePrefix;
    @SerializedName("paymentAddressFormat")
    @Expose
    private String paymentAddressFormat;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("invoiceNo")
    @Expose
    private Integer invoiceNo;
    @SerializedName("orderPrefixId")
    @Expose
    private String orderPrefixId;
    @SerializedName("oderId")
    @Expose
    private Integer oderId;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getShippingFirstname() {
        return shippingFirstname;
    }

    public void setShippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    public String getPaymentLastname() {
        return paymentLastname;
    }

    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    public String getPaymentCompany() {
        return paymentCompany;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getPaymentCity() {
        return paymentCity;
    }

    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getPaymentZone() {
        return paymentZone;
    }

    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getOrderPrefixId() {
        return orderPrefixId;
    }

    public void setOrderPrefixId(String orderPrefixId) {
        this.orderPrefixId = orderPrefixId;
    }

    public Integer getOderId() {
        return oderId;
    }

    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
