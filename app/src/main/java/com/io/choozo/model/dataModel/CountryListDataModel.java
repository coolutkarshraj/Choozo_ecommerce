package com.io.choozo.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryListDataModel {

    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isoCode2")
    @Expose
    private String isoCode2;
    @SerializedName("isoCode3")
    @Expose
    private String isoCode3;
    @SerializedName("postcodeRequired")
    @Expose
    private Integer postcodeRequired;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode2() {
        return isoCode2;
    }

    public void setIsoCode2(String isoCode2) {
        this.isoCode2 = isoCode2;
    }

    public String getIsoCode3() {
        return isoCode3;
    }

    public void setIsoCode3(String isoCode3) {
        this.isoCode3 = isoCode3;
    }

    public Integer getPostcodeRequired() {
        return postcodeRequired;
    }

    public void setPostcodeRequired(Integer postcodeRequired) {
        this.postcodeRequired = postcodeRequired;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
