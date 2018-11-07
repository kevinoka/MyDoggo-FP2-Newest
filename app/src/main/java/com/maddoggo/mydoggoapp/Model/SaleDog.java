package com.maddoggo.mydoggoapp.Model;

public class SaleDog {
    private String dogName, price, sellerLocation;

    public SaleDog() {
    }

    public SaleDog(String dogName, String price, String sellerLocation) {

        this.dogName = dogName;
        this.price = price;
        this.sellerLocation = sellerLocation;
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

}
