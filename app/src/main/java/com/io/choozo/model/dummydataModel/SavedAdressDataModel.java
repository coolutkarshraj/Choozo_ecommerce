package com.io.choozo.model.dummydataModel;

public class SavedAdressDataModel {

    String name;
    String address;

    public SavedAdressDataModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
