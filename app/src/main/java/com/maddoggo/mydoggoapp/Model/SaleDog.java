package com.maddoggo.mydoggoapp.Model;

public class SaleDog {
    private String dogName, price, sellerLocation, sellDogImage;

    public SaleDog() {
    }


    public SaleDog(String dogName, String price, String sellerLocation, String sellDogImage) {

        this.dogName = dogName;
        this.price = price;
        this.sellerLocation = sellerLocation;
        this.sellDogImage = sellDogImage;
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

}
