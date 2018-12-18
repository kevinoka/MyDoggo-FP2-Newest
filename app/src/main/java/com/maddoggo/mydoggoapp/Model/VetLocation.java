package com.maddoggo.mydoggoapp.Model;

public class VetLocation {
    private String vetName, vetLocation;

    public VetLocation() {
        this.vetName = " ";
        this.vetLocation = " ";
    }

    public VetLocation(String vetName, String vetLocation) {
        this.vetName = vetName;
        this.vetLocation = vetLocation;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getVetLocation() {
        return vetLocation;
    }

    public void setVetLocation(String vetLocation) {
        this.vetLocation = vetLocation;
    }
}
