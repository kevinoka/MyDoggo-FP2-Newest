package com.maddoggo.mydoggoapp.Model;

public class Doggopedia {
    private String dogDesc, dogType, dogPict;

    public Doggopedia(){
    }

    public Doggopedia(String dogDesc, String dogType, String dogPict){

        this.dogDesc = dogDesc;
        this.dogType = dogType;
        this.dogPict = dogPict;
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
}
