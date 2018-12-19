package com.maddoggo.mydoggoapp.Model;

public class VetLocation {
    private String vetName, vetLocation, vetMapLocation;

    public VetLocation() {
        this.vetName = " ";
        this.vetLocation = " ";
        this.vetMapLocation = " ";
    }

    public VetLocation(String vetName, String vetLocation, String vetMapLocation) {
        this.vetName = vetName;
        this.vetLocation = vetLocation;
        this.vetMapLocation = vetMapLocation;
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

    public String getVetMapLocation() {
        return vetMapLocation;
    }

    public void setVetMapLocation(String vetMapLocation) {
        this.vetMapLocation = vetMapLocation;
    }
}
