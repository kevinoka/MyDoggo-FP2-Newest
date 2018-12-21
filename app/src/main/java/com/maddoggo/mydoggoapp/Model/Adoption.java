package com.maddoggo.mydoggoapp.Model;


import java.io.Serializable;

public class Adoption implements Serializable {
    private String dogName, dogDesc, dogAge, dogType, dogPict, dogId, phoneNum, location;

    public Adoption(){
        this.dogName = " ";
        this.dogDesc = " ";
        this.dogAge = " ";
        this.dogType = " ";
        this.dogPict = " ";
        this.dogId = " ";
        this.phoneNum = " ";
        this.location = " ";
    }

    public Adoption(String dogName, String dogDesc, String dogAge, String dogType, String dogPict, String dogId, String phoneNum, String location) {
        this.dogName = dogName;
        this.dogDesc = dogDesc;
        this.dogAge = dogAge;
        this.dogType = dogType;
        this.dogPict = dogPict;
        this.dogId = dogId;
        this.phoneNum = phoneNum;
        this.location = location;

    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogDesc() {
        return dogDesc;
    }

    public void setDogDesc(String dogDesc) {
        this.dogDesc = dogDesc;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogType() {
        return dogType;
    }

    public void setDogType(String dogType) {
        this.dogType = dogType;
    }

    public String getDogPict() {
        return dogPict;
    }

    public void setDogPict(String dogPict) {
        this.dogPict = dogPict;
    }

    public String getDogId() {
        return dogId;
    }

    public void setDogId(String dogId) {
        this.dogId = dogId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
