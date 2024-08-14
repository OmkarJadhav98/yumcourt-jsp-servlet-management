package com.yumcourt.model;

import org.json.JSONObject;


public class Address {
    private long id;
    private String name;
    private long flatNo;
    private String buildingName;
    private String street;
    private String city;
    private long pinCode;
    private String state;

    public Address(long id, String name, long flatNo, String buildingName, String street, String city, long pinCode, String state) {
        this.id = id;
        this.name = name;
        this.flatNo = flatNo;
        this.buildingName = buildingName;
        this.street = street;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getFlatNo() { return flatNo; }
    public void setFlatNo(long flatNo) { this.flatNo = flatNo; }
    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public long getPinCode() { return pinCode; }
    public void setPinCode(long pinCode) { this.pinCode = pinCode; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("flatNo", this.flatNo);
        json.put("buildingName", this.buildingName);
        json.put("street", this.street);
        json.put("city", this.city);
        json.put("pinCode", this.pinCode);
        json.put("state", this.state);
        return json;
    }
}
