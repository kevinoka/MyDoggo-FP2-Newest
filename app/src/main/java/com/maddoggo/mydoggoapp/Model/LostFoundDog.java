package com.maddoggo.mydoggoapp.Model;

import java.io.Serializable;

public class LostFoundDog implements Serializable {
    private String owner, dogType, dogChara, dogLastSeen, dogPict, dogName, phoneNumber;

    public LostFoundDog(){
        this.owner = "";
        this.dogType = "";
        this.dogName = "";
        this.dogChara = "";
        this.dogLastSeen = "";
        this.dogPict = "";
        this.phoneNumber = "";
    }

    public LostFoundDog(String owner, String dogType, String dogName, String dogChara, String dogLastSeen, String dogPict, String phoneNumber) {

        this.owner = owner;
        this.dogType = dogType;
        this.dogName = dogName;
        this.dogChara = dogChara;
        this.dogLastSeen = dogLastSeen;
        this.dogPict = dogPict;
        this.phoneNumber = phoneNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDogType() {
        return dogType;
    }

    public void setDogType(String dogType) {
        this.dogType = dogType;
    }

    public String getDogChara() {
        return dogChara;
    }

    public void setDogChara(String dogChara) {
        this.dogChara = dogChara;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogLastSeen() {
        return dogLastSeen;
    }

    public void setDogLastSeen(String dogLastSeen) {
        this.dogLastSeen = dogLastSeen;
    }

    public String getDogPict() {
        return dogPict;
    }

    public void setDogPict(String dogPict) {
        this.dogPict = dogPict;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
