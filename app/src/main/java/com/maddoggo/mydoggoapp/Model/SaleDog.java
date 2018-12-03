package com.maddoggo.mydoggoapp.Model;

import java.io.Serializable;

public class SaleDog implements Serializable {
    private String dogName, price, sellerLocation, sellDogImage, dogDesc, phoneNumber;

    public SaleDog() {
        this.dogName = "";
        this.price = "";
        this.sellerLocation = "";
        this.sellDogImage = "";
        this.dogDesc = "";
        this.phoneNumber = "";
    }

    public SaleDog(String dogName, String price, String sellerLocation, String sellDogImage, String dogDesc, String phoneNumber) {

        this.dogName = dogName;
        this.price = price;
        this.sellerLocation = sellerLocation;
        this.sellDogImage = sellDogImage;
        this.dogDesc = dogDesc;
        this.phoneNumber = phoneNumber;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerLocation() {
        return sellerLocation;
    }

    public void setSellerLocation(String sellerLocation) {
        this.sellerLocation = sellerLocation;
    }

    public String getSellDogImage() {
        return sellDogImage;
    }

    public void setSellDogImage(String sellDogImage) {
        this.sellDogImage = sellDogImage;
    }

    public String getDogDesc() {
        return dogDesc;
    }

    public void setDogDesc(String dogDesc) {
        this.dogDesc = dogDesc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
