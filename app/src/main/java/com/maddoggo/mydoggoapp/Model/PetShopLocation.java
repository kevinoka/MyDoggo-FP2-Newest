package com.maddoggo.mydoggoapp.Model;

public class PetShopLocation {
    private String petShopName, petShopLoc;

    public PetShopLocation() {
        this.petShopName = " ";
        this.petShopLoc = " ";
    }

    public PetShopLocation(String petShopName, String petShopLoc) {
        this.petShopName = petShopName;
        this.petShopLoc = petShopLoc;
    }

    public String getPetShopName() {
        return petShopName;
    }

    public void setPetShopName(String petShopName) {
        this.petShopName = petShopName;
    }

    public String getPetShopLoc() {
        return petShopLoc;
    }

    public void setPetShopLoc(String petShopLoc) {
        this.petShopLoc = petShopLoc;
    }
}
