package com.maddoggo.mydoggoapp.Model;

public class PetShopLocation {
    private String petShopName, petShopLoc, petShopMapLoc;

    public PetShopLocation() {
        this.petShopName = " ";
        this.petShopLoc = " ";
        this.petShopMapLoc = " ";
    }

    public PetShopLocation(String petShopName, String petShopLoc, String petShopMapLoc) {
        this.petShopName = petShopName;
        this.petShopLoc = petShopLoc;
        this.petShopMapLoc = petShopMapLoc;
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

    public String getPetShopMapLoc() {
        return petShopMapLoc;
    }

    public void setPetShopMapLoc(String petShopMapLoc) {
        this.petShopMapLoc = petShopMapLoc;
    }
}
