package com.maddoggo.mydoggoapp.Model;

public class Doggopedia {
    private String dogDesc, dogType, dogPict, dogId;

    public Doggopedia(){

        this.dogDesc = " ";
        this.dogType = " ";
        this.dogPict = " ";
        this.dogId = " ";
    }

    public Doggopedia(String dogDesc, String dogType, String dogPict, String dogId){

        this.dogDesc = dogDesc;
        this.dogType = dogType;
        this.dogPict = dogPict;
        this.dogId = dogId;
    }


    public String getDogDesc() {
        return dogDesc;
    }

    public void setDogDesc(String dogDesc) {
        this.dogDesc = dogDesc;
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
