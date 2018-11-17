package com.maddoggo.mydoggoapp.Model;

public class LostFoundDog {
    private String dogType, dogChara, dogLastSeen, dogPict, dogName;

    public LostFoundDog(){

    }

    public LostFoundDog(String dogType, String dogName, String dogChara, String dogLastSeen, String dogPict) {

        this.dogType = dogType;
        this.dogName = dogName;
        this.dogChara = dogChara;
        this.dogLastSeen = dogLastSeen;
        this.dogPict = dogPict;
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
}
