package com.maddoggo.mydoggoapp.Model;


public class Adoption {
    private String dogName, dogDesc, dogAge, dogType, dogPict, dogId;

    public Adoption(){
        this.dogName = " ";
        this.dogDesc = " ";
        this.dogAge = " ";
        this.dogType = " ";
        this.dogPict = " ";
        this.dogId = " ";
    }

    public Adoption(String dogName, String dogDesc, String dogAge, String dogType, String dogPict, String dogId) {
        this.dogName = dogName;
        this.dogDesc = dogDesc;
        this.dogAge = dogAge;
        this.dogType = dogType;
        this.dogPict = dogPict;
        this.dogId = dogId;

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
}
